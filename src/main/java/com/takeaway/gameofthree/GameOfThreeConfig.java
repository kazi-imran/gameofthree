package com.takeaway.gameofthree;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.takeaway.gameofthree.services.GameService;

@Configuration
public class GameOfThreeConfig
{
	
	@Bean
	public CyclicBarrier cyclicBarrier(GameService gameService)
	{
		
		return new CyclicBarrier(2, new Runnable()
		{
			@Override
			public void run()
			{
				
				System.out.println("Game Started");
				gameService.startGame();
			}
		});
		
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		// Do any additional configuration here
		return builder.build();
	}
	
	@Bean
	public ExecutorService transactionPostExecutorService()
	{
		final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("transactionPostExecutor-%d").setDaemon(true).build();
		ExecutorService es = Executors.newFixedThreadPool(2, threadFactory);
		return es;
	}
	
	/**
	 * Dedicated Thread Modeling to handle GET Statistics
	 */
//	@Bean()
//	public ExecutorService transactionStatisticsExecutorService()
//	{
//		final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("transactionStatisticsExecutor-%d").setDaemon(true).build();
//		ExecutorService es = Executors.newFixedThreadPool(2, threadFactory);
//		return es;
//	}
}
