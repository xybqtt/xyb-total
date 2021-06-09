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

        <%--
            el表达式——运算
            关系运算，返回boolean：
                == 或 eq：等于；
                ！= 或 ne：不等于；
                < 或 lt：小于；
                > 或 gt：大于；
                <= 或 le：<=于；
                >= 或 ge：>=于；
            逻辑运算：
                && 或 and：与运算；
                || 或 or：或运算；
                ! 或 not：取反运算；
            算术运算：
                +：加法；
                -：减法；
                *：乘法；
                / 或 div：除法；
                % 或 mod：取模；
            empty运算：
                可以判断一个数据是否为空，为空则返回true；以下几种情况为空：
                1、值为null或空串；
                2、值为List、Map或Object类型的数组，元素个数为0。
         --%>
        <%-- 关系运算 --%>
        <h1>关系运算</h1>
        ${ 5 == 5 }<br/>
        ${ 5 != 5 }<br/>
        ${ 5 < 5 }<br/>
        ${ 5 > 5 }<br/>
        ${ 5 <= 5 }<br/>
        ${ 5 >= 5 }<br/>
        <hr/>

        <%-- 逻辑运算 --%>
        <h1>逻辑运算</h1>
        ${ (12 == 12) && (12 == 11) } 或 ${ (12 == 12) && (11 == 11) }<br/>
        ${ (12 == 12) || (12 == 11) } 或 ${ (12 == 11) || (11 == 12) }<br/>
        ${ !(12 == 12) } 或 ${ !(12 != 12) }<br/>
        <hr/>

        <%-- 算术运算 --%>
        ${ 12 + 18 }<br/>
        ${ 12 - 18 }<br/>
        ${ 2 * 3 }<br/>
        ${ 12 / 3 }<br/>
        ${ 18 % 12 }<br/>
        <hr/>

        <%-- empty运算 --%>
        <h1>empty运算</h1>
        <%
            pageContext.setAttribute("a", null);
            pageContext.setAttribute("b", "1");
            pageContext.setAttribute("objArr", new Object[5]);
            pageContext.setAttribute("list", new ArrayList());
            pageContext.setAttribute("map2", new HashMap());
        %>
        ${ empty a }<br/>
        ${ empty b }<br/>
        ${ empty objs }<br/>
        ${ empty list }<br/>
        ${ empty map2 }<br/>


    </body>
</html>
