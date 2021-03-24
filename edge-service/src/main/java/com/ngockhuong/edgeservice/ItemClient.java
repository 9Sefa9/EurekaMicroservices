package com.ngockhuong.edgeservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("item-catelog-service")
interface ItemClient {
    @GetMapping("/items")
    Resources<Item> readItems();
}
