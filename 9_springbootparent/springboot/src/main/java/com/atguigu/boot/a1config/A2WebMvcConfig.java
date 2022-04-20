package com.atguigu.boot.a1config;

import com.atguigu.boot.a4converter.GuiguMessageConverter;
import com.atguigu.boot.a4interceptor.FirstInterceptor;
import com.atguigu.boot.a4interceptor.SecondInterceptor;
import com.atguigu.boot.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.*;


/**
 * 3种自定义webmvc配置方式：
 *
 * @
 */
@Configuration(proxyBeanMethods = false)
public class A2WebMvcConfig implements WebMvcConfigurer {


    /**
     * 配置拦截器，并配置拦截路径和不拦截路径。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor()).
                addPathPatterns("/**"). // /**也会拦截静态资源，所以需要在下面进行排除
                excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "");

        registry.addInterceptor(new SecondInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "");
    }

    /**
     * 自定义内容协商策略
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //Map<String, MediaType> mediaTypes
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        mediaTypes.put("gg", MediaType.parseMediaType("application/x-guigu"));
        //指定支持解析哪些参数对应的哪些媒体类型
        ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
//                parameterStrategy.setParameterName("ff");

        HeaderContentNegotiationStrategy headeStrategy = new HeaderContentNegotiationStrategy();

        configurer.strategies(Arrays.asList(parameterStrategy, headeStrategy));
    }

    /**
     * 添加自定义的消息转换器
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new GuiguMessageConverter());
    }

    /**
     * 使矩阵变量生效
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // 不移除；后面的内容。矩阵变量功能就可以生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, User>() {

            @Override
            public User convert(String source) {
                // 啊猫,3
                if (!StringUtils.isEmpty(source)) {
                    User user = new User();
                    String[] split = source.split(",");
                    user.setUsername(split[0]);
                    user.setPassword(split[1]);
                    return user;
                }
                return null;
            }
        });
    }

}





