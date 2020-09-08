package com.cxw.algorithm.dynamic.om;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:06
 * @description 司机
 */
public class Order {
    private String orderNo;
    private double estimatedAmount;

    public Order() {
    }

    public Order(String orderNo, double estimatedAmount) {
        this.orderNo = orderNo;
        this.estimatedAmount = estimatedAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }
}