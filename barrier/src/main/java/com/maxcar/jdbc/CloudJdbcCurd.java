package com.maxcar.jdbc;

import com.maxcar.barrier.pojo.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 云端数据同步服务
 */
public class CloudJdbcCurd {
    /*
     * 查询
     */
    public static Car selectCarByCarId(String market,String rfid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car newCar = null;
        try {
            // 获取连接
            connection = CloudJdbcUtils.getConnection();
            // 获取PrepareStatement对象
            preparedStatement = connection.prepareStatement("select id,car_no,vin,rfid,stock_status" +
                    ",car_status,car_type,register_time,isvalid,market_id,insert_time,area_id,tenant from car where market_id=? and rfid=? ");
//            and stock_status in(1,2,3,6)
            preparedStatement.setString(1,market);
            preparedStatement.setString(2,rfid);
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                newCar = new Car();
                String carNo = resultSet.getString(2);
                String vin = resultSet.getString(3);
                String rfId = resultSet.getString(4);
                Integer stockStatus = resultSet.getInt(5);
                Integer carStatus = resultSet.getInt(6);
                Integer carType = resultSet.getInt(7);
                String marketId = resultSet.getString(10);
                String areaId = resultSet.getString(12);
                String tenantId = resultSet.getString(13);
                newCar.setId(resultSet.getString(1));
                newCar.setRfid(rfId);
                newCar.setCarNo(carNo);
                newCar.setStockStatus(stockStatus);
                newCar.setTenant(tenantId);
                newCar.setCarNo(carNo);
                newCar.setVin(vin);
                newCar.setIsvalid(1);
                newCar.setRegisterTime(resultSet.getDate(8));
                newCar.setMarketId(marketId);
                newCar.setCarStatus(carStatus);
                newCar.setAreaId(areaId);
                newCar.setCarType(carType);
            }
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            CloudJdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return newCar;
    }

    public static void main(String[] args){
        Car car = selectCarByCarId("007","007000000000000288401100");
        int num = JdbcCurd.saveCar(car);
        System.out.println(car+"=="+num);
    }
}
