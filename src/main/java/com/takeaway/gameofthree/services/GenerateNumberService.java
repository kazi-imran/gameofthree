package com.takeaway.gameofthree.services;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class GenerateNumberService implements Callable<AtomicInteger>
{
	
	private AtomicInteger generateNumber()
	{
		return new AtomicInteger(5);
	}
	
	@Override
	public AtomicInteger call() throws Exception
	{
		return generateNumber();
		
	}
	
}
