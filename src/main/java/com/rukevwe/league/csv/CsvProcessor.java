package com.rukevwe.league.csv;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvProcessor {

    List<CSVRecord> parseCsv(MultipartFile file);
}
