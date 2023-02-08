package com.rukevwe.league.service;

import com.rukevwe.league.csv.CsvProcessor;
import com.rukevwe.league.csv.CsvValidator;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.StringJoiner;

@Service
public class MatrixService {

    private CsvProcessor csvProcessor;

    private CsvValidator csvValidator;

    @Autowired
    public MatrixService(CsvProcessor csvProcessor, CsvValidator csvValidator) {

        this.csvProcessor = csvProcessor;
        this.csvValidator = csvValidator;
    }

    public String echo(MultipartFile file) {

        List<CSVRecord> records = csvProcessor.parseCsv(file);
        int numOfRows = records.size();
        csvValidator.validateCsv(records);

        StringJoiner parentStringJoiner = new StringJoiner("\n");
        for (CSVRecord record : records) {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (int i = 0; i < numOfRows; i++) {
                stringJoiner.add(record.get(i));
            }
            parentStringJoiner.add(stringJoiner.toString());
        }
        return parentStringJoiner.toString();
    }

    public String invert(MultipartFile file) {

        List<CSVRecord> records = csvProcessor.parseCsv(file);
        int numOfRows = records.size();
        csvValidator.validateCsv(records);

        StringJoiner parentStringJoiner = new StringJoiner("\n");
        String[][] stringArray = new String[numOfRows][numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (int j = 0; j < numOfRows; j++) {
                stringArray[i][j] = records.get(j).get(i);
                stringJoiner.add(stringArray[i][j]);
            }
            parentStringJoiner.add(stringJoiner.toString());
        }
        return parentStringJoiner.toString();
    }

    public String flatten(MultipartFile file) {

        List<CSVRecord> records = csvProcessor.parseCsv(file);
        int numOfRows = records.size();
        csvValidator.validateCsv(records);

        StringJoiner stringJoiner = new StringJoiner(",");
        for (CSVRecord record : records) {
            for (int i = 0; i < numOfRows; i++) {
                stringJoiner.add(record.get(i));
            }
        }
        return stringJoiner.toString();
    }

    public String sum(MultipartFile file) {

        List<CSVRecord> records = csvProcessor.parseCsv(file);
        int numOfRows = records.size();
        csvValidator.validateCsv(records);

        long sumOfMatrixInts = 0;
        for (CSVRecord record : records) {
            for (int i = 0; i < numOfRows; i++) {
                sumOfMatrixInts += Integer.parseInt(record.get(i));
            }
        }
        return String.valueOf(sumOfMatrixInts);
    }

    public String multiply(MultipartFile file) {

        List<CSVRecord> records = csvProcessor.parseCsv(file);
        int numOfRows = records.size();
        csvValidator.validateCsv(records);

        long multipleOfMatrixInts = 1;
        for (CSVRecord record : records) {
            for (int i = 0; i < numOfRows; i++) {
                multipleOfMatrixInts *= Integer.parseInt(record.get(i));
            }
        }
        return String.valueOf(multipleOfMatrixInts);
    }
}
