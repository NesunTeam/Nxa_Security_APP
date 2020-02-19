package com.nesun.sec.apps.bean;

public class SecScanBean {


    private String scanType;
    private String scanStatus;
    private boolean isScanSafe;


    public SecScanBean(String scanType,String scanStatus){
        this.scanType = scanType;
        this.scanStatus = scanStatus;
    }

    public String getScanStatus() {
        return scanStatus;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }


    public boolean isScanSafe() {
        return isScanSafe;
    }

    public void setScanSafe(boolean scanSafe) {
        isScanSafe = scanSafe;
    }
}
