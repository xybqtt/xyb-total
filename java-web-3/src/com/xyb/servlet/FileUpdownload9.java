package com.xyb.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件上传下载的处理，通过 http://ip:port/工程名/jsp/FileUploadAndDownload9.jsp访问
 * 处理步骤：
 *      1、导入commons-fileupload.jar、commons-io-2.9.0.jar去处理文件上传
 *      2、ServletFileUpload类，用于解析上传的数据；
 *      3、FileItem是表单项类，一个表单是一段，也是一个FileItem
 */
public class FileUpdownload9 extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        // 1、取要下载的文件名；
        String fileName = "捕获.PNG";
        String downloadFilepath = File.separator + "upload" + File.separator + fileName;

        // 2、通过响应头设置返回文件的MIME类型
        ServletContext sc = getServletContext();
        // 获取文件的MIME类型
        String mimeType = sc.getMimeType(downloadFilepath);
        write("获取的MIMEType = " + mimeType + "<br/>");
        // 设置响应头的MIME类型
        resp.setContentType(mimeType);
        /**
         *
         * Content-Disposition：设置响应头如何处理下载的文件
         *      attachement：表示附件，表示下载使用；
         *      filename：表示指定下载的文件名。
         *      url编码是将汉字转换为%x%xx的格式，解决不能下载中文文件名的问题
         */
        if(req.getHeader("User-Agent").contains("Firefox"))
            // 如果是火狐浏览器，需要用下面的配置，?UTF-8?表示使用UTF-8，B代表BASE64Encoder解码方式
            resp.setHeader("Content-Disposition", "attachement; filename==?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("UTF-8")) + "?=");
        else
            // ie、chrome这样设置
            resp.setHeader("Content-Disposition", "attachement; filename=" + URLEncoder.encode(fileName,"UTF-8"));


        // 3、读取要下载的文件内容，并回传客户端
        // 通过流读取文件
        InputStream is = sc.getResourceAsStream(downloadFilepath);
        // 获得response的输出流
        OutputStream os = resp.getOutputStream();
        // 将内容复制过去
        IOUtils.copy(is, os);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        // 注意，如果此处把requestBody读出来了，下面就不能获取了，此处注释掉
        // showRequestBody(req, resp);

        // 1、先判断上传的数据是否多段数据(只有多段的数据，才是文件上传的)
        if(!ServletFileUpload.isMultipartContent(req))
            return;

        // 2、创建FileItemFactory工厂实现类
        FileItemFactory fileItemFactory = new DiskFileItemFactory();

        // 3、创建用于解析上传数据的工具类
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

        // 4、解析上传的数据，得到每一个表单项FileItem
        List<FileItem> list = null;
        try {
            list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : list) {
                // 判断是不是普通表单项
                if(fileItem.isFormField()){
                    // 普通表单项
                    String name = fileItem.getName();
                    String value = fileItem.getString("UTF-8");
                    write("普通表单项：" + name + " = " + value + "<br/>");
                } else {
                    // 上传的文件
                    String name = fileItem.getName();
                    String value = fileItem.getString("UTF-8");
                    write("文件项：" + name + " = " + value);

                    String localRealPath = req.getRealPath(File.separator + "upload/" + fileItem.getName() + "<br/>");
                    write("上载文件的路径为：" + localRealPath + "<br/>");
                    File file = new File(localRealPath);
                    if(file.exists())
                        file.delete();
                    fileItem.write(new File(localRealPath));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void showRequestBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // post请求体需要使用流去接收,显示post请求体内容
        ServletInputStream inputStream = req.getInputStream();

        byte[] buffer = new byte[1024000];

        int read = inputStream.read(buffer);
        write("显示post请求体内容<br/>");
        write(new String(buffer, 0, read));


    }
}
