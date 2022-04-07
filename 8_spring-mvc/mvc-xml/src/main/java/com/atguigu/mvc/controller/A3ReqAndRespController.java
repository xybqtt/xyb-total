package com.atguigu.mvc.controller;

import com.atguigu.mvc.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 测试获取Req信息；
 * 测试使用@ResponseBody返回"字符串、对象(自动转换为json)"信息。
 */
@Controller
public class A3ReqAndRespController {


    /**
     * 通过id删除，注意http只有get、post请求，为了让DispatcherServlet接收到的req为DELETE请求，必须在拦截器(hiddenHttpMethodFilter为已有实现的拦截器)进行如下操作：
     *      1、首先请求必须是Post，将Request赋值给另一个自定义的ARequest；
     *      2、request.getParameter("_method")，获取这个值，如果是DELETE，则将ARequest.method = RequestMethod.DELETE；
     *      3、将ARequest传给DispatcherServlet，会去匹配method = DELETE的方法，其它同理。
     *
     *      注意，超链接是不能发送post请求的，必须使用form表单发送，即单击超链接时，取消其本身的超链接功能，在写个方法，点击超链接时，提交表单。
     *
     * @return
     */
    @RequestMapping(value = "/3restful/restTest", method = RequestMethod.GET)
    @ResponseBody
    public String restFulGet() {
        return "这是RESTful的GET请求";
    }

    /**
     * RESTful的 delete 请求
     * @return
     */
    @RequestMapping(value = "/3restful/restTest", method = RequestMethod.DELETE)
    @ResponseBody
    public String restFulDEL() {
        return "这是RESTful的DELETE请求";
    }

    /**
     * RESTful的post请求
     * @return
     */
    @RequestMapping(value = "/3restful/restTest", method = RequestMethod.POST)
    @ResponseBody
    public String restFulPost() {
        return "这是RESTful的POST请求";
    }


    /**
     * RESTful的 put 请求
     * @return
     */
    @RequestMapping(value = "/3restful/restTest", method = RequestMethod.PUT)
    @ResponseBody
    public String restFulPut() {
        return "这是RESTful的PUT请求";
    }









    /**
     * 使用RequestEntity接收request信息
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/RequestEntity", method = RequestMethod.POST)
    @ResponseBody
    public String getReqEntity(RequestEntity<String> requestEntity) {

        StringBuilder sb = new StringBuilder("");
        sb.append("RequestEntity<String> reqEntity" + requestEntity + "<br/><br/>");
        sb.append("通过参数类型为'RequestEntity'，将req信息封装到此类中，并通过此类获取header、body信息-------------------<br/>");
        sb.append("requestEntity.getHeaders()-------------------" + requestEntity.getHeaders() + "<br/><br/>");
        sb.append("requestEntity.getUrl()-------------------" + requestEntity.getUrl() + "<br/><br/>");
        sb.append("requestEntity.getMethod()-------------------" + requestEntity.getMethod() + "<br/><br/>");
        sb.append("requestEntity.getBody()-------------------" + requestEntity.getBody() + "<br/><br/>");
        sb.append("RequestEntity<String> requestEntity-------------------<" + requestEntity.toString().replaceFirst("<", " ") + "<br/><br/><br/><br/>");

        return sb.toString();
    }

    /**
     * 测试@RequestHeader注解，获取确定参数的请求头信息对应字符串，或所有请求头信息的map。
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/RequestHeader", method = RequestMethod.POST)
    @ResponseBody
    public String getReqHeader(@RequestHeader Map<String,String> headerMap, @RequestHeader("HOST") String host) {

        StringBuilder sb = new StringBuilder("");
        sb.append("header.host信息：" + host + "<br/><br/>");
        sb.append("所有header信息对应的map：" + headerMap + "<br/><br/>");
        return sb.toString();
    }

    /**
     * 前后台参数名与方法形参名一致，即可赋值
     * @param username 前端参数名与方法参数名一致，即可赋值
     * @param hobby 前端参数名与方法参数名一致，即可赋值
     * @param user 前端参数名与类型属性名一致，即可赋值
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/ParamName", method = RequestMethod.POST)
    @ResponseBody
    public String getParamName(String username, String[] hobby, User user) {

        StringBuilder sb = new StringBuilder("");
        sb.append("String username(参数名与param一致)：" + username + "<br/><br/>");
        sb.append("String[] hobby(参数名与param一致)：" + Arrays.toString(hobby) + "<br/><br/>");
        sb.append("User{username, hobby}：" + user + "<br/><br/>");
        return sb.toString();
    }

    /**
     * @RequestParam注解用法，一般用于get请求，或其它类型请求但"Content-Type: application/x-www-form-urlencoded"格式的数据。
     * @param paramMap 使用@RequestParam获取所有请求参数map
     * @param pusername 使用@RequestParam获取参数名为"username"对应的value
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/RequestParam", method = RequestMethod.POST)
    @ResponseBody
    public String getReqParam(@RequestParam MultiValueMap<String,String> paramMap, @RequestParam(value = "username", required = true, defaultValue = "aa") String pusername) {

        StringBuilder sb = new StringBuilder("");
        sb.append("@RequestParam(\"username\")：" + pusername + "<br/><br/>");
        sb.append("@RequestParam，获取所有param信息：" + paramMap + "<br/><br/>");
        return sb.toString();
    }

    /**
     * @RequestBody注解使用，
     * @param reqBody 获取post请求的请求体字符串，或get请求的请求参数字符串。
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/RequestBody", method = RequestMethod.POST)
    @ResponseBody
    public String getReqBody(@RequestBody String reqBody) {

        StringBuilder sb = new StringBuilder("");
        sb.append("@RequestBody：" + reqBody + "<br/><br/>");
        return sb.toString();
    }

    /**
     * 从cookie中取值
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/CookieValue", method = RequestMethod.POST)
    @ResponseBody
    public String getCookieValue(@CookieValue(value = "JSESSIONID", required = true, defaultValue = "没有cookie") String sessionId) {
        StringBuilder sb = new StringBuilder("");
        sb.append("@CookieValue(\"JSESSIONID\")：" + sessionId + "<br/><br/>");
        return sb.toString();
    }



    /**
     *                 <input type="submit" value="@RequestAttribute">获取request域属性<br/>
     *                 <input type="submit" value="@PathVariable">获取rest风格的路径变量<br/>
     *                 <input type="submit" value="@MatrixVariable">获取矩阵变量<br/>
     */
    @RequestMapping(value = "/3restful/reqInfo/RequestAttribute", method = RequestMethod.POST)
    public String addOneReqAttr(HttpServletRequest req) {
        req.setAttribute("field", "属性1");
        return "forward:/3restful/reqInfo/showReqAttr";
    }

