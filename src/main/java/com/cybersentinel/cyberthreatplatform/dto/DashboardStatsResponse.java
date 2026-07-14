package com.cybersentinel.cyberthreatplatform.dto;

public class DashboardStatsResponse {

    private long totalScans;
    private long fakeJobScans;
    private long urlScans;
    private long highRisk;
    private long mediumRisk;
    private long lowRisk;

    public DashboardStatsResponse(long totalScans, long fakeJobScans,
                                  long urlScans, long highRisk,
                                  long mediumRisk, long lowRisk) {
        this.totalScans = totalScans;
        this.fakeJobScans = fakeJobScans;
        this.urlScans = urlScans;
        this.highRisk = highRisk;
        this.mediumRisk = mediumRisk;
        this.lowRisk = lowRisk;
    }

    public long getTotalScans() {
        return totalScans;
    }

    public long getFakeJobScans() {
        return fakeJobScans;
    }

    public long getUrlScans() {
        return urlScans;
    }

    public long getHighRisk() {
        return highRisk;
    }

    public long getMediumRisk() {
        return mediumRisk;
    }

    public long getLowRisk() {
        return lowRisk;
    }
}
