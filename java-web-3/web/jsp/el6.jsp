<%@ page import="com.xyb.entity.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--
            el表达式：全称是Expression Language，是表达式语言；
            作用：代替jsp页面的表达式脚本在jsp页面进行数据的输出，简化表达式脚本，主要
        是输出域对象中的数据，${key} => <%=域对象.getAttribute("key")%>
            格式：${表达式}；
            el表达式在输出null值的时候，输出的是空串。jsp表达式脚本输出null值的时候，
        输出的是null；
            el表达式搜索域数据的顺序：当4个域中都有相同的key的数据的时候，el表达式会
        按照4个域的从小到大的顺序进行搜索，找到就输出。pageContext、request、session、
        application。
        --%>
        <%
            // 往4个域中都保存相同的key数据
            pageContext.setAttribute("key1", "pageContext");
            request.setAttribute("key1", "request");
            session.setAttribute("key1", "session");
            application.setAttribute("key1", "application");

            request.setAttribute("key2", "request2");
            session.setAttribute("key2", "session2");
            application.setAttribute("key2", "application2");

            session.setAttribute("key3", "session3");
            application.setAttribute("key3", "application3");

        %>
        <div>
            <p>获取域对象中key1属性的值${key1}</p>
            <p>获取域对象中key2属性的值${key2}</p>
            <p>获取域对象中key3属性的值${key3}</p>
        </div>

        <%-- el表达式输出非基本类型，需要类中有get方法，本质用的是反射 --%>
        <%
            Person p = new Person();
            p.setName("123");
            p.setAge(18);
            p.setLovers(new String[]{"l1", "l2", "l3"});

            List<String> schoolList = new ArrayList<String>();
            schoolList.add("sc1");
            schoolList.add("sc2");
            schoolList.add("sc3");
            p.setSchoolList(schoolList);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("key1", "v1");
            map.put("key2", "v2");
            p.setMap(map);

            pageContext.setAttribute("p", p);
        %>
        <div>
            获取域对象中p对应person实例
            <p>p.name = ${p.name}</p>
            <p>p.age = ${p.age}</p>

            <p>获取数组句柄：p.lovers = ${p.lovers}</p>
            <p>获取数组的第0个元素：p.lovers[0] = ${p.lovers[0]}</p><br/>

            <p>获取list的所有元素：p.schoolList = ${p.schoolList}</p>
            <p>获取list的第1个元素：p.schoolList[0] = ${p.schoolList[1]}</p><br/>

            <p>获取map的所有元素：p.map = ${p.map}</p>
            <p>获取map的key = key2的元素：p.map.get("key2") = ${p.map.get("key2")}</p>
            <p>获取map的key = key1的元素：p.map.key1 = ${p.map.key1}</p>
        </div>


    </body>
</html>
