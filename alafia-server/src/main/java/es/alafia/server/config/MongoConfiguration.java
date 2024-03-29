package es.alafia.server.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfiguration {

    @Value("${mongodb.url}")
    private String mongoDbUrl;

    @Value("${mongodb.db-name}")
    private String mongoDbName;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoDbUrl);
        MongoClient mongoClient = (MongoClient) mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDbName);
        return mongoTemplate;
    }
}
