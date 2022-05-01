package com.xyb.a3jmx;

import javax.management.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 动态MBean：
 * 我们最终注册到MBeanServer上的是MBeanInfo信息，静态MBeanInfo信息是根据
 * MBean接口得到的。
 * <p>
 * client最终调用server的MBean时，其实只需要2、3个参数，objectName、被调用的属性名、方法名、方法参数等。
 * 那我们其实可以不用写UserMBean接口，直接通过另一种方式给MBeanServer传递一个MBeanInfo就行，反正又不进行
 * 检查，这就是动态MBean。
 * <p>
 * 动态MBean发布的通知类型不用通过NotificationBroadcasterSupport.getNotificationInfo()进行重写，
 * 直接在生成MBeanInfo时，将通知写进去就行，但是因为需要调用sendNotification(notification)发送通知，
 * 所以还是要 extends NotificationBroadcasterSupport。
 */
public class MyDynamicMBean extends NotificationBroadcasterSupport implements DynamicMBean {

    /**
     * 可被调用的资源
     */
    private Object resource;

    // 要注册到MBeanServer的MBeanInfo信息，注意只有信息，属性没有具体的值，方法没有具体实现。
    private MBeanInfo mBeanInfo;

    /**
     * field对应的get方法Map
     */
    private Map<String, Method> fieldGetMethodMap;

    /**
     * field对应的set方法Map
     */
    private Map<String, Method> fieldSetMethodMap;

    /**
     * 非set、get方法Map
     */
    private Map<String, Method> methodMap;


    /**
     * 主要是对 MBeanInfo信息进行初始化
     */
    public MyDynamicMBean(Object obj) {

        this.resource = obj;

        // 1、获取this.resource.getClass()，并通过反射获取field、method信息
        Map<String, Object> clsInfoMap = getClassInfo(this.resource.getClass());
        Constructor[] constructors = (Constructor[]) clsInfoMap.get("constructors");
        Map<String, Field> getfieldsMap = (Map) clsInfoMap.get("getfields");
        Map<String, Field> setfieldsMap = (Map) clsInfoMap.get("setfields");
        Method[] methods = (Method[]) clsInfoMap.get("methods");

        /**
         * 1、MBeanInfo的构造方法信息，
         * 根据this.resource的构造方法信息，创建MBeanInfo的构造方法信息
         */
        MBeanConstructorInfo[] mbConstrucInfos = new MBeanConstructorInfo[constructors.length];
        for (int i = 0; i < constructors.length; i++)
            mbConstrucInfos[i] = new MBeanConstructorInfo(this.resource.getClass() + "第" + i + "个构造方法信息", constructors[i]);


        /**
         * 2、MBeanInfo的属性信息查询，查看get、set方法，决定其可读可写
         */
        Field[] fields = this.resource.getClass().getDeclaredFields();
        List<MBeanAttributeInfo> mbAttrInfoList = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = field.getName().toLowerCase();

            // 判断是否有可读或可写的属性
            if (getfieldsMap.get(fieldName) == null && setfieldsMap.get(fieldName) == null)
                continue;

            mbAttrInfoList.add(new MBeanAttributeInfo(
                    field.getName(),
                    field.getType().getName(),
                    "描述",
                    getfieldsMap.get(fieldName) == null ? false : true,
                    setfieldsMap.get(fieldName) == null ? false : true,
                    false));

        }
        MBeanAttributeInfo[] mbAttrInfos = mbAttrInfoList.toArray(new MBeanAttributeInfo[mbAttrInfoList.size()]);


        /**
         * 3、动态MBean有哪些方法，MBeanOperationInfo参数信息如下：
         *      方法名；
         *      方法描述；
         *      方法参数：
         *          参数名；
         *          参数类型；
         *          参数描述
         *      返回类型
         *      影响状态
         */
        MBeanOperationInfo[] mbOperaInfos = new MBeanOperationInfo[methods.length];
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            // 组装参数信息
            Parameter[] parameters = method.getParameters();
            MBeanParameterInfo[] params = new MBeanParameterInfo[parameters.length];
            for (int j = 0; j < parameters.length; j++) {
                params[j] = new MBeanParameterInfo(
                        parameters[j].getName(),
                        parameters[j].getType().getName(),
                        null);
            }

