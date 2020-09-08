package com.cxw.algorithm.dynamic.om;

import java.util.Objects;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:06
 * @description 司机
 */
public class Driver {
    private int driverId;
    private int eda;

    public Driver() {
    }

    public Driver(int driverId, int eda) {
        this.driverId = driverId;
        this.eda = eda;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getEda() {
        return eda;
    }

    public void setEda(int eda) {
        this.eda = eda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false; }
        Driver driver = (Driver) o;
        return driverId == driver.driverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId);
    }
}