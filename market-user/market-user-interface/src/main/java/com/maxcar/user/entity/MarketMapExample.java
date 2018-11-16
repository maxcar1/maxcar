package com.maxcar.user.entity;

import java.util.ArrayList;
import java.util.List;

public class MarketMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MarketMapExample() {
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

        public Criteria andEquipmentNameMapIsNull() {
            addCriterion("equipment_name_map is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapIsNotNull() {
            addCriterion("equipment_name_map is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapEqualTo(String value) {
            addCriterion("equipment_name_map =", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapNotEqualTo(String value) {
            addCriterion("equipment_name_map <>", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapGreaterThan(String value) {
            addCriterion("equipment_name_map >", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_name_map >=", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapLessThan(String value) {
            addCriterion("equipment_name_map <", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapLessThanOrEqualTo(String value) {
            addCriterion("equipment_name_map <=", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapLike(String value) {
            addCriterion("equipment_name_map like", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapNotLike(String value) {
            addCriterion("equipment_name_map not like", value, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapIn(List<String> values) {
            addCriterion("equipment_name_map in", values, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapNotIn(List<String> values) {
            addCriterion("equipment_name_map not in", values, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapBetween(String value1, String value2) {
            addCriterion("equipment_name_map between", value1, value2, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameMapNotBetween(String value1, String value2) {
            addCriterion("equipment_name_map not between", value1, value2, "equipmentNameMap");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaIsNull() {
            addCriterion("building_no_area is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaIsNotNull() {
            addCriterion("building_no_area is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaEqualTo(String value) {
            addCriterion("building_no_area =", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaNotEqualTo(String value) {
            addCriterion("building_no_area <>", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaGreaterThan(String value) {
            addCriterion("building_no_area >", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaGreaterThanOrEqualTo(String value) {
            addCriterion("building_no_area >=", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaLessThan(String value) {
            addCriterion("building_no_area <", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaLessThanOrEqualTo(String value) {
            addCriterion("building_no_area <=", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaLike(String value) {
            addCriterion("building_no_area like", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaNotLike(String value) {
            addCriterion("building_no_area not like", value, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaIn(List<String> values) {
            addCriterion("building_no_area in", values, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaNotIn(List<String> values) {
            addCriterion("building_no_area not in", values, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaBetween(String value1, String value2) {
            addCriterion("building_no_area between", value1, value2, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoAreaNotBetween(String value1, String value2) {
            addCriterion("building_no_area not between", value1, value2, "buildingNoArea");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIsNull() {
            addCriterion("building_no is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIsNotNull() {
            addCriterion("building_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNoEqualTo(String value) {
            addCriterion("building_no =", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotEqualTo(String value) {
            addCriterion("building_no <>", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoGreaterThan(String value) {
            addCriterion("building_no >", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoGreaterThanOrEqualTo(String value) {
            addCriterion("building_no >=", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLessThan(String value) {
            addCriterion("building_no <", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLessThanOrEqualTo(String value) {
            addCriterion("building_no <=", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoLike(String value) {
            addCriterion("building_no like", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotLike(String value) {
            addCriterion("building_no not like", value, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoIn(List<String> values) {
            addCriterion("building_no in", values, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotIn(List<String> values) {
            addCriterion("building_no not in", values, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoBetween(String value1, String value2) {
            addCriterion("building_no between", value1, value2, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andBuildingNoNotBetween(String value1, String value2) {
            addCriterion("building_no not between", value1, value2, "buildingNo");
            return (Criteria) this;
        }

        public Criteria andFmapIdIsNull() {
            addCriterion("fmap_id is null");
            return (Criteria) this;
        }

        public Criteria andFmapIdIsNotNull() {
            addCriterion("fmap_id is not null");
            return (Criteria) this;
        }

        public Criteria andFmapIdEqualTo(String value) {
            addCriterion("fmap_id =", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdNotEqualTo(String value) {
            addCriterion("fmap_id <>", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdGreaterThan(String value) {
            addCriterion("fmap_id >", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdGreaterThanOrEqualTo(String value) {
            addCriterion("fmap_id >=", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdLessThan(String value) {
            addCriterion("fmap_id <", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdLessThanOrEqualTo(String value) {
            addCriterion("fmap_id <=", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdLike(String value) {
            addCriterion("fmap_id like", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdNotLike(String value) {
            addCriterion("fmap_id not like", value, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdIn(List<String> values) {
            addCriterion("fmap_id in", values, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdNotIn(List<String> values) {
            addCriterion("fmap_id not in", values, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdBetween(String value1, String value2) {
            addCriterion("fmap_id between", value1, value2, "fmapId");
            return (Criteria) this;
        }

        public Criteria andFmapIdNotBetween(String value1, String value2) {
            addCriterion("fmap_id not between", value1, value2, "fmapId");
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

        public Criteria andIsvalidEqualTo(Integer value) {
            addCriterion("isvalid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Integer value) {
            addCriterion("isvalid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Integer value) {
            addCriterion("isvalid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("isvalid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Integer value) {
            addCriterion("isvalid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Integer value) {
            addCriterion("isvalid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Integer> values) {
            addCriterion("isvalid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Integer> values) {
            addCriterion("isvalid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Integer value1, Integer value2) {
            addCriterion("isvalid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("isvalid not between", value1, value2, "isvalid");
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