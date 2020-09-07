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

    public List<MatchedOrderDriver> optimalMatching(List<OrderDrivers> orderDriversList){
        //订单排序
        
        List<Driver> allDriverList = getDriverList(orderDriversList);
        int n = allDriverList.size();
        int m = orderDriversList.size();
        //构建矩阵
        List<MatchedOrderDriver>[][] v = new List[n+1][m+1]; //订单价值
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                OrderDrivers orderDrivers = orderDriversList.get(m - 1);
                Order order = orderDrivers.getOrder();
                List<Driver> driverList = orderDrivers.getDrivers();
                Driver driver = allDriverList.get(n - 1);
                if(!has(orderDrivers,driver)){//包含则可以匹配
                    v[i][j] = v[i-1][j];
                }else{
                    //先比较订单金额大小，再比较eda
                    List<MatchedOrderDriver> matchedOrderDrivers1 = v[i - i][j];
                    List<MatchedOrderDriver> matchedOrderDrivers2 = v[i][j-1];
                    List<MatchedOrderDriver> maxMatchedOrderDrivers = max(matchedOrderDrivers1,matchedOrderDrivers2);
                    List<MatchedOrderDriver> moreMaxMatchedOrderDrivers = max(maxMatchedOrderDrivers,order,driver);
                    v[i][j] = moreMaxMatchedOrderDrivers;
                }
            }
        }
        return null;
    }

    private List<MatchedOrderDriver> max(List<MatchedOrderDriver> maxMatchedOrderDrivers, Order order, Driver driver) {
        //TODO 比较当前order 和driver 与原有 金额大小 eda
        return null;
    }

    private List<MatchedOrderDriver> max(List<MatchedOrderDriver> matchedOrderDrivers1, List<MatchedOrderDriver> matchedOrderDrivers2) {
        //TODO 先比较订单金额大小，在比较eda
        return null;
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