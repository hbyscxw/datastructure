package com.cxw.algorithm.dynamic.om;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:08
 * @description
 */
public class OrderDrivers {
    private Order order;
    private List<Driver> drivers;

    public OrderDrivers() {
    }

    public OrderDrivers(Order order, Driver ... driverAry) {
        this.order = order;
        this.drivers = new ArrayList<>();
        Collections.addAll(drivers,driverAry);
    }

    public OrderDrivers(Order order, List<Driver> drivers) {
        this.order = order;
        this.drivers = drivers;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}