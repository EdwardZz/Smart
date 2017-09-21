package com.example.acer.smart.entity;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.entity
*  文件名：CourierData
*  创建者：朱勇杰
*  创建时间：2017/9/21
*  描述：  CourierData
*/
public class CourierData {

    private String datatime;
    private String remark;
    private String zone;


    public String getDatatime() {
        return datatime;
    }

    public String getZone() {
        return zone;
    }

    public String getRemark() {
        return remark;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "CourierData{" +
                "datatime='" + datatime + '\'' +
                ", remark='" + remark + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
