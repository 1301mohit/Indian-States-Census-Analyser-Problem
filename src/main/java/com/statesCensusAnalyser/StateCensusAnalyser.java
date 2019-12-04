package com.statesCensusAnalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser {
    private static final String STATE_CENSUS_DATA = "StateCensusData1.csv";

    List<StateCensus> stateCensusLists = new ArrayList();

    private int count = 0;

    public static String readCsv(String stateCensusData, Class className) throws StateCensusAnalyserException {
        List stateCensusLists = builder(stateCensusData, className);
        sort((ArrayList)stateCensusLists);
        for (Object obj: stateCensusLists
             ) {
            System.out.println(obj);
        }
        Gson gson = new Gson();
        String json = gson.toJson(stateCensusLists);
        try {
            FileWriter writer = new FileWriter("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
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
        }
        catch(NoSuchFileException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
        catch (IOException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
        catch (RuntimeException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    private static void sort(ArrayList<StateCensus> stateCensusLists) {
        StateCensus temp = null;
        int length = stateCensusLists.size();
        for(int i=0; i<length-1; i++) {
            for(int j=0; j<length-i-1; j++) {
                if(stateCensusLists.get(j).getPopulation().compareTo(stateCensusLists.get(j+1).getPopulation()) > 0) {
                    temp = stateCensusLists.get(j);
                    stateCensusLists.set(j, stateCensusLists.get(j + 1));
                    stateCensusLists.set(j, temp);
                }
            }
        }
    }

    private static void sortByArea(List stateCensusLists) {
          Comparator<StateCensus> c = (s1,s2) -> s2.getAreaInSqKm().compareTo(s1.getAreaInSqKm());
          stateCensusLists.sort(c);
    }

    private static void sortByPopulationDensity(List stateCensusLists) {
        Comparator<StateCensus> c = (s1,s2) -> s2.getAreaInSqKm().compareTo(s1.getAreaInSqKm());
        stateCensusLists.sort(c);
    }

    private static void sortByState(List stateCensusLists) {
        stateCensusLists.sort(Comparator.comparing(StateCensus::getState));
    }

    private static void sortByPopulation(List stateCensusLists) {
        Comparator<StateCensus> c = (s1,s2) -> s2.getPopulation().compareTo(s1.getPopulation());
        stateCensusLists.sort(c);
    }

}
