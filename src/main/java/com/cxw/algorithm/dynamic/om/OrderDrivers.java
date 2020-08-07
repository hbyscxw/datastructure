package com.cxw.algorithm.dynamic.om;

import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:08
 * @description
 */
public class OrderDrivers {
    private Order order;
    private List<Driver> drivers;

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