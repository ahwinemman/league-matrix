package com.rukevwe.league.csv;

import com.rukevwe.league.error.InvalidFileException;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvValidator {

    public void validateCsv(List<CSVRecord> records) {

        if (records.isEmpty()) {
            throw new InvalidFileException("Csv file can not be empty.");
        }
    }

}
