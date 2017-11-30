package com.takeaway.gameofthree.services;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlayerCounter extends Thread {

	@Autowired
	private CyclicBarrier barrier;	
	@Autowired
	private GameService gameService;
	

	@Override
	public void run() {
		try {
			System.out.println("Player "+gameService.getNumberOfPlayers()+ " has joined the game");
			
			barrier.await();		
		} catch (InterruptedException ex) {

		} catch (BrokenBarrierException ex) {

		}
	}

	
	

}