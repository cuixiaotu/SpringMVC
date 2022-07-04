package com.xiaotu.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


//web过程的初始化类 用来代替web.xml
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
    *  指定spring配置类
    *  @return
    * */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ SpringConfig.class };
    }

    /**
     *  指定springMVC配置类
     *  @return
     * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfig.class };
    }

    /**
     *  指定DispatcherServlet的映射规则 即url-pattern
     *  @return
     * */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
    *  注册过滤器
    * */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter,hiddenHttpMethodFilter};
    }
}
