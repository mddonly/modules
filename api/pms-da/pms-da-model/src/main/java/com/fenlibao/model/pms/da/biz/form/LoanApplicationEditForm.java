package com.fenlibao.model.pms.da.biz.form;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/11.
 */
public class LoanApplicationEditForm {
    private int id;
    private String phonenum;// 手机号
    private String contacts;// 联系人
    private String amountRange;// 借款金额范围
    private String districtFullName;// 所在区域全称
    private String annualIncome;// 月收入（仅记录收入范围）
    private int hasRoom;// 是否有房（0=否，1=有）
    private int hasCar;// 是否有车（0=否，1=有）
    private Date createTime;// 创建时间
    private Date updateTime;// 修改时间
    private Date processingTime;// 处理时间
    private int processingStatus;// 处理状态（0=未处理，1=通过，2=不通过）
    private String processingOpinion;// 处理意见
    private int nopassReason;// 不通过原因

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAmountRange() {
        return amountRange;
    }

    public void setAmountRange(String amountRange) {
        this.amountRange = amountRange;
    }

    public String getDistrictFullName() {
        return districtFullName;
    }

    public void setDistrictFullName(String districtFullName) {
        this.districtFullName = districtFullName;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getHasRoom() {
        return hasRoom;
    }

    public void setHasRoom(int hasRoom) {
        this.hasRoom = hasRoom;
    }

    public int getHasCar() {
        return hasCar;
    }

    public void setHasCar(int hasCar) {
        this.hasCar = hasCar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    public int getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(int processingStatus) {
        this.processingStatus = processingStatus;
    }

    public String getProcessingOpinion() {
        return processingOpinion;
    }

    public void setProcessingOpinion(String processingOpinion) {
        this.processingOpinion = processingOpinion;
    }

    public int getNopassReason() {
        return nopassReason;
    }

    public void setNopassReason(int nopassReason) {
        this.nopassReason = nopassReason;
    }
}