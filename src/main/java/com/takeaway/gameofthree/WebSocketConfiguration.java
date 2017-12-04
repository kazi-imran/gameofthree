package com.takeaway.gameofthree;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Component
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	public static final String MESSAGE_PREFIX = "/topic";	
	

	@Override
	@CrossOrigin
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/payroll");
	}

	@Override
	@CrossOrigin
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.enableSimpleBroker("/queue", "/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
}