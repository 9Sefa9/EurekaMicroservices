package com.ngockhuong.edgeservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class GoodItemApiAdapterRestController {
    private final ItemClient itemClient;

    public GoodItemApiAdapterRestController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    public Collection<Item> fallback() {
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/top-brands")
    public Collection<Item> goodItems() {
        return itemClient.readItems()
                .getContent()
                .stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(Item item) {
        return !item.getName().equals("Nike") &&
                !item.getName().equals("Adidas") &&
                !item.getName().equals("Reebok");
    }
}
