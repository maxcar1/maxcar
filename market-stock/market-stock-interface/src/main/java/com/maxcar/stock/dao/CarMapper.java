package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.entity.Request.BarrierListCarRequest;
import com.maxcar.stock.entity.Request.GetCarListByMarketIdAndTenantRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalResponse;
import com.maxcar.stock.entity.Response.*;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.vo.CarVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarMapper extends BaseDao<Car, String> {

    Integer selectCountsByCar(Car car);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String id);

    CarVehicle selectCarByid(String id);

    int updateByPrimaryKeySelective(Car car);

    int updateByVin(Car car);

    List<CarVo> listCarVo(CarVo carVo);

    List<CarVo> listReview(CarParams carParams);

    List<CarVo> carReviewDetailList(CarParams carParams);


    /**
     * param:
     * describe: 根据搜索条件动态查询 总库存量  在场量 出厂量  库存总价值
     * create_date:  lxy   2018/11/5  11:28
     **/
    ListCarVoNumberResponse listCarVoNumber(CarVo carVo);

    List<CarVo> listCarVoById(CarVo carVo);

    double getSumMarketPrice(CarVo carVo);

    Car getCar(Car record);

    CarVo getCarVoById(String id);

    List<Car> getCarList(Map<String, Object> car);

    List<Car> getCarListByStaffId(List<String> staffIds);

    List<CarVo> listCarChannelVo(CarVo carVo);

    /**
     * 市场商品车总数
     * shenzhongzong
     *
     * @return
     */
    int countCarNum(@Param("marketId") String marketId);

    /**
     * 商户商品车总数
     * shenzhongzong
     *
     * @return
     */
    int countRepertoryByTenantId(@Param("id") String id, @Param("marketId") String marketId);


    List<Car> selectByExample(CarExample example);

    /**
     * param:
     * describe: 获取库存统计信息
     * create_date:  lxy   2018/9/12  11:51
     **/
    InventoryStatisticalResponse inventoryStatistical(InventoryStatisticalRequest request);

    public Car getCarDetails(Car car);

    List<TaoBaoCar> getTaoBaoBrand();

    List<TaoBaoCar> getTaoBaoCarByKey(TaoBaoCar taoBaoCar);

    CarInfo getCarInfoById(String carId);

    List<CarPic> getCarPic(String id);

    void modifyCarInfo(CarInfo carInfo);

    List<Car> getCarListByTenant(List<String> tenants);


    int countByExample(CarExample example);

    List<CarVo> selectCarList(CarVo carVo);

    /**
     * param:
     * describe: 根据市场ID和商户ID 查询 库存车 可过户 列表
     * create_date:  lxy   2018/10/15  13:56
     **/
    List<GetCarListByMarketIdAndTenantResponse> getCarListByMarketIdAndTenant(GetCarListByMarketIdAndTenantRequest request);

    List<DpCar> getNtCar(DpCar car);

    List<CarIcon> listCarIcon();


    List<Car> getAllMarketCarByStatus(Map map);

    int deleteByVinAndRfid(CarRecord c);

    /**
     * 道闸黑白名单配置时添加车辆信息时返回的数据
     *
     * @param request
     * @return
     */
    List<BarrierCarListResponse> selectCarByTenant(BarrierListCarRequest request);

    /**
     * 获取商户下的所有的车型和品牌车系
     *
     * @param tenantId
     * @return
     */
    List<String> getAllBrandNameByTenant(@Param("tenantId") String tenantId);


    Car getStockCarByVin(String vin);

    /**
     * 商户库存信息
     * @param tenantId
     * @return
     */
    CarDataStatistics getCarDataStatistics(@Param("tenantId") String tenantId);

    CarDataStatistics carData(@Param("tenantId") String tenantId);
}