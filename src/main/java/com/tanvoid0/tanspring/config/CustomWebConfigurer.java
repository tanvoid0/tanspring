package com.tanvoid0.tanspring.config;

import com.tanvoid0.tanspring.interceptor.InterceptLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CustomWebConfigurer implements WebMvcConfigurer {
  @Autowired
  private InterceptLog logInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logInterceptor);
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    exposeDirectory("files", registry);
  }

  private void exposeDirectory(String dirName, final ResourceHandlerRegistry registry) {
    final Path uploadDir = Paths.get(dirName);
    String uploadPath = uploadDir.toFile().getAbsolutePath();

    if (dirName.startsWith("../")) {
      dirName = dirName.replace("../", "");
    }
    registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
  }

// TODO: Cors config disable for now
//  @Override
//  public void addCorsMappings(final CorsRegistry registry) {
//    registry.addMapping("/**");
//  }
}
