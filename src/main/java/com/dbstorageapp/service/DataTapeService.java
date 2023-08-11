package com.dbstorageapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(DataTapeService.class);

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

	// Reads Tape File and persists them directly in DB
	public void saveRecordFromInputFile() {

		logger.info("Data injection service started");

		var dataTapeNames = new ArrayList<String>();

		try {
			InputStream inputStream = getClass().getResourceAsStream("/dataset");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;

			while ((line = reader.readLine()) != null) {

				List<String> dTapeCSV = new ArrayList<String>();
				String dataTapeName = "";

				if (line.length() > 0 && !line.startsWith(",,,,,,,,,,,,")) {
					String[] fields = line.split(";");
					LinkedHashMap<String, String> map = new LinkedHashMap<>();

					for (String pair : fields) {
						String[] kv = pair.split(":");
						if (kv.length == 2) {
							map.put(kv[0], kv[1].strip());
							dTapeCSV.add(kv[1].strip());

						} else if (kv.length == 3) {
							String ts = kv[1] + ":" + kv[2];
							map.put(kv[0], ts.strip());
							dTapeCSV.add(ts.strip());
						} else {
							String ts = kv[1] + ":" + kv[2] + ":" + kv[3];
							map.put(kv[0], ts.strip());
							dTapeCSV.add(ts.strip());
						}
					}

					// converting current line to comma separated pattern
					String[] dTapeFields = dTapeCSV.toArray(new String[0]);

					dataTapeName = map.get("name");
					dataTapeNames.add(map.get("name"));

					try {
						DataTape currentDTape = dataTapeRepository.findDataTapeByName(dataTapeName);

						if (currentDTape != null) {
							if (currentDTape.updateOnlyOnChanges(dTapeFields) == true) {
								logger.info("Updating: " + dataTapeName);
								dataTapeRepository.save(currentDTape);
							}
						} else {
							logger.info("Creating: " + dataTapeName);
							dataTapeRepository.save(new DataTape(HelperClass.toInteger(dTapeFields[0]),
									HelperClass.toTimestamp(dTapeFields[1]), HelperClass.toLong(dTapeFields[2]),
									dTapeFields[3], HelperClass.toTimestamp(dTapeFields[4]),
									HelperClass.toTimestamp(dTapeFields[5]), HelperClass.toTimestamp(dTapeFields[6]),
									HelperClass.toTimestamp(dTapeFields[7]), HelperClass.toTimestamp(dTapeFields[8]),
									HelperClass.toInteger(dTapeFields[9]), dTapeFields[10],
									HelperClass.toInteger(dTapeFields[11]), HelperClass.toTimestamp(dTapeFields[12])));
						}
					} catch (DataAccessException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<DataTape> deleteDataTapes = dataTapeRepository.findDataTapeByNameNotIn(dataTapeNames);
		dataTapeRepository.deleteAll(deleteDataTapes);
		HelperClass.getDeletedTapes(deleteDataTapes);

		logger.info("Data injecting service ended");
		logger.info("[Summary]::Total records in DB: " + getCountOfExistingRecords());
	}

	public void saveRecordFromCSV() {

		logger.info("Data injection service started");

		var dataTapeNames = new ArrayList<String>();

		try {
			InputStream inputStream = getClass().getResourceAsStream("src/main/resources/dataset.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				String dataTapeName = fields[10];
				dataTapeNames.add(fields[10]);

				try {
					DataTape currentDTape = dataTapeRepository.findDataTapeByName(dataTapeName);

					if (currentDTape != null) {
						if (currentDTape.updateOnlyOnChanges(fields) == true) {
							logger.info("Updating: " + dataTapeName);
							dataTapeRepository.save(currentDTape);
						}
					} else {
						logger.info("Creating: " + dataTapeName);
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
		// delete all records from DB that do not exists in given file
		List<DataTape> deleteDataTapes = dataTapeRepository.findDataTapeByNameNotIn(dataTapeNames);
		dataTapeRepository.deleteAll(deleteDataTapes);
		HelperClass.getDeletedTapes(deleteDataTapes);
		logger.info("Data injecting service ended");
		logger.info("[Summary]::Total records in DB: " + getCountOfExistingRecords());
	}

	public long getCountOfExistingRecords() {
		return dataTapeRepository.count();
	}
}
