package com.thecodealchemist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class OrderServiceApplication {
    static void main() {
        SpringApplication.run(OrderServiceApplication.class);
    }

    @RestController
    @RequestMapping("/api/orders")
    static class OrderController {

        private final Map<String, Order> ORDERS_DB = new HashMap<>();

        @GetMapping
        public Map<String, Order> orders() {
            log.info("Getting orders from DB");
            return ORDERS_DB;
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
