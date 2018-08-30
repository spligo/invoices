package com.assessment.invoices.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assessment.invoices.domain.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
