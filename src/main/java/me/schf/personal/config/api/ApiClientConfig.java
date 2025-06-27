package me.schf.personal.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.api.client.api.PostHeadlinesApi;
import me.schf.api.client.api.PostsApi;
import me.schf.api.client.invoker.ApiClient;
import me.schf.personal.config.AppConfig;
import me.schf.personal.config.aws.AwsConfig.ParameterRetriever;

@Configuration
public class ApiClientConfig {

	@Bean
	ApiClient apiClient(AppConfig appConfig, ParameterRetriever awsParameterRetriever) {
		ApiClient client = new ApiClient();
		
		String xApiKey = awsParameterRetriever.getParameter(appConfig.getParameterNames().getApiClientKeyName());
		String uriBasePath = awsParameterRetriever.getParameter(appConfig.getParameterNames().getApiClientBasePathName());
		
		client.setBasePath(uriBasePath);
		client.addDefaultHeader("X-API-Key", xApiKey);
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
