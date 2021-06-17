<%@ page import="com.xyb.entity.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page pageEncoding="UTF-8" %>
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
        --%>


        <%-- el表达式输出非基本类型，需要类中有get方法，本质用的是反射 --%>
        <%--
            "."运算：可以输出bean对象中某个属性的值，p.name可以用p["name"]代替。
            "[]"运算：
                可以输出有序集合中某个元素的值，并且[]中括号运算，还
            可以输出map集合中key里含有特殊字符的key的值。例map[".a.a."]，因为
            .也是特殊运算，如果不这样获取的话，会有歧义。

            获取map中的一些value可以有3种写法：
                p.key；
                p.get("key");
                p["key"]
        --%>
        <h1>"."点运算</h1>
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
            map.put(".a.a.a", "v2");

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
            <p>三种获取map中value的方式</p>
            <p>获取map的key = key2的元素：p.map.get("key2") = ${p.map.get("key2")}</p>
            <p>获取map的key = key1的元素：p.map.key1 = ${p.map.key1}</p>
            <p>获取map的key = ".a.a.a"的元素：p.map.key1 = ${p.map[".a.a.a"]}</p>

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
         --%>
        <h1>关系运算</h1>
        ${ 5 == 5 }<br/>
        ${ 5 != 5 }<br/>
        ${ 5 < 5 }<br/>
        ${ 5 > 5 }<br/>
        ${ 5 <= 5 }<br/>
        ${ 5 >= 5 }<br/>
        <hr/>


        <%--
            逻辑运算：
                && 或 and：与运算；
                || 或 or：或运算；
                ! 或 not：取反运算；
        --%>
        <h1>逻辑运算</h1>
        ${ (12 == 12) && (12 == 11) } 或 ${ (12 == 12) && (11 == 11) }<br/>
        ${ (12 == 12) || (12 == 11) } 或 ${ (12 == 11) || (11 == 12) }<br/>
        ${ !(12 == 12) } 或 ${ !(12 != 12) }<br/>
        <hr/>

        <%--
            算术运算：
                +：加法；这个+号不能连接字符串，可以用a.concat("122")来做字符串的连接；
                -：减法；
                *：乘法；
                / 或 div：除法；
                % 或 mod：取模；
        --%>
        ${ 12 + 18 }<br/>
        ${ 12 - 18 }<br/>
        ${ 2 * 3 }<br/>
        ${ 12 / 3 }<br/>
        ${ 18 % 12 }<br/>
        <hr/>

        <%--
            empty运算：
                可以判断一个域对象中的某个数据是否为空，为空则返回true；以下几种情况为空：
                1、值为null或空串；
                2、值为List、Map或Object类型的数组，元素个数为0。
        --%>
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

        <%-- 三元运算 --%>
        <h1>三元表达式</h1>
        ${ (12 == 12) ? "1" : "2"}





    </body>
</html>
