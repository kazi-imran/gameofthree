package com.takeaway.gameofthree.services;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberSendEvent
{
	private AtomicInteger number;
	
	public NumberSendEvent(AtomicInteger number)
	{
		this.number = number;
		
	}

	public AtomicInteger getNumber()
	{
		return number;
	}
	
	
}
