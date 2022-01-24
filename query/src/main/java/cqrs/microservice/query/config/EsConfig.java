package cqrs.microservice.query.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.lang.NonNull;


@Configuration
@EnableElasticsearchRepositories(basePackages = "cqrs.microservice.query.repositories")
@ComponentScan(basePackages = "cqrs.microservice.query")
public class EsConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private String esPort;

    @Override
    @Bean
    @NonNull
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo(esHost + ":" + esPort)
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
