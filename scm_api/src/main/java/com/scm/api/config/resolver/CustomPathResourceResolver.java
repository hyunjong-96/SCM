package com.scm.api.config.resolver;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

public class CustomPathResourceResolver extends PathResourceResolver {

    @Override
    public Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource requestResource = location.createRelative(resourcePath);

        return requestResource.exists() && requestResource.isReadable()
                ? requestResource : new ClassPathResource("/static/index.html");
    }
}
