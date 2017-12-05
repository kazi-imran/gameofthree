package com.takeaway.gameofthree.web;

import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.gameofthree.services.GameService;

@RestController
public class RootController {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private CyclicBarrier cyclicBarrier;
	
	@RequestMapping("/startgame")
	public void startGame()
	{
		gameService.joinGame();
		
	}
	
	@RequestMapping("/stopgame")
	public void stopGame()
	{
		cyclicBarrier.reset();
		
	}	
	
	@RequestMapping(value="/player1",method = RequestMethod.POST)
	public void playerOneReceivesMessage(@RequestBody int number)
	{
		System.out.println("Player One received Message" + number);
		
	}
	

	@RequestMapping(value="/player2",method = RequestMethod.POST)
	public void playerTwoReceivesMessage(@RequestBody int number)
	{
		gameService.handlePlayerTwoRecievedNumber(number);
		
	}	
	

}
