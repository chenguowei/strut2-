package com.banche.model;

public class OrderState {
	private Long l;
	private Automold a;
	
	public OrderState() {
	}
	
	public OrderState(Long l, Automold a) {
		super();
		this.l = l;
		this.a = a;
	}



	public Long getL() {
		return l;
	}
	public void setL(Long l) {
		this.l = l;
	}
	public Automold getA() {
		return a;
	}
	public void setA(Automold a) {
		this.a = a;
	}
	
}
