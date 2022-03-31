package com.atguigu.mvc.a2controller;

import com.atguigu.mvc.entity.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 测试Controller获取http请求参数
 */
@Controller
public class A3ParamsController {

    /**
     * 各种方式获取req信息，及req中请求体的kv信息。
     * 通过MVC的参数注解：@RequestParam将请求参数和控制器方法的形参创建映射关系。
     * @RequestParam(value = "username", required = true, defaultValue = "aa")
     *      value：指定为形参赋值的请求参数的参数名；
     *      required：设置是否必须传输此请求参数，默认值为true；
     *          若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter ‘xxx’ is not present；若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
     *      defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
     *  @RequestHeader、@CookieValue与@RequestParam用法一样，不过一个是获取请求头中的参数值，一个是获取cookie中的参数值。
     *
     *  通过MVC获取参数，在控制器方法的形参位置，设置类的属性和请求参数同名的类型形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的类的属性。
     *
     *  通过MVC获取参数，在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参。
     */
    @RequestMapping(value = "/paramGet/getAllType")
    @ResponseBody // 返回值类型为String，则直接返回字符串，而不是跳转页面
    public String testAllTypeToGetReqInfo(
            // 通过servlet方式
            HttpServletRequest req,

            // 通过mvc方式获取请求体信息
            RequestEntity<String> requestEntity,

            // 通过@RequestHeader、@RequestBody 获取请求头(单个param)、请求体信息
            @RequestHeader(value = "HOST") String host, @RequestBody String body,

            // 参数名如果与param一致，则可以直接赋值。如果类型的属性名与param一致，也可以直接赋值给类的属性。
            String username, String password, String[] hobby, User user,

            // 通过将@RequestParam(param)，将param.v与注解对应的形参绑定。或@RequestParam，可获取所有param的kv(用MultiValueMap接收)。
            @RequestParam(value = "username", required = true, defaultValue = "aa") String rpUserName,
            @RequestParam(value = "password", required = true, defaultValue = "aa") String rpPassword,
            @RequestParam(value = "hobby", required = true, defaultValue = "aa") String[] rpHobby,
            @RequestParam MultiValueMap<String, Object> requestParamMap
    ) {

        StringBuilder sb = new StringBuilder("");
        sb.append("1、通过原始servletApi获取req、header、body、param-------------------具体查看java-web。<br/><br/>");


        sb.append("2、通过参数类型为'RequestEntity'，将req信息封装到此类中，并通过此类获取header、body信息-------------------<br/>");
        sb.append("2.1、requestEntity.getHeaders()-------------------" + requestEntity.getHeaders() + "<br/><br/>");
        sb.append("2.2、requestEntity.getUrl()-------------------" + requestEntity.getUrl() + "<br/><br/>");
        sb.append("2.3、requestEntity.getMethod()-------------------" + requestEntity.getMethod() + "<br/><br/>");
        sb.append("2.4、requestEntity.getBody()-------------------" + requestEntity.getBody() + "<br/><br/>");
        sb.append("2.5、RequestEntity<String> requestEntity-------------------<" + requestEntity.toString().replaceFirst("<", " ") + "<br/><br/><br/><br/>");



        sb.append("3、通过@RequestHeader(\"headerParam\")获取 请求头 字符串-------------------<br/>");
        sb.append("3.1、@RequestHeader(\"HOST\")-------------------" + host + "<br/><br/>");

        sb.append("4、通过@RequestBody获取 请求体 字符串-------------------<br/>");
        sb.append("4.1、@RequestBody String body-------------------" + body + "<br/><br/><br/><br/>");



        sb.append("5、通过将'方法参数名'与'param'设置为一样，直接将param对应的v赋值给方法对应的形参-------------------<br/>");
        sb.append("5.1、String username-------------------" + username + "<br/>");
        sb.append("5.2、String password-------------------" + password + "<br/>");
        sb.append("5.3、String[] hobby-------------------" + Arrays.toString(hobby) + "<br/><br/>");

        sb.append("6、通过将'类的属性名'与'param'设置为一样，直接将param对应的v赋值给方法对应的类型形参的属性-------------------<br/>");
        sb.append("6.1、user-------------------" + user + "<br/><br/><br/><br/>");



        sb.append("7、通过将'@RequestParam(\"param\")'与'前端param'设置为一样，直接将param对应的v赋值给此注解对应的形参-------------------<br/>");
        sb.append("7.1、@RequestParam(\"username\") String username-------------------" + rpUserName + "<br/>");
        sb.append("7.2、@RequestParam(\"password\") String password-------------------" + rpPassword + "<br/>");
        sb.append("7.3、@RequestParam(\"hobby\") String[] hobby-------------------" + Arrays.toString(rpHobby) + "<br/><br/>");

        sb.append("8、通过将'@RequestParam'获取前端所有的param及对应的v，赋值给MultiValueMap-------------------<br/>");
        sb.append("8.1、@RequestParam MultiValueMap map-------------------" + requestParamMap + "<br/><br/><br/><br/>");


        System.out.println(sb);
        return sb.toString();
    }

}









