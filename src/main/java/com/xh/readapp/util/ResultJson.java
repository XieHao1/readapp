package com.xh.readapp.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultJson implements Serializable {

    private int coed;

    private String msg;

    private boolean success;

    private Object date;

    public static ResultJson success(Object date){
        return new ResultJson(200,"success",true,date);
    }

    public static ResultJson fail(int coed,String msg){
        return new ResultJson(coed,msg,false,null);
    }

}
