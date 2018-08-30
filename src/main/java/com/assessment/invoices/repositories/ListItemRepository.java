package com.assessment.invoices.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assessment.invoices.domain.ListItem;

public interface ListItemRepository extends CrudRepository<ListItem, Long> {

}
