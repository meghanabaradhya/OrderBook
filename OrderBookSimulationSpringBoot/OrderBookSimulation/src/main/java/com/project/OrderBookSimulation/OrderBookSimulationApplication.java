package com.project.OrderBookSimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class OrderBookSimulationApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderBookSimulationApplication.class, args);
	}

	@Bean
	public OrderBook orderBook(){
		return new OrderBook();
	}

}
