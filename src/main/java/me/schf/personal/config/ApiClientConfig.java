package me.schf.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.api.client.api.PostHeadlinesApi;
import me.schf.api.client.api.PostsApi;
import me.schf.api.client.invoker.ApiClient;

@Configuration
public class ApiClientConfig {

	@Bean
	ApiClient apiClient(AppConfig appConfig, AwsParameterStoreService awsParameterStoreService) {
		ApiClient client = new ApiClient();
		client.setBasePath(appConfig.getParameterNames().getApiClientBasePath());
		client.addDefaultHeader("X-API-Key",
				awsParameterStoreService.getParameter(appConfig.getParameterNames().getApiClientKeyName()));
		return client;
	}

	@Bean
	PostHeadlinesApi postHeadlinesApi(ApiClient apiClient) {
		return new PostHeadlinesApi(apiClient);
	}

	@Bean
	PostsApi postsApi(ApiClient apiClient) {
		return new PostsApi(apiClient);
	}

}
