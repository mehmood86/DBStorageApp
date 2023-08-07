package com.dbstorageapp.helperMethods;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dbstorageapp.model.DataTape;

public class HelperClass {
	// Convert string to Timestamp
	public static Timestamp toTimestamp(String input) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = dateFormat.parse(input);			
			return new java.sql.Timestamp(date.getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	// Convert string to Integer
	public static Integer toInteger(String input) {
		int number = 0;
		try {
			if (input != null || input != "None") {
				number = Integer.parseInt(input);
			}

		} catch (NumberFormatException e) {
			number = 0;
		}
		return number;
	}

	// Convert string to Long
	public static Long toLong(String input) {
		long number = 0;
		try {
			number = Long.parseLong(input);
		} catch (NumberFormatException e) {
			return number;
		}
		return number;
	}

	public static void getDeletedTapes(List<DataTape> dataTapesToBeDeleted) {
		if (!dataTapesToBeDeleted.isEmpty()) {
			for (DataTape dtape : dataTapesToBeDeleted) {
				System.out.println("Deleted: " + dtape.getName());
			}
		}
	}

}
