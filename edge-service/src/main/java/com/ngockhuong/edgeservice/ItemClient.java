package com.ngockhuong.edgeservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

//The Name is listed inside item-catalog Project => Resources => application.properties.
//The /items can be called in browser within this projects session. Its not callable from other Eureka -clients or -servers
@FeignClient("item-catelog-service")
interface ItemClient {
    @GetMapping("/items")
    Resources<Item> readItems();
}
