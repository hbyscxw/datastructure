package com.cxw.algorithm.dynamic.om;

import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:08
 * @description
 */
public class MatchedOrderDriver {

    private Order order;
    private Driver driver;
    public MatchedOrderDriver(){
    }
    public MatchedOrderDriver(Order order, Driver driver) {
        this.order = order;
        this.driver = driver;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "MatchedOrderDriver{" +
                "order=" + order.getOrderNo() +
                ", driver=" + driver.getDriverId() +
                '}';
    }
}