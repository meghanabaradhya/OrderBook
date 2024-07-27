package com.project.OrderBookSimulation;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderBookController {

    @Autowired
    private OrderBook orderBook;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("buyOrders", orderBook.getBuyOrders());
        model.addAttribute("sellOrders", orderBook.getSellOrders());
        return "index";
    }

    @PostMapping("/addOrder")
    public String addOrder(
            @RequestParam String id,
            @RequestParam String type,
            @RequestParam String orderType,
            @RequestParam double price,
            @RequestParam int quantity) {

        Order order = new Order(id, type, orderType, price, quantity);
        orderBook.addOrder(order);
        return "redirect:/";
    }
}
