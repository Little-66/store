package com.cy.store.Config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      HandlerInterceptor interceptor = new LoginInterceptor();
      List list = new ArrayList();
      list.add("/bootstrap3/**");
      list.add("/css/**");
      list.add("/images/**");
      list.add("/js/**");
      list.add("/web/login.html");
      list.add("/web/product.html");
      list.add("/web/index.html");
      list.add("/User/register");
      list.add("/User/login");
      list.add("/District/**");
      list.add("/products/**");
      list.add("");

//                                           拦截的路径                 除了那些路径
      registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);

   }
}
