package com.statesCensusAnalyser;

public class StateCensusAnalyserException extends Exception {

    public ExceptionType type;

    public enum ExceptionType {
        NO_SUCH_FILE, NO_SUCH_METHOD
    }

    public StateCensusAnalyserException(String message) {
        super(message);
    }

    public StateCensusAnalyserException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public StateCensusAnalyserException() {
    }

    public StateCensusAnalyserException( String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
