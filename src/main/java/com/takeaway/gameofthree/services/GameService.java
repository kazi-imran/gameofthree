package com.takeaway.gameofthree.services;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {

	@Autowired
	NumberService numberService;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private SimpMessagingTemplate messaging;
	
	@Autowired
	private SimpMessageSendingOperations messaging2;
	
	@Autowired
	private RestTemplate restTemplate;

	private volatile AtomicInteger numberOfPlayers = new AtomicInteger(0);

	public void joinGame() {

		PlayerCounter bean = context.getBean(PlayerCounter.class);
		if (numberOfPlayers.getAndIncrement() <= 2 && numberOfPlayers.getAndIncrement() >= 0) {
			bean.start();
		} else {
			System.out.println("Max number reached");
		}

	}

	public void startGame() {
		// This task will be executed once all thread reaches barrier
		System.out.println("Game Has Started");
		int randomNumber = numberService.generateRandomNumber();
		restTemplate.postForObject("http://localhost:8080/player2", "Just A Message",String.class);
		messaging.convertAndSend("/app/playertwo", randomNumber);

	}

	public AtomicInteger getNumberOfPlayers() {
		return numberOfPlayers;
	}

}
