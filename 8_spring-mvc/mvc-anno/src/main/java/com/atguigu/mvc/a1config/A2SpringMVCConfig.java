package com.atguigu.mvc.a1config;

import com.atguigu.mvc.a4interceptor.FirstInterceptor;
import com.atguigu.mvc.a4interceptor.SecondInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.UrlPathHelper;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于代替SpringMVC.xml配置文件
 * 1、扫描组件；
 * 2、视图解析器；
 * 3、view-controller；
 * 4、default-servlet-handler：默认servlet处理器；
 * 5、mvc注解驱动、及配置<mvc:message-converters>、开启矩阵变量
 * 6、文件上传解析器；
 * 7、异常处理；
 * 8、拦截器。
 * 注意，MVC.xml配置文件的中bean可以用@Bean处理，但是<mvc:default-servlet-hander />这样的就不能通过@Bean等方式去注入，
 * 必须通过实现WebMvcConfigurer接口，此接口里面有所有的<mvc:xxx />的配置对应的方法，重写即可
 */
@Configuration
@ComponentScan(value = {"com.atguigu.mvc"}) // 2、扫描组件
@EnableWebMvc // 5、mvc注解驱动
public class A2SpringMVCConfig implements WebMvcConfigurer {

    /**
     * 3、view-controller配置；
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置handler对应的RequestMapping，和其对应的请求路径(会自动添加前后缀)
        registry.addViewController("/").setViewName("A1Index");
        registry.addViewController("/restFul/toAdd").setViewName("restful/AddUser");
        registry.addViewController("/a3ReqAndResp").setViewName("A3ReqAndResp");
        registry.addViewController("/a4ScopeData").setViewName("A4ScopeData");
        registry.addViewController("/a5ForwardAndRedirect").setViewName("A5ForwardAndRedirect");
        registry.addViewController("/a6FileUpAndDown").setViewName("A6FileUpAndDownLod");
        registry.addViewController("/a7Interceptor").setViewName("A7Interceptor");
        registry.addViewController("/a8Exception").setViewName("A8Exception");

    }

    /**
     * 4、default-servlet-handler：默认servlet处理器；
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 5、配置mvc注解驱动中的 <mvc:message-converters> 部分，处理@ResponseBody返回乱码。
     * 注意不能使用重写configureMessageConverters方法，因为现在这个方法是在mvc已有的几种转换类上进行扩展1或n种转换类，
     * configureMessageConverters是重新配置，在方法中写了几个，就只配置哪几个。
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));

        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.parseMediaType("text/html"));
        list.add(MediaType.parseMediaType("application/json"));
        converter.setSupportedMediaTypes(list);

        /*
         因为mvc默认还有一个StringHttpMessageConverter(ISO-8859-1)，并且在list中靠前的位置，如果不把自定义的
         StringHttpMessageConverter放在前面，就会先使用默认的，会乱码。查看xml配置的源码，就是这样做的。
         org.springframework.web.servlet.config.AnnotationDrivenBeanDefinitionParser.getMessageConverters(Element element, @Nullable Object source, ParserContext context)
         */
        converters.add(0, converter);
        System.out.println(converters.toArray());
    }

    /**
     * 5.2 开启矩阵变量
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper helper = new UrlPathHelper();
        helper.setRemoveSemicolonContent(false); // 不移除分号及其后面到/或url末尾的内容
        configurer.setUrlPathHelper(helper);
    }


    /**
     * 6、文件上传解析器；
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }

    /**
     * 7、异常处理；
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "error"); // 异常的全限定名，发生此异常时跳转的页面
        properties.setProperty("java.lang.NullPointerException", "error");

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setExceptionAttribute("ex"); // 跳转到异常页面时，异常的k，value是此异常
        resolver.setExceptionMappings(properties);

        resolvers.add(resolver);

    }

    /**
     * 8、拦截器，配置拦截器，及其生效路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/*").excludePathPatterns("/user").order(0);
        registry.addInterceptor(new SecondInterceptor()).addPathPatterns("/*").excludePathPatterns("/user").order(1);
    }

    /**
     * 2、视图解析器。
     * @param templateEngine
     * @return
     */
    @Bean
    public ViewResolver viewResolver(@Qualifier(value = "templateEngine") SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    //配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/"); // 设置前缀
        templateResolver.setSuffix(".html"); // 设置后缀
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(@Qualifier(value = "templateResolver") ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

}













