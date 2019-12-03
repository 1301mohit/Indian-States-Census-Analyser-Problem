package com.statesCensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
    private static final String STATE_CENSUS_DATA = "StateCensusData1.csv";

    private int count = 0;

    public String readCsv(String stateCensusData, Class className) throws StateCensusAnalyserException {
        Iterator iterator = this.builder(stateCensusData, className);
        while(iterator.hasNext()) {
            iterator.next();
            count++;
        }
        if(count == 29)
            return  "HAPPY";
        else
            return "SAD";
    }

    private <T> Iterator<T> builder(String stateCensusData, T className) throws StateCensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(stateCensusData));
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType((Class)className)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator iterator = csvToBean.iterator();
            return iterator;
        }
        catch (IOException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }
}
