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
package org.apache.catalina;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.mapper.Mapper;

/**
 * A <strong>Service</strong> is a group of one or more
 * <strong>Connectors</strong> that share a single <strong>Container</strong>
 * to process their incoming requests.  This arrangement allows, for example,
 * a non-SSL and SSL connector to share the same population of web apps.
 * <p>
 * A given JVM can contain any number of Service instances; however, they are
 * completely independent of each other and share only the basic JVM facilities
 * and classes on the system class path.
 *
 * @author Craig R. McClanahan
 */
public interface Service extends Lifecycle {

    // ------------------------------------------------------------- Properties

    /**
     * 返回处理Service中所有Connector接收的请求的Engine
     *
     * @return the <code>Engine</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     */
    public Engine getContainer();

    /**
     * 设置处理Service中所有Connector接收的请求的Engine
     *
     * Set the <code>Engine</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     *
     * @param engine The new Engine
     */
    public void setContainer(Engine engine);

    /**
     * 获取service.name属性，默认取server.xml的service标签name属性
     * @return the name of this Service.
     */
    public String getName();

    /**
     * 设置Service的name，默认取server.xml的service标签name属性
     * Set the name of this Service.
     *
     * @param name The new service name
     */
    public void setName(String name);

    /**
     * 返回Service所在的Server，一个Server.xml只有一个Server。
     *
     * @return the <code>Server</code> with which we are associated (if any).
     */
    public Server getServer();

    /**
     * 设置Service所属的Server。一个Service只能属于一个Server
     *
     * Set the <code>Server</code> with which we are associated (if any).
     *
     * @param server The server that owns this Service
     */
    public void setServer(Server server);

    /**
     * 返回本类的类加载器。如果没有设置，返回本类Server属性的clsLoad。如果还没有设置
     * Server，则返回 appClsLoader
     *
     * @return the parent class loader for this component. If not set, return
     * {@link #getServer()} {@link Server#getParentClassLoader()}. If no server
     * has been set, return the system class loader.
     */
    public ClassLoader getParentClassLoader();

    /**
     *
     * 设置本类的类加载器
     *
     * Set the parent class loader for this service.
     *
     * @param parent The new parent class loader
     */
    public void setParentClassLoader(ClassLoader parent);

    /**
     * 获取域，默认从server.xml的Service的子标签Engine的name，
     * 如果没有，则取Service标签的name属性，
     * 如果还没有，代码中设置了默认domain：Catalina
     *
     * @return the domain under which this container will be / has been
     * registered.
     */
    public String getDomain();


    // --------------------------------------------------------- Public Methods

    /**
     * 添加此与此Service关联的Connector
     *
     * Add a new Connector to the set of defined Connectors, and associate it
     * with this Service's Container.
     *
     * @param connector The Connector to be added
     */
    public void addConnector(Connector connector);

    /**
     * 获取与此Service相关的所有Connector
     *
     * Find and return the set of Connectors associated with this Service.
     *
     * @return the set of associated Connectors
     */
    public Connector[] findConnectors();

    /**
     * 移除参数中的Connector
     *
     * Remove the specified Connector from the set associated from this
     * Service.  The removed Connector will also be disassociated from our
     * Container.
     *
     * @param connector The Connector to be removed
     */
    public void removeConnector(Connector connector);

    /**
     * 向此Service添加一个 executor(执行器)
     *
     * Adds a named executor to the service
     * @param ex Executor
     */
    public void addExecutor(Executor ex);

    /**
     * 返回所有 executor
     *
     * Retrieves all executors
     * @return Executor[]
     */
    public Executor[] findExecutors();

    /**
     * 返回name为参数的执行器
     *
     * Retrieves executor by name, null if not found
     * @param name String
     * @return Executor
     */
    public Executor getExecutor(String name);

    /**
     * 移除参数中对应的执行器
     *
     * Removes an executor from the service
     * @param ex Executor
     */
    public void removeExecutor(Executor ex);

    /**
     * @return the mapper associated with this Service.
     */
    Mapper getMapper();
}
