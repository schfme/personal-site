package me.schf.personal.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import me.schf.personal.config.AppConfig;
import me.schf.personal.config.AppConfig.ParameterNames;
import me.schf.personal.config.aws.AwsConfig.ParameterRetriever;

@Configuration
public class DatabaseConfig {
	
	@FunctionalInterface
	public interface DatabaseConnection {
		public String getConnectionUri();
	}
	
	public record MongoConnection(	    
			String cluster,
		    String clusterHost,
		    String dbName,
		    String dbPassword,
		    String dbUser
		   ) implements DatabaseConnection {

		@Override
		public String getConnectionUri() {
	        return "mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority&appName=%s"
	                .formatted(dbUser, dbPassword, clusterHost, cluster);
		}

	}

	@Bean
	DatabaseConnection mongoConnection(ParameterRetriever awsParameterRetriever, AppConfig appConfig) {
		ParameterNames paramNames = appConfig.getParameterNames();
	    String clusterNameParam = paramNames.getClusterName();
	    String hostParam = paramNames.getHost();
	    String dbNameParam = paramNames.getDatabaseName();
	    String serviceNameParam = paramNames.getServiceName();
	    String servicePasswordParam = paramNames.getServicePassword();

	    String clusterName = awsParameterRetriever.getParameter(clusterNameParam);
	    String host = awsParameterRetriever.getParameter(hostParam);
	    String dbName = awsParameterRetriever.getParameter(dbNameParam);
	    String serviceName = awsParameterRetriever.getParameter(serviceNameParam);
	    String servicePassword = awsParameterRetriever.getParameter(servicePasswordParam);

	    return new MongoConnection(
	    		clusterName, 
	    		host, 
	    		dbName, 
	    		servicePassword, 
	    		serviceName
	    		);
	}
	
	@Configuration
	public static class MongoConfig extends AbstractMongoClientConfiguration {

		private final DatabaseConnection mongoConnection;

		public MongoConfig(DatabaseConnection mongoConnection) {
			super();
			this.mongoConnection = mongoConnection;
		}

		@Override
		protected String getDatabaseName() {
			if (mongoConnection instanceof MongoConnection mongoconnection) {
				return mongoconnection.dbName();
			}
			throw new IllegalStateException(
					"Unexpected state. Given DatabaseConnection was not of type MongoConnection.");
		}
		
		@Override
		public MongoClient mongoClient() {
			return MongoClients.create(mongoConnection.getConnectionUri());
		}

	}
}
