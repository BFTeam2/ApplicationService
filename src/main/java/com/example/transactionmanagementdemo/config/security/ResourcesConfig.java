package com.example.transactionmanagementdemo.config.security;

//import com.example.authentication.common.interceptor.SellerAuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Static resource path configuration
 * interceptor configuration
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** Static resource configuration */  // The ones starting with "static" will be searched in the "static" folder under the classpath.
        registry.addResourceHandler( "/static/**")
                .addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SellerAuthorityInterceptor())
//                .excludePathPatterns("/api/login", "/api/register")
//                .addPathPatterns("/product/delete","/product/add","/product/changeRetailPrice","/product/changeWholesalePrice", "/product/{id}/stock-quantity");
//    }
}
