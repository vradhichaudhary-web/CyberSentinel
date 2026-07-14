package com.cybersentinel.cyberthreatplatform.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalScans;
    private long fakeJobScans;
    private long urlScans;
    private long highRisk;
    private long mediumRisk;
    private long lowRisk;

    public DashboardResponse(
            long totalUsers,
            long totalScans,
            long fakeJobScans,
            long urlScans,
            long highRisk,
            long mediumRisk,
            long lowRisk) {

        this.totalUsers = totalUsers;
        this.totalScans = totalScans;
        this.fakeJobScans = fakeJobScans;
        this.urlScans = urlScans;
        this.highRisk = highRisk;
        this.mediumRisk = mediumRisk;
        this.lowRisk = lowRisk;
    }

    public long getTotalUsers() { return totalUsers; }
    public long getTotalScans() { return totalScans; }
    public long getFakeJobScans() { return fakeJobScans; }
    public long getUrlScans() { return urlScans; }
    public long getHighRisk() { return highRisk; }
    public long getMediumRisk() { return mediumRisk; }
    public long getLowRisk() { return lowRisk; }

    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }
    public void setTotalScans(long totalScans) { this.totalScans = totalScans; }
    public void setFakeJobScans(long fakeJobScans) { this.fakeJobScans = fakeJobScans; }
    public void setUrlScans(long urlScans) { this.urlScans = urlScans; }
    public void setHighRisk(long highRisk) { this.highRisk = highRisk; }
    public void setMediumRisk(long mediumRisk) { this.mediumRisk = mediumRisk; }
    public void setLowRisk(long lowRisk) { this.lowRisk = lowRisk; }
}