package com.nesun.sec.apps.bean;

public class PrivateFileBean {

    private int fileType;

    private String fileName;
    private String fileTime;
    private String fileSize;
    private String filePath;

    private boolean isDirs;
    private boolean isSelect;


    public PrivateFileBean(String fileName,String fileTime,String fileSize,String filePath,int fileType){
        this.fileName = fileName;
        this.fileTime = fileTime;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileType = fileType;
    }



    public int getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileTime() {
        return fileTime;
    }

    public String getFileTimeTrim() {
        return fileTime.replace("_"," ");
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isDirs() {
        return isDirs;
    }

    public void setDirs(boolean dirs) {
        isDirs = dirs;
    }


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
