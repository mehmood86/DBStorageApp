package com.dbstorageapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbstorageapp.model.DataTape;
import com.dbstorageapp.service.DataTapeService;

@RestController
public class DataTapeController {

	@Autowired
	DataTapeService dataTapeService;

	@GetMapping("/dtapes")
	private List<DataTape> getAllDataTapes() {		
		return dataTapeService.getAllDataTape();
	}

	@GetMapping("/dtape/{id}")
	private DataTape getDataTapes(@PathVariable("id") int dtapeId) {
		return dataTapeService.getDataTapeById(dtapeId);
	}

	@PostMapping("/dtape")
	private long saveBook(@RequestBody DataTape dataTape) {
		dataTapeService.save(dataTape);
		return dataTape.getId();
	}

	@PutMapping("/dtape")
	private DataTape update(@RequestBody DataTape dataTape) {
		dataTapeService.save(dataTape);
		return dataTape;
	}

	@DeleteMapping("/delete/{id}")
	private void deleteDataTape(@PathVariable("id") int dtapeId) {
		dataTapeService.delete(dtapeId);
	}

}
