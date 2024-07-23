package vn.bookstore.Book_Store_BackEnd.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import vn.bookstore.Book_Store_BackEnd.entity.Category;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {
    private String url = "http://localhost:8080";
    @Autowired
    private EntityManager entityManager;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] disablieMethods ={
                HttpMethod.PUT,
                HttpMethod.DELETE,
                HttpMethod.PATCH,
                HttpMethod.POST
        };
        //export ids
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
       // disableHttpMethod(Category.class,config,disablieMethods);
    }
    private void disableHttpMethod(Class c, RepositoryRestConfiguration config, HttpMethod[] methods) {
        config.getExposureConfiguration().forDomainType(c)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(methods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}