    /**
     * 从Request中取值
     * @return
     */
    @RequestMapping(value = "/3restful/reqInfo/showReqAttr", method = RequestMethod.POST)
    @ResponseBody
    public String getReqAttr(@RequestAttribute("field") String field) {
        StringBuilder sb = new StringBuilder("");
        sb.append("@RequestAttribute(\"field\")：" + field + "<br/><br/>");
        return sb.toString();
    }





    /**
     * rest风格
     * /path1;a=1,2;b=1;b=张三/path2=4;c=1;d=2
     * @return
     */
    @RequestMapping(value = "/3restful/rest/{path1}/{path2}", method = RequestMethod.GET)
    @ResponseBody
    public String getReqAttr(@PathVariable("path1") String path1,
                             @PathVariable("path2") String path2,
                             @PathVariable Map<String, String> map,
                             @MatrixVariable(pathVar = "path1") MultiValueMap path1Map,
                             @MatrixVariable(pathVar = "path1", value = "a") String[] path1aValue
                             ) {
        StringBuilder sb = new StringBuilder("");
        sb.append("@PathVariable(\"path1\")：" + path1 + "(没有对应值，仅有路径)<br/><br/>");
        sb.append("@PathVariable(\"path2\")：" + path2 + "(有对应值)<br/><br/>");
        sb.append("@PathVariable获取所有路径变量map：" + map + "<br/><br/>");
        sb.append("@MatrixVariable(pathVar = \"path1\")(路径分号后的kv对)：" + path1Map + "<br/><br/>");
        sb.append("@MatrixVariable(pathVar = \"path1\", value = \"a\")(路径后的矩阵变量中，k=a对应的value值)：" + Arrays.toString(path1aValue) + "<br/><br/>");
        return sb.toString();
    }

    /**
     * 测试使用"@ResponseBody"将java对象转换为json对象或json数组对象，返回前端
     * @return
     */
    @RequestMapping(value = "/3restful/respObject")
    @ResponseBody
    public User testRequestBodyAnno() {
        System.out.println("直接返回java对象");
        return new User(1001, "张三", "123", null);
    }

    /**
     * 测试使用"@ResponseBody"直接返回字符串，而不是页面显示在前端
     * @return
     */
    @RequestMapping(value = "/3restful/respStr")
    @ResponseBody
    public String testRequestBodyStr() {
        System.out.println("直接返回java对象");
        return "返回字符串";
    }

    /**
     * 测试使用"@ResponseBody"直接返回字符串，而不是页面显示在前端
     * @return
     */
    @RequestMapping(value = "/3restful/respEntity")
    @ResponseBody
    public ResponseEntity testRespEntity() {

        User user = new User(1001, "张三", "123", null);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        return responseEntity;
    }


    /**
     * 测试ajax
     * @return
     */
    @RequestMapping(value = "/3restful/ajax")
    @ResponseBody
    public String testAjax(String username) {
        return "hello，ajax，" + username;
    }


}









