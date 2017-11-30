package com.takeaway.gameofthree.services;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	NumberService numberService;
	@Autowired
	private ApplicationContext context;

	private volatile AtomicInteger numberOfPlayers = new AtomicInteger(0);

	public void joinGame() {

		PlayerCounter bean = context.getBean(PlayerCounter.class);
		if (numberOfPlayers.getAndIncrement() != 2) {
			bean.start();
		} else {
			System.out.println("Max number reached");
		}

	}

	public void startGame() {
		// This task will be executed once all thread reaches barrier
		System.out.println("Game Has Started");
		numberService.generateRandomNumber();

	}

	public AtomicInteger getNumberOfPlayers() {
		return numberOfPlayers;
	}

}
