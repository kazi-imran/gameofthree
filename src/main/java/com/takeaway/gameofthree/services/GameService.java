package com.takeaway.gameofthree.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {

	@Autowired
	GenerateNumberService numberService;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private SimpMessagingTemplate messaging;
	
	@Autowired
	private SimpMessageSendingOperations messaging2;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ExecutorService player1;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private GenerateNumberService service;

	private volatile AtomicInteger numberOfPlayers = new AtomicInteger(0);

	public void joinGame() {

		PlayerCounter bean = context.getBean(PlayerCounter.class);
		if (numberOfPlayers.getAndIncrement() <= 2 && numberOfPlayers.getAndIncrement() >= 0) {
			bean.start();
		} else {
			System.out.println("Max number reached");
		}

	}
	
	@Autowired
	public void startGame() {
		// This task will be executed once all thread reaches barrier
		System.out.println("Game Has Started");
		Future<AtomicInteger> result = player1.submit(service);
		if( result.isDone())
		{
			try
			{
				this.applicationEventPublisher.publishEvent(new NumberSendEvent(result.get()));
			}
			catch (InterruptedException | ExecutionException e)
			{
				
			}
		}
		}
	
	public void handlePlayerTwoRecievedNumber(Integer receivedNumber)
	{
		System.out.println("player 2 received number"+receivedNumber);
	}

	public AtomicInteger getNumberOfPlayers() {
		return numberOfPlayers;
	}

}
