package com.assessment.invoices.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.invoices.domain.Invoice;
import com.assessment.invoices.domain.ListItem;
import com.assessment.invoices.repositories.InvoiceRepository;
import com.assessment.invoices.repositories.ListItemRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ListItemRepository listItemRepository;

	public Invoice addInvoice(Invoice invoiceDto) throws Exception {
		if (invoiceDto == null) {
			throw new Exception("No invoice found");
		}

		List<ListItem> listItems = invoiceDto.getListItems();
		Invoice invoice = invoiceRepository.save(invoiceDto);

		if (listItems != null) {
			for (ListItem listItem : listItems) {
				if (listItem != null) {
					listItem.setInvoice(invoice);
					listItemRepository.save(listItem);
				}
			}
		}

		Optional<Invoice> option = invoiceRepository.findById(invoice.getId());
		return option.get();
	}

	public List<Invoice> getInvoices() {

		Iterable<Invoice> iterator = invoiceRepository.findAll();

		List<Invoice> invoices = new ArrayList<>();
		iterator.forEach(invoices::add);
		return invoices;
	}

	public Invoice getInvoice(long invoiceId) {

		Optional<Invoice> option = invoiceRepository.findById(invoiceId);
		if (option.isPresent()) {
			return option.get();
		} else {
			return null;
		}

	}

}
