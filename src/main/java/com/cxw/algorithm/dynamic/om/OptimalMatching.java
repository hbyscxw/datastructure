package com.cxw.algorithm.dynamic.om;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chengxuwei
 * @date 2020-08-07 15:05
 * @description
 */
public class OptimalMatching {
    public static void main(String[] args) {
        OptimalMatching op = new OptimalMatching();
        List<OrderDrivers> orderDriversList = new ArrayList<>();
        orderDriversList.add(new OrderDrivers(new Order("P1",80)
                ,new Driver(1,500),new Driver(2,100)));
        orderDriversList.add(new OrderDrivers(new Order("P2",50)
                ,new Driver(2,300)));
        orderDriversList.add(new OrderDrivers(new Order("P3",30)
                ,new Driver(3,400)));
        List<MatchedOrderDriver> res = op.optimalMatching(orderDriversList);
        System.out.println(res);
    }

    public List<MatchedOrderDriver> optimalMatching(List<OrderDrivers> orderDriversList){
        //先排序 金额、司机多少
        orderDriversList.sort((o1,o2)->{
            if(o1.getDrivers().size()<o2.getDrivers().size()){
                return -1;
            }else if(o1.getDrivers().size()>o2.getDrivers().size()) {
                return 1;
            }else{
                if (o1.getOrder().getEstimatedAmount() > o2.getOrder().getEstimatedAmount()) {
                    return -1;
                } else if (o1.getOrder().getEstimatedAmount() < o2.getOrder().getEstimatedAmount()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        List<Driver> allDriverList = getDriverList(orderDriversList);
        int n = allDriverList.size();
        int m = orderDriversList.size();
        //构建矩阵
        Object[][] v = new Object[n+1][m+1]; //订单价值
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                OrderDrivers orderDrivers = orderDriversList.get(j - 1);
                Order order = orderDrivers.getOrder();
                //List<Driver> driverList = orderDrivers.getDrivers();
                Driver driver = allDriverList.get(i - 1);
                if(!has(orderDrivers,driver)){//不包含直接取上个单元格
                    if(v[i-1][j]==null){
                        v[i][j] = null;
                    }else {
                        v[i][j] = new ArrayList<>((List<MatchedOrderDriver>) v[i - 1][j]);
                    }
                }else{
                    //先比较订单金额大小，再比较eda
                    List<MatchedOrderDriver> matchedOrderDrivers1 = (List<MatchedOrderDriver>)v[i - i][j];
                    List<MatchedOrderDriver> matchedOrderDrivers2 = (List<MatchedOrderDriver>)v[i][j-1];
                    List<MatchedOrderDriver> maxMatchedOrderDrivers = max(matchedOrderDrivers1,matchedOrderDrivers2);
                    List<MatchedOrderDriver> moreMaxMatchedOrderDrivers = max(maxMatchedOrderDrivers,order,driver);
                    v[i][j] = moreMaxMatchedOrderDrivers;
                }
            }
        }
        return (List<MatchedOrderDriver>)v[n][m];
    }

    private List<MatchedOrderDriver> max(List<MatchedOrderDriver> mods, Order order, Driver driver) {
        //比较当前order 和driver 与原有 金额大小 eda
        List<MatchedOrderDriver> matchedOrderDrivers;
        if(mods==null){
            matchedOrderDrivers = new ArrayList<>();
        }else{
            matchedOrderDrivers = new ArrayList<>(mods);
        }
        MatchedOrderDriver mod = getContainsMatchedOrderDriver(matchedOrderDrivers, driver);
        if(mod!=null){
            //比较当前order 和driver 与原有 金额大小
            if(mod.getOrder().getEstimatedAmount()<order.getEstimatedAmount()){
                mod.setOrder(order);
                mod.setDriver(driver);
            }else if((mod.getOrder().getEstimatedAmount()==order.getEstimatedAmount())
                    && mod.getDriver().getEda() > driver.getEda()){
                mod.setOrder(order);
                mod.setDriver(driver);
            }
        }else{
            matchedOrderDrivers.add(new MatchedOrderDriver(order,driver));
        }
        return matchedOrderDrivers;
    }

    private MatchedOrderDriver getContainsMatchedOrderDriver(List<MatchedOrderDriver> matchedOrderDrivers, Driver driver) {
        if(matchedOrderDrivers==null){
            return null;
        }
        for (MatchedOrderDriver matchedOrderDriver : matchedOrderDrivers) {
            if(matchedOrderDriver.getDriver().getDriverId()==driver.getDriverId()){
                return matchedOrderDriver;
            }
        }
        return null;
    }

    private List<MatchedOrderDriver> max(List<MatchedOrderDriver> matchedOrderDrivers1, List<MatchedOrderDriver> matchedOrderDrivers2) {
        //先比较订单金额大小，在比较eda
        if(matchedOrderDrivers1==null||matchedOrderDrivers1.size()==0){
            return matchedOrderDrivers2;
        }
        if(matchedOrderDrivers2==null||matchedOrderDrivers2.size()==0){
            return matchedOrderDrivers1;
        }
        double modEstimatedAmount1 = sumEstimatedAmount(matchedOrderDrivers1);
        double modEstimatedAmount2 = sumEstimatedAmount(matchedOrderDrivers2);
        if(modEstimatedAmount1>modEstimatedAmount2){
            return matchedOrderDrivers1;
        }else if(modEstimatedAmount1<modEstimatedAmount2){
            return matchedOrderDrivers2;
        }else {
            double modEda1 = sumEda(matchedOrderDrivers1);
            double modEda2 = sumEda(matchedOrderDrivers2);
            if(modEda1<=modEda2){
                return matchedOrderDrivers1;
            }else {
                return matchedOrderDrivers2;
            }
        }
    }

    private double sumEda(List<MatchedOrderDriver> matchedOrderDrivers) {
        if(matchedOrderDrivers==null||matchedOrderDrivers.size()==0){
            return 0;
        }
        return matchedOrderDrivers.stream().mapToDouble(od->od.getDriver().getEda()).sum();
    }

    private double sumEstimatedAmount(List<MatchedOrderDriver> matchedOrderDrivers) {
        if(matchedOrderDrivers==null||matchedOrderDrivers.size()==0){
            return Integer.MAX_VALUE;
        }
        return matchedOrderDrivers.stream().mapToDouble(od->od.getOrder().getEstimatedAmount()).sum();
    }

    private boolean has(OrderDrivers orderDrivers, Driver driver) {
        List<Driver> drivers = orderDrivers.getDrivers();
        return drivers.contains(driver);
    }

    private List<Driver> getDriverList(List<OrderDrivers> orderDrivers) {
        Set<Driver> driverSet = new LinkedHashSet<>();
        for (OrderDrivers orderDriver : orderDrivers) {
            driverSet.addAll(orderDriver.getDrivers());
        }
        return new ArrayList<>(driverSet);
    }
}