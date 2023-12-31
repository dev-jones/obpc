package com.devjones.obpc.domain;

public class ParserThread implements Runnable {

	private String product;
	
	public ParserThread(String product) {
		this.product = product;
	}
	
	@Override
	public void run() {
		LowestPriceCash lpc = new LowestPriceCash();
		
		int rt = lpc.parse(product);
//		result += rt;
	}

}
