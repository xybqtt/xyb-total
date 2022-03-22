package com.atguigu.mvc.a1config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 使用注解方式替代web.xml文件，需要创建如下内容
 * 1、DispatcherServlet配置：
 *      1.1 引入Spring配置文件，即引入@Configuration注解的类；
 *      1.2 配置url-pattern；
 *      1。3 配置SpringMVC相关配置。
 * 2、配置2个过滤器：CharacterEncodingFilter、HiddenHttpMethodFilter。
 */
public class A1WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置Spring的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{A3SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置类，配置视图解析器、拦截器等。
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{A2SpringMVCConfig.class};
    }

    /**
     * 配置DispatcherServlet的url-pattern
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 配置过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        // 配置CharacterEncodingFilter过滤器，必须配置在首位，因为要设置编码。
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true, true);

        // 配置HiddenHttpMethodFilter过滤器，用于RESTful风格
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();

        return new Filter[]{encodingFilter, methodFilter};
    }


}
