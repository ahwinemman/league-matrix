package com.rukevwe.league.csv.impl;

import com.rukevwe.league.csv.CsvProcessor;
import com.rukevwe.league.error.InvalidFileException;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CsvProcessorImpl implements CsvProcessor {

    @Override
    public List<CSVRecord> parseCsv(MultipartFile file) {

        try {
            InputStreamReader input = new InputStreamReader(file.getInputStream());
            return IteratorUtils.toList(CSVFormat.DEFAULT.parse(input).iterator());
        } catch (IOException ex) {
            throw new InvalidFileException("Error parsing input file", ex);
        }
    }
}
