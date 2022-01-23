package cqrs.microservice.query.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import cqrs.microservice.query.models.Product;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collection;


@Configuration
public class SerializeConfig {
    @Bean
    XStream xstream(){
        Class<?>[] classes = new Class[] { Product.class };
        XStream xstream = new XStream();
        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow any type from the same package
        xstream.addPermission(NullPermission.NULL);
        xstream.allowTypes(classes);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypesByRegExp(new String[] {
                ".*"
        });

        return xstream;
    }

    @Bean
    @Primary
    public Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }
}
