package com.assessment.invoices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.invoices.domain.Invoice;
import com.assessment.invoices.services.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value = "invoices", method = RequestMethod.POST)
	public Invoice addInvoice(@RequestBody Invoice invoiceDto) throws Exception {

		return invoiceService.addInvoice(invoiceDto);
	}

	@RequestMapping(value = "invoices", method = RequestMethod.GET)
	public List<Invoice> viewAllInvoices() throws Exception {

		return invoiceService.getInvoices();
	}

	@RequestMapping(value = "/invoices/{invoiceId}", method = RequestMethod.GET)
	public Invoice viewInvoice(@PathVariable("invoiceId") long invoiceId) throws Exception {

		return invoiceService.getInvoice(invoiceId);
	}
}
