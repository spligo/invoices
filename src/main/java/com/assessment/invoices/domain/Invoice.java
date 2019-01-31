package com.assessment.invoices.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "invoices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {

	@Id
	@GeneratedValue
	private Long id;

	private String client;
	private Long vatRate;
	private Date invoiceDate;

	@OneToMany(mappedBy = "invoice")
	private List<ListItem> listItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public List<ListItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<ListItem> listItems) {
		this.listItems = listItems;
	}

	@Transient
	public BigDecimal getSubTotal() {
		BigDecimal subTotal = new BigDecimal(0);
		if (listItems != null) {
			for (ListItem listItem : listItems) {
				if (listItem != null) {
					subTotal = subTotal.add(listItem.getLineItemTotal());
				}
			}
		}
		return subTotal.setScale(2, RoundingMode.HALF_UP);
	}

	@Transient
	public BigDecimal getVat() {
		BigDecimal vatTotal = new BigDecimal(0);
		if (vatRate != null) {
			double percentage = ((double) vatRate) / 100;
			vatTotal = getSubTotal().multiply(new BigDecimal(percentage));
		}
		return vatTotal.setScale(2, RoundingMode.HALF_UP);
	}

	@Transient
	public BigDecimal getTotal() {
		BigDecimal total = getSubTotal().add(getVat());
		return total.setScale(2, RoundingMode.HALF_UP);
	}

}
