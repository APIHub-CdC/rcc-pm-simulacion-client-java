package com.cdc.apihub.mx.RCCPM.simulacion.client;

public class Configuration {
	private static ApiClient defaultApiClient = new ApiClient();

	public static ApiClient getDefaultApiClient() {
		return defaultApiClient;
	}

	public static void setDefaultApiClient(ApiClient apiClient) {
		defaultApiClient = apiClient;
	}
}
