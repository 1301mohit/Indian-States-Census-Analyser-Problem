package com.statesCensusAnalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser {
    private static final String STATE_CENSUS_DATA = "StateCensusData1.csv";

    List<StateCensus> stateCensusLists = new ArrayList();

    private int count = 0;

    public static String readCsv(String stateCensusData, Class className) throws StateCensusAnalyserException {
        List stateCensusLists = builder(stateCensusData, className);
        stateCensusLists.sort(Comparator.comparing(StateCensus::getState));
        Gson gson = new Gson();
        String json = gson.toJson(stateCensusLists);
        try {
            FileWriter writer = new FileWriter("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stateCensusLists.size() == 29)
            return "HAPPY";
        else
            return "SAD";
    }

    private static <T> List<T> builder(String stateCensusData, T className) throws StateCensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(stateCensusData));
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType((Class) className)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<T> stateCensusList = csvToBean.parse();
            return stateCensusList;
        } catch (IOException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }
}
