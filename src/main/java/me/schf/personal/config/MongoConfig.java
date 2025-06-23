package me.schf.personal.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import me.schf.personal.data.TagReadConverter;
import me.schf.personal.data.TagWriteConverter;
import me.schf.personal.data.ZonedDateTimeReadConverter;
import me.schf.personal.data.ZonedDateTimeWriteConverter;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	private final MongoConnection mongoConnection;

	public MongoConfig(AwsParameterStoreService parameterStoreService) {
		this.mongoConnection = new MongoConnection(
			parameterStoreService.getParameter("mongo-schfme-cluster"),
			parameterStoreService.getParameter("mongo-schfme-cluster-host"),
			parameterStoreService.getParameter("mongo-schfme-db"),
			parameterStoreService.getParameter("mongo-schfme-db-password"),
			parameterStoreService.getParameter("mongo-schfme-db-user")
		);
	}

	@Override
	public MongoCustomConversions customConversions() {
		return new MongoCustomConversions(
			List.of(
				new ZonedDateTimeReadConverter(),
				new ZonedDateTimeWriteConverter(),
				new TagReadConverter(),
				new TagWriteConverter()
			)
		);
	}

	@Override
	protected String getDatabaseName() {
		return mongoConnection.dbName();
	}

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create(mongoConnection.buildConnectionString());
	}
}
