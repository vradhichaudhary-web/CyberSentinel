package com.cybersentinel.cyberthreatplatform.dto;

public class JobCheckResponse {

    private String riskLevel;
    private int scamScore;
    private String result;

    public JobCheckResponse() {
    }

    public JobCheckResponse(String riskLevel, int scamScore, String result) {
        this.riskLevel = riskLevel;
        this.scamScore = scamScore;
        this.result = result;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getScamScore() {
        return scamScore;
    }

    public void setScamScore(int scamScore) {
        this.scamScore = scamScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}