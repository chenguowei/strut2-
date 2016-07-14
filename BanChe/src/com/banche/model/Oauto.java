package com.banche.model;

public class Oauto {
	private Long l;
	private Orderauto o;
	
	public Oauto() {}
	
	public Oauto(Long l, Orderauto o) {
		super();
		this.l = l;
		this.o = o;
	}
	public Long getL() {
		return l;
	}
	public void setL(Long l) {
		this.l = l;
	}
	public Orderauto getO() {
		return o;
	}
	public void setO(Orderauto o) {
		this.o = o;
	}
	
}
