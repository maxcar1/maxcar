package com.maxcar.tenant.app.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDealInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AddDealInfoExample() {
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

        public Criteria andCarManagerIsNull() {
            addCriterion("car_manager is null");
            return (Criteria) this;
        }

        public Criteria andCarManagerIsNotNull() {
            addCriterion("car_manager is not null");
            return (Criteria) this;
        }

        public Criteria andCarManagerEqualTo(String value) {
            addCriterion("car_manager =", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerNotEqualTo(String value) {
            addCriterion("car_manager <>", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerGreaterThan(String value) {
            addCriterion("car_manager >", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerGreaterThanOrEqualTo(String value) {
            addCriterion("car_manager >=", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerLessThan(String value) {
            addCriterion("car_manager <", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerLessThanOrEqualTo(String value) {
            addCriterion("car_manager <=", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerLike(String value) {
            addCriterion("car_manager like", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerNotLike(String value) {
            addCriterion("car_manager not like", value, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerIn(List<String> values) {
            addCriterion("car_manager in", values, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerNotIn(List<String> values) {
            addCriterion("car_manager not in", values, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerBetween(String value1, String value2) {
            addCriterion("car_manager between", value1, value2, "carManager");
            return (Criteria) this;
        }

        public Criteria andCarManagerNotBetween(String value1, String value2) {
            addCriterion("car_manager not between", value1, value2, "carManager");
            return (Criteria) this;
        }

        public Criteria andDealPriceIsNull() {
            addCriterion("deal_price is null");
            return (Criteria) this;
        }

        public Criteria andDealPriceIsNotNull() {
            addCriterion("deal_price is not null");
            return (Criteria) this;
        }

        public Criteria andDealPriceEqualTo(BigDecimal value) {
            addCriterion("deal_price =", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotEqualTo(BigDecimal value) {
            addCriterion("deal_price <>", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceGreaterThan(BigDecimal value) {
            addCriterion("deal_price >", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deal_price >=", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceLessThan(BigDecimal value) {
            addCriterion("deal_price <", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deal_price <=", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceIn(List<BigDecimal> values) {
            addCriterion("deal_price in", values, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotIn(List<BigDecimal> values) {
            addCriterion("deal_price not in", values, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deal_price between", value1, value2, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deal_price not between", value1, value2, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerIsNull() {
            addCriterion("burden_owner is null");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerIsNotNull() {
            addCriterion("burden_owner is not null");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerEqualTo(Byte value) {
            addCriterion("burden_owner =", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerNotEqualTo(Byte value) {
            addCriterion("burden_owner <>", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerGreaterThan(Byte value) {
            addCriterion("burden_owner >", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerGreaterThanOrEqualTo(Byte value) {
            addCriterion("burden_owner >=", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerLessThan(Byte value) {
            addCriterion("burden_owner <", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerLessThanOrEqualTo(Byte value) {
            addCriterion("burden_owner <=", value, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerIn(List<Byte> values) {
            addCriterion("burden_owner in", values, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerNotIn(List<Byte> values) {
            addCriterion("burden_owner not in", values, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerBetween(Byte value1, Byte value2) {
            addCriterion("burden_owner between", value1, value2, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andBurdenOwnerNotBetween(Byte value1, Byte value2) {
            addCriterion("burden_owner not between", value1, value2, "burdenOwner");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayIsNull() {
            addCriterion("buyer_check_day is null");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayIsNotNull() {
            addCriterion("buyer_check_day is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayEqualTo(Byte value) {
            addCriterion("buyer_check_day =", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayNotEqualTo(Byte value) {
            addCriterion("buyer_check_day <>", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayGreaterThan(Byte value) {
            addCriterion("buyer_check_day >", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayGreaterThanOrEqualTo(Byte value) {
            addCriterion("buyer_check_day >=", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayLessThan(Byte value) {
            addCriterion("buyer_check_day <", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayLessThanOrEqualTo(Byte value) {
            addCriterion("buyer_check_day <=", value, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayIn(List<Byte> values) {
            addCriterion("buyer_check_day in", values, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayNotIn(List<Byte> values) {
            addCriterion("buyer_check_day not in", values, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayBetween(Byte value1, Byte value2) {
            addCriterion("buyer_check_day between", value1, value2, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andBuyerCheckDayNotBetween(Byte value1, Byte value2) {
            addCriterion("buyer_check_day not between", value1, value2, "buyerCheckDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayIsNull() {
            addCriterion("seller_money_day is null");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayIsNotNull() {
            addCriterion("seller_money_day is not null");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayEqualTo(Byte value) {
            addCriterion("seller_money_day =", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayNotEqualTo(Byte value) {
            addCriterion("seller_money_day <>", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayGreaterThan(Byte value) {
            addCriterion("seller_money_day >", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayGreaterThanOrEqualTo(Byte value) {
            addCriterion("seller_money_day >=", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayLessThan(Byte value) {
            addCriterion("seller_money_day <", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayLessThanOrEqualTo(Byte value) {
            addCriterion("seller_money_day <=", value, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayIn(List<Byte> values) {
            addCriterion("seller_money_day in", values, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayNotIn(List<Byte> values) {
            addCriterion("seller_money_day not in", values, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayBetween(Byte value1, Byte value2) {
            addCriterion("seller_money_day between", value1, value2, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andSellerMoneyDayNotBetween(Byte value1, Byte value2) {
            addCriterion("seller_money_day not between", value1, value2, "sellerMoneyDay");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerIsNull() {
            addCriterion("trust_owner is null");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerIsNotNull() {
            addCriterion("trust_owner is not null");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerEqualTo(Byte value) {
            addCriterion("trust_owner =", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerNotEqualTo(Byte value) {
            addCriterion("trust_owner <>", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerGreaterThan(Byte value) {
            addCriterion("trust_owner >", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerGreaterThanOrEqualTo(Byte value) {
            addCriterion("trust_owner >=", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerLessThan(Byte value) {
            addCriterion("trust_owner <", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerLessThanOrEqualTo(Byte value) {
            addCriterion("trust_owner <=", value, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerIn(List<Byte> values) {
            addCriterion("trust_owner in", values, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerNotIn(List<Byte> values) {
            addCriterion("trust_owner not in", values, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerBetween(Byte value1, Byte value2) {
            addCriterion("trust_owner between", value1, value2, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andTrustOwnerNotBetween(Byte value1, Byte value2) {
            addCriterion("trust_owner not between", value1, value2, "trustOwner");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakIsNull() {
            addCriterion("buyer_break is null");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakIsNotNull() {
            addCriterion("buyer_break is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakEqualTo(BigDecimal value) {
            addCriterion("buyer_break =", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakNotEqualTo(BigDecimal value) {
            addCriterion("buyer_break <>", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakGreaterThan(BigDecimal value) {
            addCriterion("buyer_break >", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_break >=", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakLessThan(BigDecimal value) {
            addCriterion("buyer_break <", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_break <=", value, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakIn(List<BigDecimal> values) {
            addCriterion("buyer_break in", values, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakNotIn(List<BigDecimal> values) {
            addCriterion("buyer_break not in", values, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_break between", value1, value2, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andBuyerBreakNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_break not between", value1, value2, "buyerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakIsNull() {
            addCriterion("seller_break is null");
            return (Criteria) this;
        }

        public Criteria andSellerBreakIsNotNull() {
            addCriterion("seller_break is not null");
            return (Criteria) this;
        }

        public Criteria andSellerBreakEqualTo(BigDecimal value) {
            addCriterion("seller_break =", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakNotEqualTo(BigDecimal value) {
            addCriterion("seller_break <>", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakGreaterThan(BigDecimal value) {
            addCriterion("seller_break >", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_break >=", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakLessThan(BigDecimal value) {
            addCriterion("seller_break <", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_break <=", value, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakIn(List<BigDecimal> values) {
            addCriterion("seller_break in", values, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakNotIn(List<BigDecimal> values) {
            addCriterion("seller_break not in", values, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_break between", value1, value2, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andSellerBreakNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_break not between", value1, value2, "sellerBreak");
            return (Criteria) this;
        }

        public Criteria andDealOwnerIsNull() {
            addCriterion("deal_owner is null");
            return (Criteria) this;
        }

        public Criteria andDealOwnerIsNotNull() {
            addCriterion("deal_owner is not null");
            return (Criteria) this;
        }

        public Criteria andDealOwnerEqualTo(Byte value) {
            addCriterion("deal_owner =", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerNotEqualTo(Byte value) {
            addCriterion("deal_owner <>", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerGreaterThan(Byte value) {
            addCriterion("deal_owner >", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerGreaterThanOrEqualTo(Byte value) {
            addCriterion("deal_owner >=", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerLessThan(Byte value) {
            addCriterion("deal_owner <", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerLessThanOrEqualTo(Byte value) {
            addCriterion("deal_owner <=", value, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerIn(List<Byte> values) {
            addCriterion("deal_owner in", values, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerNotIn(List<Byte> values) {
            addCriterion("deal_owner not in", values, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerBetween(Byte value1, Byte value2) {
            addCriterion("deal_owner between", value1, value2, "dealOwner");
            return (Criteria) this;
        }

        public Criteria andDealOwnerNotBetween(Byte value1, Byte value2) {
            addCriterion("deal_owner not between", value1, value2, "dealOwner");
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