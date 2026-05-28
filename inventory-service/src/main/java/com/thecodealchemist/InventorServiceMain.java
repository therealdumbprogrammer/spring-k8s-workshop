package com.thecodealchemist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InventorServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(InventorServiceMain.class, args);
    }

    @RestController
    @RequestMapping("/api/inventory")
    static class InventoryController {

        @Value("${POD_NAME:local}")
        private String podName;

        @GetMapping("/{sku}")
        public String isInStock(@PathVariable String sku) {
            return sku + " In Stock | Inventory handled by " + podName;
        }
    }
}
