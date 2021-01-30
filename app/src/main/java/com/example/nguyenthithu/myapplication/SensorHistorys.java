package com.example.nguyenthithu.myapplication;

/**
 * Created by nguyenthithu on 1/27/21.
 */

public class SensorHistorys {
    /**
     *
     */
    private String Doam;
    private String Time;
    private String Note;

    // Constructor Declaration of Class

    public SensorHistorys(String Doam,  String Time, String Note )
    {
        this.Doam = Doam;

        this.Time = Time;
        this.Note = Note;
    }

    public String getDoam() {
        return Doam;
    }

    public String getTime() {
        return Time;
    }

    public String getNote() {
        return Note;
    }
}
