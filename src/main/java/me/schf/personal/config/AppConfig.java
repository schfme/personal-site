package me.schf.personal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	private String environment;
	private ParameterNames parameterNames;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public ParameterNames getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(ParameterNames parameterNames) {
		this.parameterNames = parameterNames;
	}

	public static class ParameterNames {
		private String apiClientBasePathName;
		private String apiClientKeyName;
		private String apiKeysPath;
		private String clusterName;
		private String databaseName;
		private String host;
		private String serviceName;
		private String servicePassword;
		private String emailAddressName;

		public String getApiClientBasePathName() {
			return apiClientBasePathName;
		}

		public void setApiClientBasePathName(String apiClientBasePathName) {
			this.apiClientBasePathName = apiClientBasePathName;
		}

		public String getApiClientKeyName() {
			return apiClientKeyName;
		}

		public void setApiClientKeyName(String apiClientKeyName) {
			this.apiClientKeyName = apiClientKeyName;
		}

		public String getApiKeysPath() {
			return apiKeysPath;
		}

		public void setApiKeysPath(String apiKeysPath) {
			this.apiKeysPath = apiKeysPath;
		}

		public String getClusterName() {
			return clusterName;
		}

		public void setClusterName(String clusterName) {
			this.clusterName = clusterName;
		}

		public String getDatabaseName() {
			return databaseName;
		}

		public void setDatabaseName(String databaseName) {
			this.databaseName = databaseName;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getServicePassword() {
			return servicePassword;
		}

		public void setServicePassword(String servicePassword) {
			this.servicePassword = servicePassword;
		}

		public String getEmailAddressName() {
			return emailAddressName;
		}

		public void setEmailAddressName(String emailAddressName) {
			this.emailAddressName = emailAddressName;
		}

	}
}
