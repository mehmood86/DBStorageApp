package com.dbstorageapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbstorageapp.model.DataTape;

public interface DataTapeRepository extends CrudRepository<DataTape, Long> {
}
