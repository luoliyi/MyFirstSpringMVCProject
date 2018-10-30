package com.nf.utils;

import java.util.HashMap;

public class Standard extends HashMap {
    private int statecode;
    private String msg;
    private int count;
    private Object data;

    public Standard success(int statecode, String msg){
        return  new Standard(statecode,msg);
    }

    public Standard succData(Object key,Object value){
        super.put(key, value);
        return this;
    }

    public Standard(){}
    public Standard(int statecode, String msg) {
        this.statecode = statecode;
        this.msg = msg;
    }

   public Standard(int statecode, String msg, Object data) {
       this.statecode = statecode;
       this.msg = msg;
       this.data = data;
    }

    //hashmap的级联编程
   /* public Standard(int statecode, String msg, Object data) {
        this.put("statecoude",statecode);
        this.put("msg",msg);
        this.put("data",data);
    }*/

    public Standard(int statecode, String msg, int count, Object data) {
        this.statecode = statecode;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getStatecode() {
        return statecode;
    }

    public void setStatecode(int statecode) {
        this.statecode = statecode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
