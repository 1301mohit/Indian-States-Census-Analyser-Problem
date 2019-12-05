package com.bridgelabz.stateCensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.statesCensusAnalyser.StateCensus;
import com.statesCensusAnalyser.StateCensusAnalyser;
import com.statesCensusAnalyser.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StateCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA = "/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.csv";
    private static final String STATE_CODE = "StateCode1.csv";

    @Test
    public void readFile_ReturnList_ComapreSize() throws StateCensusAnalyserException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        List<StateCensus> list = stateCensusAnalyser.readCsv( STATE_CENSUS_DATA, StateCensus.class, "");
        Assert.assertEquals(29, list.size());
    }

    @Test
    public void readFile_IfFileNameIncorrect_ReturnException() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "");
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfFileTypeIncorrect_ReturnException() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "");
        //    Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfDelimiterIncorrect_ReturnException() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "");
        //    Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfCSVHeaderIncorrect_ReturnException() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "");
         //   Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_SortByStates_ReturnMaximumValueOfState() throws InvocationTargetException, IllegalAccessException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        List<StateCensus> list = null;
        try {
            list = stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "getState");
            Assert.assertEquals("Andhra Pradesh", list.get(0).getState());
        } catch (StateCensusAnalyserException e) {
            System.out.println(e);
        }
    }

    @Test
    public void readFile_SortByStatePopulation_ReturnMaximumPopulation() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        List<StateCensus> list = null;
        try {
            list = stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "getPopulation");
            Assert.assertEquals("199812341", list.get(0).getPopulation());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile_SortByDensity_ReturnMaximumDensity() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        List<StateCensus> list = null;
        try {
            list = stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "getDensityPerSqKm");
            Assert.assertEquals("1102", list.get(0).getDensityPerSqKm());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile_SortByArea_ReturnMaximumArea() throws IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        List<StateCensus> list = null;
        try {
            list = stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "getAreaInSqKm");
            Assert.assertEquals("342239", list.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile_IfMethodNotPresent_ReturnException() throws InvocationTargetException, IllegalAccessException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv(STATE_CENSUS_DATA, StateCensus.class, "acbcd");
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }
}
