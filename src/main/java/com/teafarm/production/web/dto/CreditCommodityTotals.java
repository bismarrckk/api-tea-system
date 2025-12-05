package com.teafarm.production.web.dto;

public class CreditCommodityTotals {
	private String commodity;
	private Double amount;
	public CreditCommodityTotals() {
		super();
	}
	public CreditCommodityTotals(String commodity, Double amount) {
		super();
		this.commodity = commodity;
		this.amount = amount;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
