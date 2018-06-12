package com.project.getonlinehour;

/**
 * Created by r028367 on 09/06/2017.
 */

public class TimeZone {

    private long timestamp, gmtOffset;
    private String countryCode, countryName, zoneName;

    public TimeZone(long timestamp, long gmtOffset
            , String countryCode, String countryName, String zoneName) {
        this.timestamp = timestamp;
        this.gmtOffset = gmtOffset;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.zoneName = zoneName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getGmtOffset() {
        return gmtOffset;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getZoneName() {
        return zoneName;
    }
}
