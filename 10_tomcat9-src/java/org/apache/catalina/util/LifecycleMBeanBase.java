/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.catalina.Globals;
import org.apache.catalina.JmxEnabled;
import org.apache.catalina.LifecycleException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.modeler.Registry;
import org.apache.tomcat.util.res.StringManager;

public abstract class LifecycleMBeanBase extends LifecycleBase
        implements JmxEnabled {

    private static final Log log = LogFactory.getLog(LifecycleMBeanBase.class);

    private static final StringManager sm =
        StringManager.getManager("org.apache.catalina.util");

    /**
     * 域名，"domain:type=a,name=b"，domain就是域名。
     * 获取objectName的域名，优先server.xml中Engine.name，如果没有则使用Service.name
     * 默认的server.xml文件中是Catalina。
     * Cache components of the MBean registration.
     */
    private String domain = null;

    /**
     * 子类组件注册后的ObjectName，类似："Catalina:type=Server"、"Catalina:type=Service"等。
     */
    private ObjectName oname = null;

    @Deprecated
    protected MBeanServer mserver = null;

    /**
     * 如果子类想将其(组件)注册到MBeanServer上，则应该重写这个方法，并首先调用super.initInternal()。
     * 本类的此方法功能是注册组件到MBeanServer上。
     *
     * Sub-classes wishing to perform additional initialization should override
     * this method, ensuring that super.initInternal() is the first call in the
     * overriding method.
     */
    @Override
    protected void initInternal() throws LifecycleException {
        // preRegister();
        /**
         * If oname is not null then registration has already happened via
         * 如果此组件的 this.oname(objectName) != null，证明已经注册到MBeanServer上了。
         * 如果 this.oname = null，则进行注册
         */
        if (oname == null) {
            // 给this.mserver赋值，记录下子类组件是到哪个MBeanServer注册的，实际注册是在Registry实现的。
            mserver = Registry.getRegistry(null, null).getMBeanServer();

            /**
             * 注册此组件，第2个参数是objectName的type，各个组件实现有各自的type，如
             *  type=Server、type=Service等。
             *  并返回objectName：类似
             *      Catalina:type=Server
             *      Catalina:type=Service等
             */
            oname = register(this, getObjectNameKeyProperties());
        }
    }


    /**
     * Sub-classes wishing to perform additional clean-up should override this
     * method, ensuring that super.destroyInternal() is the last call in the
     * overriding method.
     */
    @Override
    protected void destroyInternal() throws LifecycleException {
        unregister(oname);
    }


    /**
     * Specify the domain under which this component should be registered. Used
     * with components that cannot (easily) navigate the component hierarchy to
     * determine the correct domain to use.
     */
    @Override
    public final void setDomain(String domain) {
        this.domain = domain;
    }


    /**
     * 获取域名，"domain:type=a,name=b"，domain就是域名。
     * 获取objectName的域名，优先使用Engine.name，如果没有则使用Service.name
     *      <Service name="Catalina">
     *          <Engine name="Catalina"></Engine>
     *      </Service>
     *
     * Obtain the domain under which this component will be / has been
     * registered.
     */
    @Override
    public final String getDomain() {
        if (domain == null) {
            domain = getDomainInternal();
        }

        if (domain == null) {
            domain = Globals.DEFAULT_MBEAN_DOMAIN;
        }

        return domain;
    }


    /**
     * Method implemented by sub-classes to identify the domain in which MBeans
     * should be registered.
     *
     * @return  The name of the domain to use to register MBeans.
     */
    protected abstract String getDomainInternal();


    /**
     * Obtain the name under which this component has been registered with JMX.
     */
    @Override
    public final ObjectName getObjectName() {
        return oname;
    }


    /**
     * 获取组件的域，对于Server来说即ObjectName中"com.xyb:type=server,name=user"中的type=server部分，
     *
     * Allow sub-classes to specify the key properties component of the
     * {@link ObjectName} that will be used to register this component.
     *
     * @return  The string representation of the key properties component of the
     *          desired {@link ObjectName}
     */
    protected abstract String getObjectNameKeyProperties();


    /**
     *
     * Utility method to enable sub-classes to easily register additional
     * components that don't implement {@link JmxEnabled} with an MBean server.
     * <br>
     * Note: This method should only be used once {@link #initInternal()} has
     * been called and before {@link #destroyInternal()} has been called.
     *
     * @param obj                       被注册的组件The object the register
     * @param objectNameKeyProperties   JMX的objectName的 "type=sss"部分。
     *                                  The key properties component of the
     *                                  object name to use to register the
     *                                  object
     *
     * @return  The name used to register the object
     */
    protected final ObjectName register(Object obj,
            String objectNameKeyProperties) {

        // 组装objectname = "domain:type=Server"、
        /**
         * 组装objectName ：
         *      其中domain的取值规，优先取 Engine.name属性，没有则取Service.name
         *      type：是根据组件标准实现类来的，如Server、Service
         * name最终格式举例："Catalina:type=Server"
         */
        StringBuilder name = new StringBuilder(getDomain());
        name.append(':');
        name.append(objectNameKeyProperties);

        ObjectName on = null;

        try {
            on = new ObjectName(name.toString());
            // 注册子类组件
            Registry.getRegistry(null, null).registerComponent(obj, on, null);
        } catch (Exception e) {
            log.warn(sm.getString("lifecycleMBeanBase.registerFail", obj, name), e);
        }

        return on;
    }


    /**
     * Utility method to enable sub-classes to easily unregister additional
     * components that don't implement {@link JmxEnabled} with an MBean server.
     * <br>
     * Note: This method should only be used once {@link #initInternal()} has
     * been called and before {@link #destroyInternal()} has been called.
     *
     * @param objectNameKeyProperties   The key properties component of the
     *                                  object name to use to unregister the
     *                                  object
     */
    protected final void unregister(String objectNameKeyProperties) {
        // Construct an object name with the right domain
        StringBuilder name = new StringBuilder(getDomain());
        name.append(':');
        name.append(objectNameKeyProperties);
        Registry.getRegistry(null, null).unregisterComponent(name.toString());
    }


    /**
     * Utility method to enable sub-classes to easily unregister additional
     * components that don't implement {@link JmxEnabled} with an MBean server.
     * <br>
     * Note: This method should only be used once {@link #initInternal()} has
     * been called and before {@link #destroyInternal()} has been called.
     *
     * @param on    The name of the component to unregister
     */
    protected final void unregister(ObjectName on) {
        Registry.getRegistry(null, null).unregisterComponent(on);
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void postDeregister() {
        // NOOP
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void postRegister(Boolean registrationDone) {
        // NOOP
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void preDeregister() throws Exception {
        // NOOP
    }


    /**
     * Allows the object to be registered with an alternative
     * {@link MBeanServer} and/or {@link ObjectName}.
     */
    @Override
    public final ObjectName preRegister(MBeanServer server, ObjectName name)
            throws Exception {

        this.mserver = server;
        this.oname = name;
        this.domain = name.getDomain().intern();

        return oname;
    }

}
