package com.atguigu.boot.a1config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * web项目配置：
 *  监听器、过滤器
 */
public class A1JavaWebConfig  extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置Spring的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * 指定SpringMVC的配置类，配置视图解析器、拦截器等。
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{A2WebMvcConfig.class};
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

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }


}
