package me.schf.personal.config;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Service
public class AwsParameterStoreService {

	private final SsmClient ssmClient;

	public AwsParameterStoreService() {
		this.ssmClient = SsmClient.create();
	}

	public String getParameter(String parameterName) {
		GetParameterRequest request = GetParameterRequest.builder()
				.name(parameterName)
				.withDecryption(true)
				.build();

		return ssmClient.getParameter(request).parameter().value();
	}

}
