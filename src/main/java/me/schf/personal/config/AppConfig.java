package me.schf.personal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	private String environment;

	private ParameterNames parameterNames = new ParameterNames();

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
		private String apiClientBasePath;
		private String apiClientKeyName;

		public String getApiClientBasePath() {
			return apiClientBasePath;
		}

		public void setApiClientBasePath(String apiClientBasePath) {
			this.apiClientBasePath = apiClientBasePath;
		}

		public String getApiClientKeyName() {
			return apiClientKeyName;
		}

		public void setApiClientKeyName(String apiClientKeyName) {
			this.apiClientKeyName = apiClientKeyName;
		}
	}
}
