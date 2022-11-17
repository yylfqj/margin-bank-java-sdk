package org.margin.bank.request;

import java.io.Serializable;

/**
 * @ClassName FlowDetail
 * @Author yyl
 * @Date 2022-10-25 09:58:52
 * @Description FlowDetail
 * @Version 1.0
 */
public class FlowDetail implements Serializable {

    private static final long serialVersionUID = -1721272434424273279L;

    private String serialNumber;

    private String bankCard;

    private String bankName;

    private String belongNumber;

    private String flowDirection;

    private String flowType;

    private String oppositeBankCard;

    private String oppositeBankName;

    private String oppositeOrgCode;

    private String oppositeOrgName;

    private String oppositeIdNumber;

    private String amount;

    private String balanceAfterChange;

    private String operationTime;

    private String remarks;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBelongNumber() {
        return belongNumber;
    }

    public void setBelongNumber(String belongNumber) {
        this.belongNumber = belongNumber;
    }

    public String getFlowDirection() {
        return flowDirection;
    }

    public void setFlowDirection(String flowDirection) {
        this.flowDirection = flowDirection;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getOppositeBankCard() {
        return oppositeBankCard;
    }

    public void setOppositeBankCard(String oppositeBankCard) {
        this.oppositeBankCard = oppositeBankCard;
    }

    public String getOppositeBankName() {
        return oppositeBankName;
    }

    public void setOppositeBankName(String oppositeBankName) {
        this.oppositeBankName = oppositeBankName;
    }

    public String getOppositeOrgCode() {
        return oppositeOrgCode;
    }

    public void setOppositeOrgCode(String oppositeOrgCode) {
        this.oppositeOrgCode = oppositeOrgCode;
    }

    public String getOppositeOrgName() {
        return oppositeOrgName;
    }

    public void setOppositeOrgName(String oppositeOrgName) {
        this.oppositeOrgName = oppositeOrgName;
    }

    public String getOppositeIdNumber() {
        return oppositeIdNumber;
    }

    public void setOppositeIdNumber(String oppositeIdNumber) {
        this.oppositeIdNumber = oppositeIdNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalanceAfterChange() {
        return balanceAfterChange;
    }

    public void setBalanceAfterChange(String balanceAfterChange) {
        this.balanceAfterChange = balanceAfterChange;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
