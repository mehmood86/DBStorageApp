package com.dbstorageapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dbstorageapp.service.DataTapeService;

@SpringBootApplication
public class DbStorageAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DbStorageAppApplication.class, args);
	}

	@Autowired
	private DataTapeService dService;

	public void run(String... args) throws Exception {

		// 1: Automated process
		dService.saveRecordFromInputFile();

		// 2: Manual process,
		// dService.saveRecordFromCSV();
	}

}
