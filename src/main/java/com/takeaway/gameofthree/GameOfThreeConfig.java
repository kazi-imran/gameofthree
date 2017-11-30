package com.takeaway.gameofthree;

import java.util.concurrent.CyclicBarrier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.takeaway.gameofthree.services.GameService;

@Configuration
public class GameOfThreeConfig {


	@Bean
	public CyclicBarrier cyclicBarrier(GameService gameService)
	{
		
		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {

				System.out.println("Game Started");
				gameService.startGame();
			}
		});
		
		return barrier;
	}
	
	
}
