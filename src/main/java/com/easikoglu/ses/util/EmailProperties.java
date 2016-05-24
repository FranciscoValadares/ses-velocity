package com.easikoglu.ses.util;

import java.util.List;

public class EmailProperties {
	private String fullName;
	private String toEmail;
	private String actionUrl;
	
	// for order summary mail
	private String orderId;
	private String orderDateText;
	private String invoiceAddressText;
	private String shipmentAddressText;
	private List<String> items;
	private String totalAmount;


	//for delivery tracking code mail
	private String trackingCode;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDateText() {
		return orderDateText;
	}

	public void setOrderDateText(String orderDateText) {
		this.orderDateText = orderDateText;
	}

	public String getInvoiceAddressText() {
		return invoiceAddressText;
	}

	public void setInvoiceAddressText(String invoiceAddressText) {
		this.invoiceAddressText = invoiceAddressText;
	}

	public String getShipmentAddressText() {
		return shipmentAddressText;
	}

	public void setShipmentAddressText(String shipmentAddressText) {
		this.shipmentAddressText = shipmentAddressText;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
}
