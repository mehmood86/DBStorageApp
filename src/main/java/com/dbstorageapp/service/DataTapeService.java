package com.dbstorageapp.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.dbstorageapp.helperMethods.HelperClass;
import com.dbstorageapp.model.DataTape;
import com.dbstorageapp.repository.DataTapeRepository;

@Service
public class DataTapeService {

	@Autowired
	DataTapeRepository dataTapeRepository;

	// Get all records
	public List<DataTape> getAllDataTape() {
		List<DataTape> dataTape = new ArrayList<>();
		dataTapeRepository.findAll().forEach(dataTape::add);
		return dataTape;
	}

	// Get single record with id
	public DataTape getDataTapeById(long id) {
		return dataTapeRepository.findById(id).get();
	}

	// Save a record
	public void save(DataTape dataTape) {
		dataTapeRepository.save(dataTape);
	}

	// Delete a specific record
	public void delete(long id) {
		dataTapeRepository.deleteById(id);
	}

	// Updating a record
	public void update(DataTape dataTape, int dataTapeid) {
		dataTapeRepository.save(dataTape);
	}

	// Reads a .txt File and persists KeyValue pairs in DB
	// optionally, a csv is also created
	public void saveRecordFromInputFile() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/dataset.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			List<DataTape> records = new ArrayList<>();

			FileWriter writer = new FileWriter("dataset.csv", false);

			String[] items = { "access_cnt", "accessed_at", "bytes", "campaign", "closed_at", "created_at",
					"deleted_at", "eol_at", "expired_at", "length", "name", "run_number", "updated_at" };
			// Add header to csv file
			for (String item : items) {
				writer.write(item + ",");
			}
			writer.write("\n");

			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(";");
				LinkedHashMap<String, String> map = new LinkedHashMap<>();

				for (String pair : fields) {
					String[] kv = pair.split(":");
					if (kv.length <= 2) {
						map.put(kv[0], kv[1].strip());
					} else if (kv.length == 3) {
						String ts = kv[1] + ":" + kv[2];
						map.put(kv[0], ts.strip());
					} else {
						String ts = kv[1] + ":" + kv[2] + ":" + kv[3];
						map.put(kv[0], ts.strip());
					}
				}

				// Add records to csv file
				for (String item : items) {
					writer.write(map.get(item) + ",");
				}
				writer.write("\n");

				records.add(new DataTape(HelperClass.toInteger(map.get("access_cnt")),
						HelperClass.toTimestamp(map.get("accessed_at")), HelperClass.toLong(map.get("bytes")),
						map.get("campaign"), HelperClass.toTimestamp(map.get("closed_at")),
						HelperClass.toTimestamp(map.get("created_at")), HelperClass.toTimestamp(map.get("deleted_at")),
						HelperClass.toTimestamp(map.get("eol_at")), HelperClass.toTimestamp(map.get("expired_at")),
						HelperClass.toInteger(map.get("length")), map.get("name"),
						HelperClass.toInteger(map.get("run_number")), HelperClass.toTimestamp(map.get("updated_at"))));
			}
			dataTapeRepository.saveAll(records);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read from .csv and persist them directly in DB
	public void saveRecordFromCSV() {
		var dataTapeNames = new ArrayList<String>();
		try {
			InputStream inputStream = getClass().getResourceAsStream("/dataset.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				String dataTapeName = fields[10];
				dataTapeNames.add(fields[10]);
				try {
					DataTape currentDTape = dataTapeRepository.findDataTapeByName(dataTapeName);
					if (currentDTape != null) {						
						currentDTape.setAccess_cnt(HelperClass.toInteger(fields[0]));
						currentDTape.setAccessed_at(HelperClass.toTimestamp(fields[1]));
						currentDTape.setBytes(HelperClass.toLong(fields[2]));
						currentDTape.setCampaign(fields[3]);
						currentDTape.setClosed_at(HelperClass.toTimestamp(fields[4]));
						currentDTape.setCreated_at(HelperClass.toTimestamp(fields[5]));
						currentDTape.setDeleted_at(HelperClass.toTimestamp(fields[6]));
						currentDTape.setEol_at(HelperClass.toTimestamp(fields[7]));
						currentDTape.setExpired_at(HelperClass.toTimestamp(fields[8]));
						currentDTape.setLength(HelperClass.toInteger(fields[9]));
						currentDTape.setName(dataTapeName);
						currentDTape.setRun_number(HelperClass.toInteger(fields[11]));
						currentDTape.setUpdated_at(HelperClass.toTimestamp(fields[12]));
						System.out.println("Updating: " + dataTapeName);
						dataTapeRepository.save(currentDTape);
					} else {
						System.out.println("Creating: " + dataTapeName);
						dataTapeRepository.save(new DataTape(HelperClass.toInteger(fields[0]),
								HelperClass.toTimestamp(fields[1]), HelperClass.toLong(fields[2]), fields[3],
								HelperClass.toTimestamp(fields[4]), HelperClass.toTimestamp(fields[5]),
								HelperClass.toTimestamp(fields[6]), HelperClass.toTimestamp(fields[7]),
								HelperClass.toTimestamp(fields[8]), HelperClass.toInteger(fields[9]), fields[10],
								HelperClass.toInteger(fields[11]), HelperClass.toTimestamp(fields[12])));
					}

				} catch (DataAccessException e) {
					System.out.println(e.getMessage());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// delete entries		
		List<DataTape> deleteDataTapes = dataTapeRepository.findDataTapeByNameNotIn(dataTapeNames);
		dataTapeRepository.deleteAll(deleteDataTapes);
		
		HelperClass.getDeletedTapes(deleteDataTapes);

	}
}
