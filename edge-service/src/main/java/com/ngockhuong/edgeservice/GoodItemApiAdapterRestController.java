package com.ngockhuong.edgeservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoodItemApiAdapterRestController {
    private final ItemClient itemClient;

    public GoodItemApiAdapterRestController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    @HystrixCommand(fallbackMethod = "goodItems2")
    @GetMapping("/top-brands")
    public Collection<Item> goodItems() {
        ArrayList<Item> a = new ArrayList<>();
        for(Item i:this.itemClient.readItems()){
            a.add(i);
        }

        return a;
    }
    private Collection<Item> goodItems2() {
        System.out.println("fallback Method was called!");
        return null;
    }
    /*return itemClient.readItems()
                .getContent()
                .stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());


    }


    /*private boolean isGreat(Item item) {
        return !item.getName().equals("Nike") &&
                !item.getName().equals("Adidas") &&
                !item.getName().equals("Reebok");
    }*/


}
