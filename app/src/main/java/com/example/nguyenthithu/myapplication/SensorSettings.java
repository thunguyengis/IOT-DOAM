package com.example.nguyenthithu.myapplication;

/**
 * Created by nguyenthithu on 1/27/21.
 */
public class SensorSettings {
    // Instance Variables
    String minDoam;
    String maxDoam;
    String minTime;
    String maxTime;
    String Note;
    Boolean Status;

    // Constructor Declaration of Class
    public SensorSettings(String minDoam, String maxDoam,
                          String minTime, String maxTime, String Note,Boolean Status )
    {
        this.minDoam = minDoam;
        this.maxDoam = maxDoam;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.Note = Note;
        this.Status = Status;
    }

    // method 1
    public String getMinDoAm()
    {
        return minDoam;
    }

    // method 2
    public String getMaxDoAm()
    {
        return maxDoam;
    }

    // method 3
    public String getMinTime()
    {
        return minTime;
    }

    // method 4
    public String getMaxTime()
    {
        return maxTime;
    }

    //
    public String getNote() {
        return Note;
    }
    //

    public Boolean getStatus() {
        return Status;
    }
}
