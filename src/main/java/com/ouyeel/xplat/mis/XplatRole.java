package com.ouyeel.xplat.mis;

import java.io.Serializable;
import java.lang.*;
import java.util.*;

public class XplatRole implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleCode;

    private String roleName;

    private String roleDescription;

    private String accountsetId;

    private String recCreator;

    private Date recCreateTime;

    private String recRevisor;

    private Date recReviseTime;

    private String recFlag;


    public void setRoleId(Integer ROLE_ID){
        this.roleId=roleId;
    }
    public Integer getRoleId(){
        return roleId;
    }
    public void setRoleCode(String ROLE_CODE){
        this.roleCode=roleCode;
    }
    public String getRoleCode(){
        return roleCode;
    }
    public void setRoleName(String ROLE_NAME){
        this.roleName=roleName;
    }
    public String getRoleName(){
        return roleName;
    }
    public void setRoleDescription(String ROLE_DESCRIPTION){
        this.roleDescription=roleDescription;
    }
    public String getRoleDescription(){
        return roleDescription;
    }
    public void setAccountsetId(String ACCOUNTSET_ID){
        this.accountsetId=accountsetId;
    }
    public String getAccountsetId(){
        return accountsetId;
    }
    public void setRecCreator(String REC_CREATOR){
        this.recCreator=recCreator;
    }
    public String getRecCreator(){
        return recCreator;
    }
    public void setRecCreateTime(Date REC_CREATE_TIME){
        this.recCreateTime=recCreateTime;
    }
    public Date getRecCreateTime(){
        return recCreateTime;
    }
    public void setRecRevisor(String REC_REVISOR){
        this.recRevisor=recRevisor;
    }
    public String getRecRevisor(){
        return recRevisor;
    }
    public void setRecReviseTime(Date REC_REVISE_TIME){
        this.recReviseTime=recReviseTime;
    }
    public Date getRecReviseTime(){
        return recReviseTime;
    }
    public void setRecFlag(String REC_FLAG){
        this.recFlag=recFlag;
    }
    public String getRecFlag(){
        return recFlag;
    }

    @Override
    public String toString() {
        return "XplatRole{" +
                            "roleId=" + roleId + "," +
                            "roleCode=" + roleCode + "," +
                            "roleName=" + roleName + "," +
                            "roleDescription=" + roleDescription + "," +
                            "accountsetId=" + accountsetId + "," +
                            "recCreator=" + recCreator + "," +
                            "recCreateTime=" + recCreateTime + "," +
                            "recRevisor=" + recRevisor + "," +
                            "recReviseTime=" + recReviseTime + "," +
                            "recFlag=" + recFlag + "," +
                    '}';
    }
}