package com.thecodealchemist;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryClient {
    private final RestClient restClient;
    public InventoryClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String isInStock(String sku) {
        return restClient.get().uri("/api/inventory/{sku}", sku)
                .retrieve()
                .body(new ParameterizedTypeReference<String>() {});
    }
}
