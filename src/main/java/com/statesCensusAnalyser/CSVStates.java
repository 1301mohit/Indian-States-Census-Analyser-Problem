package com.statesCensusAnalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
//    SrNo,State,Name,TIN,StateCode,
    @CsvBindByName
    private int SrNo;

    @CsvBindByName
    private String State;

    @CsvBindByName
    private String Name;

    @CsvBindByName
    private String TIN;

    @CsvBindByName
    private String StateCode;

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int srNo) {
        SrNo = srNo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    @Override
    public String toString() {
        return "CSVStates{" +
                "SrNo=" + SrNo +
                ", State='" + State + '\'' +
                ", Name='" + Name + '\'' +
                ", TIN='" + TIN + '\'' +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }

}
