package com.assessment.invoices.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "list_items")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ListItem {

	@Id
	@GeneratedValue
	private Long id;

	private Long quantity;
	private String description;
	private BigDecimal unitPrice;

	@ManyToOne
	@JoinColumn(name = "invoice")
	private Invoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Transient
	public BigDecimal getLineItemTotal() {
		BigDecimal lineItemTotal = new BigDecimal(0);
		if (unitPrice != null && quantity != null) {
			lineItemTotal = lineItemTotal.add(unitPrice).multiply(new BigDecimal(quantity));
		}
		return lineItemTotal.setScale(2, RoundingMode.HALF_UP);
	}
}
