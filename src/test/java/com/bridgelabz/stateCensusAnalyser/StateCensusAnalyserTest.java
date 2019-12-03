package com.bridgelabz.stateCensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.statesCensusAnalyser.StateCensus;
import com.statesCensusAnalyser.StateCensusAnalyser;
import com.statesCensusAnalyser.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA = "/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.csv";
    private static final String STATE_CODE = "StateCode1.csv";

    @Test
    public void readFile_IfCorrect_ReturnHappy() throws StateCensusAnalyserException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        String result = stateCensusAnalyser.readCsv( STATE_CENSUS_DATA, StateCensus.class);
        Assert.assertEquals("HAPPY", result);
    }

    @Test
    public void readFile_IfFileNameIncorrect_ReturnException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.csv", StateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfFileTypeIncorrect_ReturnException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusData.txt", StateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfDelimiterIncorrect_ReturnException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusDataDelimiterIncorrect.csv", StateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void readFile_IfCSVHeaderIncorrect_ReturnException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.readCsv("/home/mohit/Indian-States-Census-Analyser-Problem/src/main/resources/StateCensusDataCSVHeaderIncorrect.csv", StateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }

    }
}