            // 组装方法信息
            mbOperaInfos[i] = new MBeanOperationInfo(method.getName(),
                    "方法" + method.getName(),
                    params,
                    method.getReturnType().getName(),
                    MBeanOperationInfo.INFO
            );
        }

        /**
         * 4、动态MBean的通知，MBeanNotificationInfo参数如下：
         *      可被订阅通知类型数组
         *      通知类型全限定名
         *      通知描述
         */
        MBeanNotificationInfo[] mbNotiInfos = new MBeanNotificationInfo[]{
                new MBeanNotificationInfo(
                        new String[]{
                                AttributeChangeNotification.ATTRIBUTE_CHANGE
                        },
                        AttributeChangeNotification.class.getName(),
                        "动态MBean的通知"
                )
        };

        //create mbean
        this.mBeanInfo = new MBeanInfo(
                this.getClass().getName(),
                "自动义动态MBean",
                mbAttrInfos,
                mbConstrucInfos,
                mbOperaInfos,
                mbNotiInfos);
    }


    /**
     * @param cls
     * @return Map：
     * getfields -> 所有对应get方法的Map(属性名 -> Field)
     * setfields -> 所有对应set方法的Map(属性名 -> Field)
     * methods -> 所有方法中除了get、set方法(get、set后面不是属性名的除外)，
     * constructors -> 所有构造方法
     */
    private Map<String, Object> getClassInfo(Class cls) {

        this.fieldGetMethodMap = new HashMap<>();
        this.fieldSetMethodMap = new HashMap<>();
        this.methodMap = new HashMap<>();

        // 1、获取所有的属性、方法
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Constructor[] constructors = cls.getConstructors();

        // 2、将属性、方法转换为map
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields)
            fieldMap.put(field.getName().toLowerCase(), field);

        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : methods)
            methodMap.put(method.getName().toLowerCase(), method);


        // 3、获取所有有get或set方法的属性
        Map<String, Field> fieldGetMap = new HashMap<>();
        Map<String, Field> fieldSetMap = new HashMap<>();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if (!methodName.startsWith("get") && !methodName.startsWith("set"))
                continue;

            String fieldNameInMethod = methodName.substring(3).toLowerCase();
            boolean delMethodFlag = false;

            if (methodName.startsWith("get") && fieldMap.get(fieldNameInMethod) != null) {
                delMethodFlag = true;
                fieldGetMap.put(fieldNameInMethod, fieldMap.get(fieldNameInMethod));
                this.fieldGetMethodMap.put(fieldNameInMethod, methods[i]);
            }

            if (methodName.startsWith("set") && fieldMap.get(fieldNameInMethod) != null) {
                delMethodFlag = true;
                fieldSetMap.put(fieldNameInMethod, fieldMap.get(fieldNameInMethod));
                this.fieldSetMethodMap.put(fieldNameInMethod, methods[i]);
            }

            if (delMethodFlag)
                methods[i] = null;
        }

        List<Method> list = Arrays.stream(methods).
                filter(method -> method != null).
                collect(Collectors.toList());
        list.stream().forEach(method -> this.methodMap.put(method.getName(), method));
        methods = list.toArray(new Method[list.size()]);

        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("getfields", fieldGetMap);
        retMap.put("setfields", fieldSetMap);
        retMap.put("methods", methods);
        retMap.put("constructors", constructors);

        return retMap;
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        Method method = this.fieldGetMethodMap.get(attribute.toLowerCase());
        try {
            return method.invoke(this.resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        Method method = this.fieldSetMethodMap.get(attribute.getName().toLowerCase());
        try {
            method.invoke(this.resource, attribute.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        try {
            AttributeList attrList = new AttributeList();
            for (String attribute : attributes)
                attrList.add(getAttribute(attribute));
            return attrList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        try {
            for (Object attribute : attributes)
                setAttribute((Attribute) attribute);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        try {
            return this.methodMap.get(actionName).invoke(this.resource, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return this.mBeanInfo;
    }
}
