package com.takeaway.gameofthree.web;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameOfThreeContoller {
	@SubscribeMapping("/playertwo")
	public void playerTwoReceivesANumber(String message)
	{
		System.out.println(message);
		
	}
}
