package com.maxcar.jdbc;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.BarrierControlCarExample;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import com.maxcar.util.Canstats;
import com.maxcar.util.JsonTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcCurd {
    /*
     * 增加
     */
    public static Integer saveCar(Car car) {
        Connection connection = null;
        int num  = 0;
        PreparedStatement preparedStatement = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            String sql = "insert into car (id,car_no,vin,rfid,stock_status" +
            ",car_status,car_type,register_time,isvalid,market_id,area_id,tenant) values(" +
                    "?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,car.getId());
            preparedStatement.setString(2,car.getCarNo());
            preparedStatement.setString(3,car.getVin());
            preparedStatement.setString(4,car.getRfid());
            preparedStatement.setInt(5,car.getStockStatus());
            preparedStatement.setInt(6,car.getCarStatus());
            preparedStatement.setInt(7,car.getCarType());
            Long nowTime = (new Date().getTime());
            preparedStatement.setDate(8,car.getRegisterTime()!=null?new java.sql.Date(car.getRegisterTime().getTime()):new java.sql.Date(nowTime));
            preparedStatement.setInt(9,car.getIsvalid());
            preparedStatement.setString(10,car.getMarketId());
            preparedStatement.setString(11,car.getAreaId());
            preparedStatement.setString(12,car.getTenant());
            // 填充占位符
//            preparedStatement.set
            num = preparedStatement.executeUpdate();
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
                JdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return num;
    }
    /*
     * 查询
     */
    public static Car selectCarByRfid(String marketId,String rfid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car newCar = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 获取PrepareStatement对象
            preparedStatement = connection.prepareStatement("select * from car where rfid=? and market_id=? and isvalid=1 ");
//            and stock_status in(1,2,3,6)
            preparedStatement.setString(1,rfid);
            preparedStatement.setString(2,marketId);
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String carNo = resultSet.getString(2);
                String vin = resultSet.getString(3);
                String rfId = resultSet.getString(4);
                Integer stockStatus = resultSet.getInt(5);
                Integer carStatus = resultSet.getInt(6);
                Integer carType = resultSet.getInt(7);
//                String marketId = resultSet.getString(11);
                String tenantId = resultSet.getString(13);
                newCar = new Car();
                newCar.setId(id);
                newCar.setRfid(rfId);
                newCar.setCarNo(carNo);
                newCar.setStockStatus(stockStatus);
                newCar.setTenant(tenantId);
                newCar.setCarNo(carNo);
                newCar.setVin(vin);
                newCar.setCarStatus(carStatus);
                newCar.setCarType(carType);
                newCar.setMarketId(marketId);
            }
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return newCar;
    }

    /**
     * 根据道闸id获取道闸信息
     * @param barrierId
     * @return
     */
    public static Barrier selectByBarrierId(String barrierId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Barrier barrier = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 获取PrepareStatement对象
            preparedStatement = connection.prepareStatement("SELECT * from barrier where barrier_id=? and isvalid=1");
            preparedStatement.setString(1,barrierId);
//            preparedStatement.setString(2,marketId);
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                barrier = new Barrier();
                barrier.setId(resultSet.getInt(1));
                barrier.setBarrierId(barrierId);
                barrier.setGatewayIp(resultSet.getString(3));
                barrier.setSubnetMask(resultSet.getString(4));
                barrier.setMacAddress(resultSet.getString(5));
                barrier.setClientIp(resultSet.getString(6));
                barrier.setServerIp(resultSet.getString(7));
                barrier.setClientPort(resultSet.getString(8));
                barrier.setServerPort(resultSet.getString(9));
                barrier.setBarrierType(resultSet.getString(10));
//                barrier.setBarrierPosition(resultSet.getString(3));
//                barrier.setInOutCar(resultSet.getString(12));
                barrier.setInOutType(resultSet.getInt(13));
                barrier.setParkLimit(resultSet.getString(14));
//                barrier.setIsvalid();
                barrier.setStatus(resultSet.getString(16));
                barrier.setStaticSpeech(resultSet.getString(20));
                barrier.setMarketId(resultSet.getString(21));
                barrier.setMqttTopic(resultSet.getString(22));
            }
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return barrier;
    }

    /**
     * 黑白名单改造
     * @param car
     * @param barrier
     * @return
     */
    public static List<BarrierControlCar> selectBarrierControlCar(Car car,Barrier barrier) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BarrierControlCar> barrierControlCars = new ArrayList<>();
        BarrierControlCar barrierControlCar = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 获取PrepareStatement对象
            StringBuffer stringBuffer = new StringBuffer("SELECT * from barrier_control_car where barrier_id=? and isvalid=1 " +
                    "and market_id=?  ");
//            if(car.getTenant()!=null)//当车或者商户都在白名单中，代表可行
                stringBuffer.append("and (car_id=? or tenant_id=?)");
//            else
//                stringBuffer.append(" and car_id=?");

            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setString(1,barrier.getBarrierId());
            preparedStatement.setString(2,barrier.getMarketId());
            preparedStatement.setString(3,car.getId());
//            if(car.getTenant()!=null)//当车或者商户都在白名单中，代表可行
                preparedStatement.setString(4,car.getTenant());

//            preparedStatement.setString(2,marketId);
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                barrierControlCar = new BarrierControlCar();
                barrierControlCars.add(barrierControlCar);
            }
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return barrierControlCars;
    }

    /**
     * 查询出场超时状态
     * @param carId
     * @return
     */
    public static boolean selectCarReviewByCarId(String carId,String marketId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 获取PrepareStatement对象

            String stringBuffer = "SELECT * from car_review where is_valid=1 " +
                    " and car_id=? and is_pass=1 and is_complete=0 and market_id=?";

            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setString(1,carId);
            preparedStatement.setString(2,marketId);
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            return resultSet.next();
            // 返回影响到的行数
        } catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseDB(connection, preparedStatement, null);
        }
        return false;
    }
    public static void main(String[] args){
        String rfId = "010000000000011589310458";
        String marketId = "010";
//        String sql = "select * from car where rfid=? and market_id=? and isvalid=1 and stock_status in(1,2,3,6)";
        Car car = new Car();
        car.setMarketId(marketId);
        car.setRfid(rfId);
        car.setId("f31c4de831d04684ad65ff5d27658e8f");
        car.setTenant("18101010134993652858");
//        Car car = selectCarByRfid(marketId,rfId);
        System.out.println("查询开始时间：" + Canstats.dateformat.format(new Date()));
        Barrier barrier = selectByBarrierId("05D8FF363431464D43174836");
        List flag = selectBarrierControlCar(car,barrier);
        System.out.println("查询结束时间：" + Canstats.dateformat.format(new Date()));

        System.out.println(JsonTools.toJson(flag));
    }
}
