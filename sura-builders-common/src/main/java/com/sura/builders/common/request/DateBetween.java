package com.sura.builders.common.request;

import lombok.Data;

import java.util.Date;

@Data
public class DateBetween {

    private Date initDate;
    private Date finalDate;

}
