<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xyb.entity.Person" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>jstl标签库</title>
        <style type="text/css">
            div {
                border: 1px solid black;
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body>
        <%--
        一、jstl标签说明
            JSTL标签库，全称是指JSP Stansard Tag Library，jsp标准标签库，是一个
        不断完善的开源jsp标签库；
            EL表达式主要是为了替换jsp中的表达式脚本，而标签库则是为了替换代码脚本，使
        jsp页面更加简洁。
            JSTL由5个不同功能的标签库组成：
            核心标签库：
                URI：http://java.sun.com/jsp/jstl/core；
                前缀：c；
                引入：<%@ tablib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>；
            格式化：
                URI：http://java.sun.com/jsp/jstl/fmt；
                前缀：fmt；
                引入：<%@ tablib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>；
            函数：
                URI：http://java.sun.com/jsp/jstl/functions；
                前缀：fn；
                引入：<%@ tablib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>；
            数据库(不使用)：
                URI：http://java.sun.com/jsp/jstl/sql；
                前缀：sql；
                引入：<%@ tablib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  ；
            xml(不使用)：
                URI：http://java.sun.com/jsp/jstl/xml；
                前缀：x；
                引入：<%@ tablib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>；

        二、jstl标签的使用
            1、导入jstl标签库的jar包：taglibs-standard-impl-1.2.5.jar、taglibs-standard-spec-1.2.5.jar；
            2、使用taglib导入标签库，如<%@ tablib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            3、使用
         --%>
        <div>
            <h1>jstl核心库的使用</h1>
            <%--
                c:set标签：给域对象设置属性，相当于 域对象.setAttribute("key", "value")
                    scope属性：表示设置保存到哪个域
                        page：PageContext域(默认值)；
                        request：Request域；
                        session：Session域；
                        application：ServletContext域；
                    var属性：表示需要设置的key为多少；
                    value属性：表示设置属性值为多少。

             --%>
            <c:set scope="request" var="requestKey" value="requestValue"/>
            <p>request.requestKey = ${requestScope["requestKey"]}</p>

            <%--
                c:if标签：用来做if判断，当条件成立时，会输出标签的内容，否则不会
                    test属性：表示判断的条件，值为el表达式
             --%>
            <c:if test="${requestScope['requestKey'] == 'requestValue'}">
                <p>通过c:if标签的判断，request.requestKey = requestValue</p>
            </c:if>

            <%--
                c:choose c:when c:otherwise 标签：和switch case default特别接近
                choose标签：开始选择判断；
                when标签：表示每一各判断情况
                    test属性：表示当前这种判断情况的值；
                otherwise标签：表示剩下的情况；

                注意，
                    1、这几个标签里面不能使用html注释，可以使用jsp注释；
                    2、when标签的父标签只能是choose标签，如果在otherwise中使用when标签，
                必须在里面重新写一整套choose标签。
             --%>
            <% request.setAttribute("height", 178); %>
            <c:choose>
                <c:when test="${requestScope.height > 180}">
                    <h2>180以上</h2>
                </c:when>
                <c:when test="${requestScope.height > 170}">
                    <h2>170-180之间</h2>
                </c:when>
                <c:otherwise>
                    <h2>170以下</h2>
                </c:otherwise>
            </c:choose>

            <%--
                c:forEach：遍历标签
                    begin属性：设置开始索引；
                    end属性：设置结束索引(无法获取list的长度，因为size属性没有getSize()方法)；
                    var属性：设置循环的变量(也是当前正在遍历的数据)；
                    step属性：表示遍历的步长值；
                    varStatus属性：表示当前遍历数据的状态，是LoopTagStatus的实现类，
                可以获取当前对象的一些信息，如第几个、是否最后一个等，下面遍历list有示例

            --%>
            <c:forEach begin="0" end="9" var="i">
                ${i}
            </c:forEach>

            <%
                request.setAttribute("arr", new String[]{"a", "b", "c"});

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("key1", "v1");
                map.put("key2", "v2");
                map.put("key3", "v3");
                request.setAttribute("map", map);

                List<Person> list = new ArrayList<Person>();
                list.add(new Person("张三", 18));
                list.add(new Person("李四", 18));
                list.add(new Person("王五", 18));
                list.add(new Person("赵6", 18));
                request.setAttribute("list", list);


            %>
            <h2>遍历数组</h2>
            <c:forEach items="${requestScope.arr}" var="item">
                ${item}
            </c:forEach>
            <br/>
            <h2>遍历map</h2>
            <c:forEach items="${requestScope.map}" var="entry">
                ${entry.key} : ${entry.value}
            </c:forEach>
            <br/>
            <h2>遍历list</h2>
            <table border="1px solid red" cellspacing="2px">
                <thead>
                    <td>序号</td>
                    <td>name</td>
                    <td>age</td>
                    <td>当前对象</td>
                    <td>已经遍历的个数</td>
                    <td>是否第1个元素</td>
                    <td>是否最后1个元素</td>
                    <td>此次forEach的begin、end、step的值</td>
                </thead>
                <c:forEach begin="0" step="1" items="${requestScope.list}" varStatus="varstatus" var="person">
                    <tr>
                        <td>${varstatus.index}</td>
                        <td>${person.name}</td>
                        <td>${person.age}</td>
                        <td>${varstatus.current}</td>
                        <td>${varstatus.count}</td>
                        <td>${varstatus.first}</td>
                        <td>${varstatus.last}</td>
                        <td>${varstatus.begin}, ${varstatus.end}, ${varstatus.step}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </body>
</html>
