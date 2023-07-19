package com.dbstorageapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dbstorageapp.model.DataTape;

public interface DataTapeRepository extends CrudRepository<DataTape, Long> {

	DataTape findDataTapeByName(String dataTapeName);
	List<DataTape> findDataTapeByNameNotIn(ArrayList<String> names);
}
