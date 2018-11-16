package com.maxcar.tenant.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransferCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransferCarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoIsNull() {
            addCriterion("transfer_car_no is null");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoIsNotNull() {
            addCriterion("transfer_car_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoEqualTo(String value) {
            addCriterion("transfer_car_no =", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoNotEqualTo(String value) {
            addCriterion("transfer_car_no <>", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoGreaterThan(String value) {
            addCriterion("transfer_car_no >", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_car_no >=", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoLessThan(String value) {
            addCriterion("transfer_car_no <", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoLessThanOrEqualTo(String value) {
            addCriterion("transfer_car_no <=", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoLike(String value) {
            addCriterion("transfer_car_no like", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoNotLike(String value) {
            addCriterion("transfer_car_no not like", value, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoIn(List<String> values) {
            addCriterion("transfer_car_no in", values, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoNotIn(List<String> values) {
            addCriterion("transfer_car_no not in", values, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoBetween(String value1, String value2) {
            addCriterion("transfer_car_no between", value1, value2, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andTransferCarNoNotBetween(String value1, String value2) {
            addCriterion("transfer_car_no not between", value1, value2, "transferCarNo");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNull() {
            addCriterion("market_id is null");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNotNull() {
            addCriterion("market_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarketIdEqualTo(String value) {
            addCriterion("market_id =", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotEqualTo(String value) {
            addCriterion("market_id <>", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThan(String value) {
            addCriterion("market_id >", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThanOrEqualTo(String value) {
            addCriterion("market_id >=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThan(String value) {
            addCriterion("market_id <", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThanOrEqualTo(String value) {
            addCriterion("market_id <=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLike(String value) {
            addCriterion("market_id like", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotLike(String value) {
            addCriterion("market_id not like", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdIn(List<String> values) {
            addCriterion("market_id in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotIn(List<String> values) {
            addCriterion("market_id not in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdBetween(String value1, String value2) {
            addCriterion("market_id between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotBetween(String value1, String value2) {
            addCriterion("market_id not between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNull() {
            addCriterion("car_id is null");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNotNull() {
            addCriterion("car_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarIdEqualTo(String value) {
            addCriterion("car_id =", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotEqualTo(String value) {
            addCriterion("car_id <>", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThan(String value) {
            addCriterion("car_id >", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThanOrEqualTo(String value) {
            addCriterion("car_id >=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThan(String value) {
            addCriterion("car_id <", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThanOrEqualTo(String value) {
            addCriterion("car_id <=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLike(String value) {
            addCriterion("car_id like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotLike(String value) {
            addCriterion("car_id not like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdIn(List<String> values) {
            addCriterion("car_id in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotIn(List<String> values) {
            addCriterion("car_id not in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdBetween(String value1, String value2) {
            addCriterion("car_id between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotBetween(String value1, String value2) {
            addCriterion("car_id not between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoIsNull() {
            addCriterion("bye_sell_info is null");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoIsNotNull() {
            addCriterion("bye_sell_info is not null");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoEqualTo(String value) {
            addCriterion("bye_sell_info =", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoNotEqualTo(String value) {
            addCriterion("bye_sell_info <>", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoGreaterThan(String value) {
            addCriterion("bye_sell_info >", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoGreaterThanOrEqualTo(String value) {
            addCriterion("bye_sell_info >=", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoLessThan(String value) {
            addCriterion("bye_sell_info <", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoLessThanOrEqualTo(String value) {
            addCriterion("bye_sell_info <=", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoLike(String value) {
            addCriterion("bye_sell_info like", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoNotLike(String value) {
            addCriterion("bye_sell_info not like", value, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoIn(List<String> values) {
            addCriterion("bye_sell_info in", values, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoNotIn(List<String> values) {
            addCriterion("bye_sell_info not in", values, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoBetween(String value1, String value2) {
            addCriterion("bye_sell_info between", value1, value2, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andByeSellInfoNotBetween(String value1, String value2) {
            addCriterion("bye_sell_info not between", value1, value2, "byeSellInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoIsNull() {
            addCriterion("add_deal_info is null");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoIsNotNull() {
            addCriterion("add_deal_info is not null");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoEqualTo(String value) {
            addCriterion("add_deal_info =", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoNotEqualTo(String value) {
            addCriterion("add_deal_info <>", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoGreaterThan(String value) {
            addCriterion("add_deal_info >", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoGreaterThanOrEqualTo(String value) {
            addCriterion("add_deal_info >=", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoLessThan(String value) {
            addCriterion("add_deal_info <", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoLessThanOrEqualTo(String value) {
            addCriterion("add_deal_info <=", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoLike(String value) {
            addCriterion("add_deal_info like", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoNotLike(String value) {
            addCriterion("add_deal_info not like", value, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoIn(List<String> values) {
            addCriterion("add_deal_info in", values, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoNotIn(List<String> values) {
            addCriterion("add_deal_info not in", values, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoBetween(String value1, String value2) {
            addCriterion("add_deal_info between", value1, value2, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andAddDealInfoNotBetween(String value1, String value2) {
            addCriterion("add_deal_info not between", value1, value2, "addDealInfo");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("tenant_id like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("tenant_id not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andVinIsNull() {
            addCriterion("vin is null");
            return (Criteria) this;
        }

        public Criteria andVinIsNotNull() {
            addCriterion("vin is not null");
            return (Criteria) this;
        }

        public Criteria andVinEqualTo(String value) {
            addCriterion("vin =", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotEqualTo(String value) {
            addCriterion("vin <>", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThan(String value) {
            addCriterion("vin >", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThanOrEqualTo(String value) {
            addCriterion("vin >=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThan(String value) {
            addCriterion("vin <", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThanOrEqualTo(String value) {
            addCriterion("vin <=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLike(String value) {
            addCriterion("vin like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotLike(String value) {
            addCriterion("vin not like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinIn(List<String> values) {
            addCriterion("vin in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotIn(List<String> values) {
            addCriterion("vin not in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinBetween(String value1, String value2) {
            addCriterion("vin between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotBetween(String value1, String value2) {
            addCriterion("vin not between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andBrandModelIsNull() {
            addCriterion("brand_model is null");
            return (Criteria) this;
        }

        public Criteria andBrandModelIsNotNull() {
            addCriterion("brand_model is not null");
            return (Criteria) this;
        }

        public Criteria andBrandModelEqualTo(String value) {
            addCriterion("brand_model =", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelNotEqualTo(String value) {
            addCriterion("brand_model <>", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelGreaterThan(String value) {
            addCriterion("brand_model >", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelGreaterThanOrEqualTo(String value) {
            addCriterion("brand_model >=", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelLessThan(String value) {
            addCriterion("brand_model <", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelLessThanOrEqualTo(String value) {
            addCriterion("brand_model <=", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelLike(String value) {
            addCriterion("brand_model like", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelNotLike(String value) {
            addCriterion("brand_model not like", value, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelIn(List<String> values) {
            addCriterion("brand_model in", values, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelNotIn(List<String> values) {
            addCriterion("brand_model not in", values, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelBetween(String value1, String value2) {
            addCriterion("brand_model between", value1, value2, "brandModel");
            return (Criteria) this;
        }

        public Criteria andBrandModelNotBetween(String value1, String value2) {
            addCriterion("brand_model not between", value1, value2, "brandModel");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(Byte value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(Byte value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(Byte value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(Byte value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(Byte value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<Byte> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<Byte> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(Byte value1, Byte value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNull() {
            addCriterion("register_code is null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNotNull() {
            addCriterion("register_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeEqualTo(String value) {
            addCriterion("register_code =", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotEqualTo(String value) {
            addCriterion("register_code <>", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThan(String value) {
            addCriterion("register_code >", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("register_code >=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThan(String value) {
            addCriterion("register_code <", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThanOrEqualTo(String value) {
            addCriterion("register_code <=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLike(String value) {
            addCriterion("register_code like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotLike(String value) {
            addCriterion("register_code not like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIn(List<String> values) {
            addCriterion("register_code in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotIn(List<String> values) {
            addCriterion("register_code not in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeBetween(String value1, String value2) {
            addCriterion("register_code between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotBetween(String value1, String value2) {
            addCriterion("register_code not between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andPlateNumIsNull() {
            addCriterion("plate_num is null");
            return (Criteria) this;
        }

        public Criteria andPlateNumIsNotNull() {
            addCriterion("plate_num is not null");
            return (Criteria) this;
        }

        public Criteria andPlateNumEqualTo(String value) {
            addCriterion("plate_num =", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumNotEqualTo(String value) {
            addCriterion("plate_num <>", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumGreaterThan(String value) {
            addCriterion("plate_num >", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumGreaterThanOrEqualTo(String value) {
            addCriterion("plate_num >=", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumLessThan(String value) {
            addCriterion("plate_num <", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumLessThanOrEqualTo(String value) {
            addCriterion("plate_num <=", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumLike(String value) {
            addCriterion("plate_num like", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumNotLike(String value) {
            addCriterion("plate_num not like", value, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumIn(List<String> values) {
            addCriterion("plate_num in", values, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumNotIn(List<String> values) {
            addCriterion("plate_num not in", values, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumBetween(String value1, String value2) {
            addCriterion("plate_num between", value1, value2, "plateNum");
            return (Criteria) this;
        }

        public Criteria andPlateNumNotBetween(String value1, String value2) {
            addCriterion("plate_num not between", value1, value2, "plateNum");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoIsNull() {
            addCriterion("check_out_photo is null");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoIsNotNull() {
            addCriterion("check_out_photo is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoEqualTo(String value) {
            addCriterion("check_out_photo =", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoNotEqualTo(String value) {
            addCriterion("check_out_photo <>", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoGreaterThan(String value) {
            addCriterion("check_out_photo >", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("check_out_photo >=", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoLessThan(String value) {
            addCriterion("check_out_photo <", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoLessThanOrEqualTo(String value) {
            addCriterion("check_out_photo <=", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoLike(String value) {
            addCriterion("check_out_photo like", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoNotLike(String value) {
            addCriterion("check_out_photo not like", value, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoIn(List<String> values) {
            addCriterion("check_out_photo in", values, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoNotIn(List<String> values) {
            addCriterion("check_out_photo not in", values, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoBetween(String value1, String value2) {
            addCriterion("check_out_photo between", value1, value2, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andCheckOutPhotoNotBetween(String value1, String value2) {
            addCriterion("check_out_photo not between", value1, value2, "checkOutPhoto");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isvalid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isvalid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(Byte value) {
            addCriterion("isvalid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Byte value) {
            addCriterion("isvalid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Byte value) {
            addCriterion("isvalid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Byte value) {
            addCriterion("isvalid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Byte value) {
            addCriterion("isvalid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Byte value) {
            addCriterion("isvalid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Byte> values) {
            addCriterion("isvalid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Byte> values) {
            addCriterion("isvalid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Byte value1, Byte value2) {
            addCriterion("isvalid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Byte value1, Byte value2) {
            addCriterion("isvalid not between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNull() {
            addCriterion("insert_time is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("insert_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(Date value) {
            addCriterion("insert_time =", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<Date> values) {
            addCriterion("insert_time in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<Date> values) {
            addCriterion("insert_time not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(Date value1, Date value2) {
            addCriterion("insert_time between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(Date value1, Date value2) {
            addCriterion("insert_time not between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}