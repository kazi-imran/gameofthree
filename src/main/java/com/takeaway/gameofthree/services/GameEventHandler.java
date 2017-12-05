package com.takeaway.gameofthree.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GameEventHandler
{
	@Autowired
	private SimpMessagingTemplate messaging;
	@Autowired
	private RestTemplate restTemplate;
	
	@EventListener
	public void sendNumber(NumberSendEvent e) {
		restTemplate.postForObject("http://localhost:8080/player2", e.getNumber().intValue(),Integer.class);
		messaging.convertAndSend("/app/playertwo",e.getNumber().intValue());
	}
}
