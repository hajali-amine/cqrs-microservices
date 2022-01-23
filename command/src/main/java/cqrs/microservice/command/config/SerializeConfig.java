package cqrs.microservice.command.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class SerializeConfig {
    @Bean
    XStream xstream(){
        XStream xstream = new XStream();
        xstream.addPermission(NoTypePermission.NONE);
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
