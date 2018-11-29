package com.maxcar.core.entity.user.manage.dto;

import java.io.Serializable;
import java.util.Date;

import com.maxcar.core.utils.Page;

public class CustomerFavoriteDto extends Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customer;

	private String car;
	
	//查询条件
    private String series;//品牌车系
    private String series_name;//车系名称
    private Float displacement;//排量
    private String model_year;//年款
    private Integer seats;//座位数
    private Integer seatType;
    private Integer mileage;//表显里程
    private String environmentalStandards;//排放标准
    private Date initial_licence_time;//初次上牌时间
    private String initial_licence_timeStr;
    private String color;//车身颜色
    private Integer attribution;//车辆归属地，城市ID
    private Float newcar_price;//新车市场价
    //private Float net_price;//网上报价
    private Float market_price;//展厅报价
    //private Float floor_price;//底价
    //private Float manager_price;//销售经理底价
    private String advertisement;//一句话广告
    private Integer version;//数据版本号，防止为空
    private Date insert_time;
    private Date update_time;
    private String insert_timeStr;
    
    //扩展
    private String brand_name;
    private String brand_code;
    private String model_name;
    private String model_code;
    private String tenant_name;
    private String tenant_phone;
    private String tenant_address;
    
	private String air_conditioner_control_type;//手动挡
   private String fuel_form;//燃料
   private String level;//轿车级别
   private String environmental_standards;//排放标准
   private Integer favorite;//是否收藏
   private Date register_time;//上架时间
   private String register_timeStr;
   private String city_name;//车辆归属地城市名
   private Integer is_vendor_certificated;//是否厂商认证：1-是；2不是
   private String tenant_no;//商户编号
   private String vin;//车辆vin码
   private Integer support_periodization;//是否支持分期：1-支持；o or null不支持
   private Integer include_transfer_fee;//是否含过户费：1-包含；0 or null-不包含
   private String rfid;
   private Integer equipid;//监控终端id
   private Integer status;//车辆状态
   private Integer apply_status;//车辆申请出场状态
   private Integer warrancy;
   private Integer warranty_time;//质保时间
   private Integer warranty_mile;//质保里程
   private Integer regular_maintance;//是否定期保养
   private Integer brows_count;//详情页浏览次数
   private Integer disType;
   private Integer evaType;
   private Float displacement1;
   private Float displacement2;
   private Float evaluate_price1;
   private Float evaluate_price2;
   private Float amount;
   private Integer car_status;// 1 场内 2 场外
   private Integer initial_licence_time_int;//车龄
   private Integer order_type;// 1最新发布 2价格最低 3价格最高 4里程最少 5车龄最短 
   private String car_pic;//车辆展示图
   private Integer new_car;//是否准新车 1是 其余为否
   private Integer car_sales;//是否已售出 1 售出车辆
   private Integer equType;//1 监管中  2 不监管
	

	public CustomerFavoriteDto() {
	}
	
	public CustomerFavoriteDto(String customer, String car) {
		super();
		this.customer = customer;
		this.car = car;
	}
	
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSeries_name() {
		return series_name;
	}
	
	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public Float getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Float displacement) {
		this.displacement = displacement;
	}

	public String getModel_year() {
		return model_year;
	}

	public void setModel_year(String model_year) {
		this.model_year = model_year;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getEnvironmentalStandards() {
		return environmentalStandards;
	}

	public void setEnvironmentalStandards(String environmentalStandards) {
		this.environmentalStandards = environmentalStandards;
	}

	public Date getInitial_licence_time() {
		return initial_licence_time;
	}

	public void setInitial_licence_time(Date initial_licence_time) {
		this.initial_licence_time = initial_licence_time;
	}

	public String getInitial_licence_timeStr() {
		return initial_licence_timeStr;
	}

	public void setInitial_licence_timeStr(String initial_licence_timeStr) {
		this.initial_licence_timeStr = initial_licence_timeStr;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Integer getDisType() {
		return disType;
	}

	public void setDisType(Integer disType) {
		this.disType = disType;
	}

	public Integer getEvaType() {
		return evaType;
	}

	public void setEvaType(Integer evaType) {
		this.evaType = evaType;
	}

	public Float getDisplacement1() {
		return displacement1;
	}

	public void setDisplacement1(Float displacement1) {
		this.displacement1 = displacement1;
	}

	public Float getDisplacement2() {
		return displacement2;
	}

	public void setDisplacement2(Float displacement2) {
		this.displacement2 = displacement2;
	}

	public Float getEvaluate_price1() {
		return evaluate_price1;
	}

	public void setEvaluate_price1(Float evaluate_price1) {
		this.evaluate_price1 = evaluate_price1;
	}

	public Float getEvaluate_price2() {
		return evaluate_price2;
	}

	public void setEvaluate_price2(Float evaluate_price2) {
		this.evaluate_price2 = evaluate_price2;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getCar_status() {
		return car_status;
	}

	public void setCar_status(Integer car_status) {
		this.car_status = car_status;
	}

	public Integer getInitial_licence_time_int() {
		return initial_licence_time_int;
	}

	public void setInitial_licence_time_int(Integer initial_licence_time_int) {
		this.initial_licence_time_int = initial_licence_time_int;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public String getCar_pic() {
		return car_pic;
	}

	public void setCar_pic(String car_pic) {
		this.car_pic = car_pic;
	}

	public Integer getNew_car() {
		return new_car;
	}

	public void setNew_car(Integer new_car) {
		this.new_car = new_car;
	}

	public Integer getCar_sales() {
		return car_sales;
	}

	public void setCar_sales(Integer car_sales) {
		this.car_sales = car_sales;
	}

	public Integer getEquType() {
		return equType;
	}

	public void setEquType(Integer equType) {
		this.equType = equType;
	}

	public Integer getAttribution() {
		return attribution;
	}

	public void setAttribution(Integer attribution) {
		this.attribution = attribution;
	}

	public Float getNewcar_price() {
		return newcar_price;
	}

	public void setNewcar_price(Float newcar_price) {
		this.newcar_price = newcar_price;
	}

	public Float getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Float market_price) {
		this.market_price = market_price;
	}

	public String getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getInsert_timeStr() {
		return insert_timeStr;
	}

	public void setInsert_timeStr(String insert_timeStr) {
		this.insert_timeStr = insert_timeStr;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrand_code() {
		return brand_code;
	}

	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getModel_code() {
		return model_code;
	}

	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}

	public String getTenant_name() {
		return tenant_name;
	}

	public void setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
	}

	public String getTenant_phone() {
		return tenant_phone;
	}

	public void setTenant_phone(String tenant_phone) {
		this.tenant_phone = tenant_phone;
	}

	public String getTenant_address() {
		return tenant_address;
	}

	public void setTenant_address(String tenant_address) {
		this.tenant_address = tenant_address;
	}

	public String getAir_conditioner_control_type() {
		return air_conditioner_control_type;
	}

	public void setAir_conditioner_control_type(String air_conditioner_control_type) {
		this.air_conditioner_control_type = air_conditioner_control_type;
	}

	public String getFuel_form() {
		return fuel_form;
	}

	public void setFuel_form(String fuel_form) {
		this.fuel_form = fuel_form;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEnvironmental_standards() {
		return environmental_standards;
	}

	public void setEnvironmental_standards(String environmental_standards) {
		this.environmental_standards = environmental_standards;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public String getRegister_timeStr() {
		return register_timeStr;
	}

	public void setRegister_timeStr(String register_timeStr) {
		this.register_timeStr = register_timeStr;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Integer getIs_vendor_certificated() {
		return is_vendor_certificated;
	}

	public void setIs_vendor_certificated(Integer is_vendor_certificated) {
		this.is_vendor_certificated = is_vendor_certificated;
	}

	public String getTenant_no() {
		return tenant_no;
	}

	public void setTenant_no(String tenant_no) {
		this.tenant_no = tenant_no;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getSupport_periodization() {
		return support_periodization;
	}

	public void setSupport_periodization(Integer support_periodization) {
		this.support_periodization = support_periodization;
	}

	public Integer getInclude_transfer_fee() {
		return include_transfer_fee;
	}

	public void setInclude_transfer_fee(Integer include_transfer_fee) {
		this.include_transfer_fee = include_transfer_fee;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Integer getEquipid() {
		return equipid;
	}

	public void setEquipid(Integer equipid) {
		this.equipid = equipid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getApply_status() {
		return apply_status;
	}

	public void setApply_status(Integer apply_status) {
		this.apply_status = apply_status;
	}

	public Integer getWarrancy() {
		return warrancy;
	}

	public void setWarrancy(Integer warrancy) {
		this.warrancy = warrancy;
	}

	public Integer getWarranty_time() {
		return warranty_time;
	}

	public void setWarranty_time(Integer warranty_time) {
		this.warranty_time = warranty_time;
	}

	public Integer getWarranty_mile() {
		return warranty_mile;
	}

	public void setWarranty_mile(Integer warranty_mile) {
		this.warranty_mile = warranty_mile;
	}

	public Integer getRegular_maintance() {
		return regular_maintance;
	}

	public void setRegular_maintance(Integer regular_maintance) {
		this.regular_maintance = regular_maintance;
	}

	public Integer getBrows_count() {
		return brows_count;
	}

	public void setBrows_count(Integer brows_count) {
		this.brows_count = brows_count;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCar() {
		return this.car;
	}

	public void setCar(String car) {
		this.car = car;
	}
}