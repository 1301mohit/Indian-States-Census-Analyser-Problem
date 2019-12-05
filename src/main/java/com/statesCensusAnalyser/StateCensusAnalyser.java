package com.statesCensusAnalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser<T extends Comparable<T>> {
    private static final String STATE_CENSUS_DATA = "StateCensusData1.csv";

    List<StateCensus> stateCensusList = new ArrayList();

    private int count = 0;

    public List<StateCensus> readCsv(String stateCensusData, Class className, String methodName) throws StateCensusAnalyserException, IllegalAccessException, InvocationTargetException {
        List stateCensusLists = builder(stateCensusData, className);
        if(methodName != "") {
            try {
                stateCensusList = sort(stateCensusLists, methodName);
            } catch (NoSuchMethodException e) {
                throw new StateCensusAnalyserException("Method is not present", StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD);
            }
            writeInJson(stateCensusList);
            return stateCensusList;
        }
        else {
            writeInJson(stateCensusLists);
            return stateCensusLists;
        }
    }

    private void writeInJson(List list) throws StateCensusAnalyserException {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        try {
            FileWriter writer = new FileWriter("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new StateCensusAnalyserException("SAD", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
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

    private List<StateCensus> sort(List<StateCensus> stateCensusLists, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StateCensus temp = null;
        int length = stateCensusLists.size();
        for(int i=0; i<length-1; i++) {
            for(int j=0; j<length-i-1; j++) {
                Class cls1 = stateCensusLists.get(j).getClass();
                Method method1 = cls1.getDeclaredMethod(methodName);
                String value1 = (String)method1.invoke(stateCensusLists.get(j));
                Class cls2 = stateCensusLists.get(j+1).getClass();
                Method method2 = cls2.getDeclaredMethod(methodName);
                String value2 = (String)method2.invoke(stateCensusLists.get(j+1));
                if(methodName.equals("getState")) {
                    if(value1.compareTo(value2) > 0) {
                        temp = stateCensusLists.get(j);
                        stateCensusLists.set(j, stateCensusLists.get(j + 1));
                        stateCensusLists.set(j+1, temp);
                    }
                }
                else{
                    if(Integer.parseInt(value1) < Integer.parseInt(value2)) {
                        temp = stateCensusLists.get(j);
                        stateCensusLists.set(j, stateCensusLists.get(j + 1));
                        stateCensusLists.set(j+1, temp);
                    }
                }
            }
        }
        return stateCensusLists;
    }
}
