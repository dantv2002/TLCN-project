package com.medical.backendsystem.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.medical.backendsystem.services.CryptographyService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class DatabaseConfiguration extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String database;

    private final CryptographyService cryptographyRSAService;
    public DatabaseConfiguration(@Autowired CryptographyService cryptographyRSAService){
        this.cryptographyRSAService = cryptographyRSAService;
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        try {
            this.uri = this.cryptographyRSAService.Decrypt(this.uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MongoClients.create(uri + "/" + database + "?retryWrites=true&w=majority");
    }
}
