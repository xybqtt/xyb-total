package com.xyb.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyb.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 服务器端Json的使用，需要gson包
 */
public class JsonServlet12 extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        // 1、根据前端传送的json字符串，转换为实体类
        Person person = transStrToBean(new String(req.getParameter("jsonstr").getBytes("ISO-8859-1"), "UTF-8"));

        // 2、将单bean实例转换为json字符串
        String jsonStr = new Gson().toJson(person);
        writeln("2、java对象转json字符串：" + jsonStr);

        // 3、将数组json转换为具体的List<Person>对象
        List<Person> personList = transArrJsonStrToList(new String(req.getParameter("listJsonStr").getBytes("ISO-8859-1"), "UTF-8"));

        // 4、将List<Person> 转换为json字符串
        jsonStr = new Gson().toJson(personList);
        writeln("4、Person的List转json字符串：" + jsonStr);

        // 5、json和Map互转
        Map<String, Person> personMap = transMapJsonStrToMap(new String(req.getParameter("mapJsonStr").getBytes("ISO-8859-1"), "UTF-8"));

        // 6、将Map转为json字符串
        jsonStr = new Gson().toJson(personMap);
        writeln("6、Person的Map转json字符串：" + jsonStr);

    }

    /**
     * 将{1 : person1, 2 : person2}的字符串转为map
     * @param mapJsonStr
     * @return
     */
    private Map<String, Person> transMapJsonStrToMap(String mapJsonStr) {

        Gson gson = new Gson();
        // 第2个参数传class对象，但是最张结果要一个List<Person>，我们就算传进去一个List.class，也不能解决最后list的元素是person
        // 用如下方式就可以了，此处用的是匿名内部类，其中的泛型就是你最终想要将json变为的类型。
        Map<String, Person> personList = gson.fromJson(mapJsonStr, new TypeToken<Map<Integer, Person>>(){}.getType());
        writeln("5、Mapjson字符串转Person的Map：" + personList.toString());

        return personList;

    }

    /**
     * 将数组json转换为list<Person>
     * @param jsonstrs
     */
    private List<Person> transArrJsonStrToList(String jsonstrs) {

        Gson gson = new Gson();
        // 第2个参数传class对象，但是最张结果要一个List<Person>，我们就算传进去一个List.class，也不能解决最后list的元素是person
        // 用如下方式就可以了，此处用的是匿名内部类，其中的泛型就是你最终想要将json变为的类型。
        List<Person> personList = gson.fromJson(jsonstrs, new TypeToken<List<Person>>(){}.getType());
        writeln("3、数组json字符串转Person的List：" + personList.toString());

        return personList;
    }

    /**
     * 将java对象转换为json字符串
     * @param jsonstr
     * @return
     */
    private Person transStrToBean(String jsonstr) {
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonstr, Person.class);
        writeln("1、json字符串转对象：" + person.toString());
        return person;
    }


}
