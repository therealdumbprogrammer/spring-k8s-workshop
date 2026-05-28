package com.thecodealchemist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class OrderServiceApplication {
    static void main() {
        SpringApplication.run(OrderServiceApplication.class);
    }

    @Bean
    public RestClient restClient(RestClient.Builder builder,
                                 @Value("${inventory.service.url}") String url) {
        return builder.baseUrl(url).build();
    }

    @RestController
    @RequestMapping("/api/orders")
    static class OrderController {
        private static final Logger log = LoggerFactory.getLogger(OrderController.class);

        private final InventoryClient inventoryClient;

        private final Map<String, Order> ORDERS_DB = new HashMap<>();

        @Value("${app.message}")
        private String message;

        @Value("${app.apiToken}")
        private String apiToken;

        @Value("${POD_NAME:local}")
        private String podName;

        OrderController(InventoryClient inventoryClient) {
            this.inventoryClient = inventoryClient;
        }

        @GetMapping
        public Map<String, Order> orders() {
            log.info("Getting orders from DB");
            return ORDERS_DB;
        }

        @GetMapping("/config")
        public  Map<String, String> config() {
            return Map.of("message", message,  "apiToken", apiToken);
        }

        @GetMapping("/{sku}")
        public String isInStock(@PathVariable String sku) {
            return "Order handled by " + podName + " | " + inventoryClient.isInStock(sku);
        }

        @PostMapping
        public Order createOrder(@RequestBody OrderRequest orderRequest) {
            log.info("Creating order {}", orderRequest);

            ORDERS_DB.put(orderRequest.orderId(), map(orderRequest));
            return ORDERS_DB.get(orderRequest.orderId());
        }
    }

    record OrderRequest(String orderId, Integer quantity) {}

    record Order(String orderId, Integer quantity) {}

    private static Order map(OrderRequest orderRequest) {
        return new Order(orderRequest.orderId(), orderRequest.quantity());
    }
}
