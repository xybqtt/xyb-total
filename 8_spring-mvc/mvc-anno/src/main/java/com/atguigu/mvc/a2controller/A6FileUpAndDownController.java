package com.atguigu.mvc.a2controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * ResponseEntity完成文件的上载、下载。
 */
@Controller
public class A6FileUpAndDownController {


    /**
     * 文件下载
     * @param session
     * @return
     */
    @RequestMapping(value = "/fileUpAndDown/fileDown")
    public ResponseEntity<byte[]> fileDown(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及设置下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    /**
     * 文件上载
     * @param session
     * @return
     */
    /**
     * 多文件上传。
     * @param photos 多文件上传用MultipartFile[]接收，单文件用MultipartFile接收。
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fileUpAndDown/fileUp")
    @ResponseBody
    public String fileUp(@RequestPart("photos") MultipartFile[] photos, HttpSession session) throws IOException {

        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("/static/photo");

        for(MultipartFile photo : photos) {
            //获取上传的文件的文件名
            String fileName = photo.getOriginalFilename();
            //处理文件重名问题
            String hzName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + hzName;


            File file = new File(photoPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String finalPath = photoPath + File.separator + fileName;
            //实现上传功能
            photo.transferTo(new File(finalPath));
        }
        return "文件上传成功，路径为" + photoPath;
    }

}









