package com.maxcar.tenant.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuySellInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuySellInfoExample() {
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

        public Criteria andSellerTypeIsNull() {
            addCriterion("seller_type is null");
            return (Criteria) this;
        }

        public Criteria andSellerTypeIsNotNull() {
            addCriterion("seller_type is not null");
            return (Criteria) this;
        }

        public Criteria andSellerTypeEqualTo(Integer value) {
            addCriterion("seller_type =", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotEqualTo(Integer value) {
            addCriterion("seller_type <>", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeGreaterThan(Integer value) {
            addCriterion("seller_type >", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_type >=", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeLessThan(Integer value) {
            addCriterion("seller_type <", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("seller_type <=", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeIn(List<Integer> values) {
            addCriterion("seller_type in", values, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotIn(List<Integer> values) {
            addCriterion("seller_type not in", values, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeBetween(Integer value1, Integer value2) {
            addCriterion("seller_type between", value1, value2, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_type not between", value1, value2, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNull() {
            addCriterion("seller_name is null");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNotNull() {
            addCriterion("seller_name is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNameEqualTo(String value) {
            addCriterion("seller_name =", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotEqualTo(String value) {
            addCriterion("seller_name <>", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThan(String value) {
            addCriterion("seller_name >", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThanOrEqualTo(String value) {
            addCriterion("seller_name >=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThan(String value) {
            addCriterion("seller_name <", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThanOrEqualTo(String value) {
            addCriterion("seller_name <=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLike(String value) {
            addCriterion("seller_name like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotLike(String value) {
            addCriterion("seller_name not like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameIn(List<String> values) {
            addCriterion("seller_name in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotIn(List<String> values) {
            addCriterion("seller_name not in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameBetween(String value1, String value2) {
            addCriterion("seller_name between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotBetween(String value1, String value2) {
            addCriterion("seller_name not between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardIsNull() {
            addCriterion("seller_idcard is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardIsNotNull() {
            addCriterion("seller_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardEqualTo(String value) {
            addCriterion("seller_idcard =", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardNotEqualTo(String value) {
            addCriterion("seller_idcard <>", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardGreaterThan(String value) {
            addCriterion("seller_idcard >", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("seller_idcard >=", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardLessThan(String value) {
            addCriterion("seller_idcard <", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardLessThanOrEqualTo(String value) {
            addCriterion("seller_idcard <=", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardLike(String value) {
            addCriterion("seller_idcard like", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardNotLike(String value) {
            addCriterion("seller_idcard not like", value, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardIn(List<String> values) {
            addCriterion("seller_idcard in", values, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardNotIn(List<String> values) {
            addCriterion("seller_idcard not in", values, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardBetween(String value1, String value2) {
            addCriterion("seller_idcard between", value1, value2, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardNotBetween(String value1, String value2) {
            addCriterion("seller_idcard not between", value1, value2, "sellerIdcard");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressIsNull() {
            addCriterion("seller_idcard_address is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressIsNotNull() {
            addCriterion("seller_idcard_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressEqualTo(String value) {
            addCriterion("seller_idcard_address =", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressNotEqualTo(String value) {
            addCriterion("seller_idcard_address <>", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressGreaterThan(String value) {
            addCriterion("seller_idcard_address >", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressGreaterThanOrEqualTo(String value) {
            addCriterion("seller_idcard_address >=", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressLessThan(String value) {
            addCriterion("seller_idcard_address <", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressLessThanOrEqualTo(String value) {
            addCriterion("seller_idcard_address <=", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressLike(String value) {
            addCriterion("seller_idcard_address like", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressNotLike(String value) {
            addCriterion("seller_idcard_address not like", value, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressIn(List<String> values) {
            addCriterion("seller_idcard_address in", values, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressNotIn(List<String> values) {
            addCriterion("seller_idcard_address not in", values, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressBetween(String value1, String value2) {
            addCriterion("seller_idcard_address between", value1, value2, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardAddressNotBetween(String value1, String value2) {
            addCriterion("seller_idcard_address not between", value1, value2, "sellerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIsNull() {
            addCriterion("seller_phone is null");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIsNotNull() {
            addCriterion("seller_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneEqualTo(String value) {
            addCriterion("seller_phone =", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotEqualTo(String value) {
            addCriterion("seller_phone <>", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneGreaterThan(String value) {
            addCriterion("seller_phone >", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("seller_phone >=", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLessThan(String value) {
            addCriterion("seller_phone <", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLessThanOrEqualTo(String value) {
            addCriterion("seller_phone <=", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLike(String value) {
            addCriterion("seller_phone like", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotLike(String value) {
            addCriterion("seller_phone not like", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIn(List<String> values) {
            addCriterion("seller_phone in", values, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotIn(List<String> values) {
            addCriterion("seller_phone not in", values, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneBetween(String value1, String value2) {
            addCriterion("seller_phone between", value1, value2, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotBetween(String value1, String value2) {
            addCriterion("seller_phone not between", value1, value2, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontIsNull() {
            addCriterion("seller_idcard_front is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontIsNotNull() {
            addCriterion("seller_idcard_front is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontEqualTo(String value) {
            addCriterion("seller_idcard_front =", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontNotEqualTo(String value) {
            addCriterion("seller_idcard_front <>", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontGreaterThan(String value) {
            addCriterion("seller_idcard_front >", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("seller_idcard_front >=", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontLessThan(String value) {
            addCriterion("seller_idcard_front <", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontLessThanOrEqualTo(String value) {
            addCriterion("seller_idcard_front <=", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontLike(String value) {
            addCriterion("seller_idcard_front like", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontNotLike(String value) {
            addCriterion("seller_idcard_front not like", value, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontIn(List<String> values) {
            addCriterion("seller_idcard_front in", values, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontNotIn(List<String> values) {
            addCriterion("seller_idcard_front not in", values, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontBetween(String value1, String value2) {
            addCriterion("seller_idcard_front between", value1, value2, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardFrontNotBetween(String value1, String value2) {
            addCriterion("seller_idcard_front not between", value1, value2, "sellerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseIsNull() {
            addCriterion("seller_idcard_reverse is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseIsNotNull() {
            addCriterion("seller_idcard_reverse is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseEqualTo(String value) {
            addCriterion("seller_idcard_reverse =", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseNotEqualTo(String value) {
            addCriterion("seller_idcard_reverse <>", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseGreaterThan(String value) {
            addCriterion("seller_idcard_reverse >", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseGreaterThanOrEqualTo(String value) {
            addCriterion("seller_idcard_reverse >=", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseLessThan(String value) {
            addCriterion("seller_idcard_reverse <", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseLessThanOrEqualTo(String value) {
            addCriterion("seller_idcard_reverse <=", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseLike(String value) {
            addCriterion("seller_idcard_reverse like", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseNotLike(String value) {
            addCriterion("seller_idcard_reverse not like", value, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseIn(List<String> values) {
            addCriterion("seller_idcard_reverse in", values, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseNotIn(List<String> values) {
            addCriterion("seller_idcard_reverse not in", values, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseBetween(String value1, String value2) {
            addCriterion("seller_idcard_reverse between", value1, value2, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerIdcardReverseNotBetween(String value1, String value2) {
            addCriterion("seller_idcard_reverse not between", value1, value2, "sellerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyIsNull() {
            addCriterion("seller_agency is null");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyIsNotNull() {
            addCriterion("seller_agency is not null");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyEqualTo(String value) {
            addCriterion("seller_agency =", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyNotEqualTo(String value) {
            addCriterion("seller_agency <>", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyGreaterThan(String value) {
            addCriterion("seller_agency >", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyGreaterThanOrEqualTo(String value) {
            addCriterion("seller_agency >=", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyLessThan(String value) {
            addCriterion("seller_agency <", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyLessThanOrEqualTo(String value) {
            addCriterion("seller_agency <=", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyLike(String value) {
            addCriterion("seller_agency like", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyNotLike(String value) {
            addCriterion("seller_agency not like", value, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyIn(List<String> values) {
            addCriterion("seller_agency in", values, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyNotIn(List<String> values) {
            addCriterion("seller_agency not in", values, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyBetween(String value1, String value2) {
            addCriterion("seller_agency between", value1, value2, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerAgencyNotBetween(String value1, String value2) {
            addCriterion("seller_agency not between", value1, value2, "sellerAgency");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeIsNull() {
            addCriterion("seller_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeIsNotNull() {
            addCriterion("seller_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeEqualTo(String value) {
            addCriterion("seller_credit_code =", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeNotEqualTo(String value) {
            addCriterion("seller_credit_code <>", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeGreaterThan(String value) {
            addCriterion("seller_credit_code >", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("seller_credit_code >=", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeLessThan(String value) {
            addCriterion("seller_credit_code <", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("seller_credit_code <=", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeLike(String value) {
            addCriterion("seller_credit_code like", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeNotLike(String value) {
            addCriterion("seller_credit_code not like", value, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeIn(List<String> values) {
            addCriterion("seller_credit_code in", values, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeNotIn(List<String> values) {
            addCriterion("seller_credit_code not in", values, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeBetween(String value1, String value2) {
            addCriterion("seller_credit_code between", value1, value2, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerCreditCodeNotBetween(String value1, String value2) {
            addCriterion("seller_credit_code not between", value1, value2, "sellerCreditCode");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNull() {
            addCriterion("seller_address is null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNotNull() {
            addCriterion("seller_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressEqualTo(String value) {
            addCriterion("seller_address =", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotEqualTo(String value) {
            addCriterion("seller_address <>", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThan(String value) {
            addCriterion("seller_address >", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("seller_address >=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThan(String value) {
            addCriterion("seller_address <", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThanOrEqualTo(String value) {
            addCriterion("seller_address <=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLike(String value) {
            addCriterion("seller_address like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotLike(String value) {
            addCriterion("seller_address not like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIn(List<String> values) {
            addCriterion("seller_address in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotIn(List<String> values) {
            addCriterion("seller_address not in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressBetween(String value1, String value2) {
            addCriterion("seller_address between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotBetween(String value1, String value2) {
            addCriterion("seller_address not between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseIsNull() {
            addCriterion("seller_business_license is null");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseIsNotNull() {
            addCriterion("seller_business_license is not null");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseEqualTo(String value) {
            addCriterion("seller_business_license =", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseNotEqualTo(String value) {
            addCriterion("seller_business_license <>", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseGreaterThan(String value) {
            addCriterion("seller_business_license >", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("seller_business_license >=", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseLessThan(String value) {
            addCriterion("seller_business_license <", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("seller_business_license <=", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseLike(String value) {
            addCriterion("seller_business_license like", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseNotLike(String value) {
            addCriterion("seller_business_license not like", value, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseIn(List<String> values) {
            addCriterion("seller_business_license in", values, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseNotIn(List<String> values) {
            addCriterion("seller_business_license not in", values, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseBetween(String value1, String value2) {
            addCriterion("seller_business_license between", value1, value2, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andSellerBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("seller_business_license not between", value1, value2, "sellerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeIsNull() {
            addCriterion("buyer_type is null");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeIsNotNull() {
            addCriterion("buyer_type is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeEqualTo(Byte value) {
            addCriterion("buyer_type =", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeNotEqualTo(Byte value) {
            addCriterion("buyer_type <>", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeGreaterThan(Byte value) {
            addCriterion("buyer_type >", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("buyer_type >=", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeLessThan(Byte value) {
            addCriterion("buyer_type <", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeLessThanOrEqualTo(Byte value) {
            addCriterion("buyer_type <=", value, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeIn(List<Byte> values) {
            addCriterion("buyer_type in", values, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeNotIn(List<Byte> values) {
            addCriterion("buyer_type not in", values, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeBetween(Byte value1, Byte value2) {
            addCriterion("buyer_type between", value1, value2, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("buyer_type not between", value1, value2, "buyerType");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNull() {
            addCriterion("buyer_name is null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNotNull() {
            addCriterion("buyer_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameEqualTo(String value) {
            addCriterion("buyer_name =", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotEqualTo(String value) {
            addCriterion("buyer_name <>", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThan(String value) {
            addCriterion("buyer_name >", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_name >=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThan(String value) {
            addCriterion("buyer_name <", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThanOrEqualTo(String value) {
            addCriterion("buyer_name <=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLike(String value) {
            addCriterion("buyer_name like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotLike(String value) {
            addCriterion("buyer_name not like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIn(List<String> values) {
            addCriterion("buyer_name in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotIn(List<String> values) {
            addCriterion("buyer_name not in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameBetween(String value1, String value2) {
            addCriterion("buyer_name between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotBetween(String value1, String value2) {
            addCriterion("buyer_name not between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardIsNull() {
            addCriterion("buyer_idcard is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardIsNotNull() {
            addCriterion("buyer_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardEqualTo(String value) {
            addCriterion("buyer_idcard =", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardNotEqualTo(String value) {
            addCriterion("buyer_idcard <>", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardGreaterThan(String value) {
            addCriterion("buyer_idcard >", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_idcard >=", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardLessThan(String value) {
            addCriterion("buyer_idcard <", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardLessThanOrEqualTo(String value) {
            addCriterion("buyer_idcard <=", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardLike(String value) {
            addCriterion("buyer_idcard like", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardNotLike(String value) {
            addCriterion("buyer_idcard not like", value, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardIn(List<String> values) {
            addCriterion("buyer_idcard in", values, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardNotIn(List<String> values) {
            addCriterion("buyer_idcard not in", values, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardBetween(String value1, String value2) {
            addCriterion("buyer_idcard between", value1, value2, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardNotBetween(String value1, String value2) {
            addCriterion("buyer_idcard not between", value1, value2, "buyerIdcard");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressIsNull() {
            addCriterion("buyer_idcard_address is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressIsNotNull() {
            addCriterion("buyer_idcard_address is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressEqualTo(String value) {
            addCriterion("buyer_idcard_address =", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressNotEqualTo(String value) {
            addCriterion("buyer_idcard_address <>", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressGreaterThan(String value) {
            addCriterion("buyer_idcard_address >", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_address >=", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressLessThan(String value) {
            addCriterion("buyer_idcard_address <", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressLessThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_address <=", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressLike(String value) {
            addCriterion("buyer_idcard_address like", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressNotLike(String value) {
            addCriterion("buyer_idcard_address not like", value, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressIn(List<String> values) {
            addCriterion("buyer_idcard_address in", values, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressNotIn(List<String> values) {
            addCriterion("buyer_idcard_address not in", values, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressBetween(String value1, String value2) {
            addCriterion("buyer_idcard_address between", value1, value2, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardAddressNotBetween(String value1, String value2) {
            addCriterion("buyer_idcard_address not between", value1, value2, "buyerIdcardAddress");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneIsNull() {
            addCriterion("buyer_phone is null");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneIsNotNull() {
            addCriterion("buyer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneEqualTo(String value) {
            addCriterion("buyer_phone =", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneNotEqualTo(String value) {
            addCriterion("buyer_phone <>", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneGreaterThan(String value) {
            addCriterion("buyer_phone >", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_phone >=", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneLessThan(String value) {
            addCriterion("buyer_phone <", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneLessThanOrEqualTo(String value) {
            addCriterion("buyer_phone <=", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneLike(String value) {
            addCriterion("buyer_phone like", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneNotLike(String value) {
            addCriterion("buyer_phone not like", value, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneIn(List<String> values) {
            addCriterion("buyer_phone in", values, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneNotIn(List<String> values) {
            addCriterion("buyer_phone not in", values, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneBetween(String value1, String value2) {
            addCriterion("buyer_phone between", value1, value2, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerPhoneNotBetween(String value1, String value2) {
            addCriterion("buyer_phone not between", value1, value2, "buyerPhone");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontIsNull() {
            addCriterion("buyer_idcard_front is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontIsNotNull() {
            addCriterion("buyer_idcard_front is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontEqualTo(String value) {
            addCriterion("buyer_idcard_front =", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontNotEqualTo(String value) {
            addCriterion("buyer_idcard_front <>", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontGreaterThan(String value) {
            addCriterion("buyer_idcard_front >", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_front >=", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontLessThan(String value) {
            addCriterion("buyer_idcard_front <", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontLessThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_front <=", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontLike(String value) {
            addCriterion("buyer_idcard_front like", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontNotLike(String value) {
            addCriterion("buyer_idcard_front not like", value, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontIn(List<String> values) {
            addCriterion("buyer_idcard_front in", values, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontNotIn(List<String> values) {
            addCriterion("buyer_idcard_front not in", values, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontBetween(String value1, String value2) {
            addCriterion("buyer_idcard_front between", value1, value2, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardFrontNotBetween(String value1, String value2) {
            addCriterion("buyer_idcard_front not between", value1, value2, "buyerIdcardFront");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseIsNull() {
            addCriterion("buyer_idcard_reverse is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseIsNotNull() {
            addCriterion("buyer_idcard_reverse is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseEqualTo(String value) {
            addCriterion("buyer_idcard_reverse =", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseNotEqualTo(String value) {
            addCriterion("buyer_idcard_reverse <>", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseGreaterThan(String value) {
            addCriterion("buyer_idcard_reverse >", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_reverse >=", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseLessThan(String value) {
            addCriterion("buyer_idcard_reverse <", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseLessThanOrEqualTo(String value) {
            addCriterion("buyer_idcard_reverse <=", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseLike(String value) {
            addCriterion("buyer_idcard_reverse like", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseNotLike(String value) {
            addCriterion("buyer_idcard_reverse not like", value, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseIn(List<String> values) {
            addCriterion("buyer_idcard_reverse in", values, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseNotIn(List<String> values) {
            addCriterion("buyer_idcard_reverse not in", values, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseBetween(String value1, String value2) {
            addCriterion("buyer_idcard_reverse between", value1, value2, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerIdcardReverseNotBetween(String value1, String value2) {
            addCriterion("buyer_idcard_reverse not between", value1, value2, "buyerIdcardReverse");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyIsNull() {
            addCriterion("buyer_agency is null");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyIsNotNull() {
            addCriterion("buyer_agency is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyEqualTo(String value) {
            addCriterion("buyer_agency =", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyNotEqualTo(String value) {
            addCriterion("buyer_agency <>", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyGreaterThan(String value) {
            addCriterion("buyer_agency >", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_agency >=", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyLessThan(String value) {
            addCriterion("buyer_agency <", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyLessThanOrEqualTo(String value) {
            addCriterion("buyer_agency <=", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyLike(String value) {
            addCriterion("buyer_agency like", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyNotLike(String value) {
            addCriterion("buyer_agency not like", value, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyIn(List<String> values) {
            addCriterion("buyer_agency in", values, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyNotIn(List<String> values) {
            addCriterion("buyer_agency not in", values, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyBetween(String value1, String value2) {
            addCriterion("buyer_agency between", value1, value2, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerAgencyNotBetween(String value1, String value2) {
            addCriterion("buyer_agency not between", value1, value2, "buyerAgency");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeIsNull() {
            addCriterion("buyer_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeIsNotNull() {
            addCriterion("buyer_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeEqualTo(String value) {
            addCriterion("buyer_credit_code =", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeNotEqualTo(String value) {
            addCriterion("buyer_credit_code <>", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeGreaterThan(String value) {
            addCriterion("buyer_credit_code >", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_credit_code >=", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeLessThan(String value) {
            addCriterion("buyer_credit_code <", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("buyer_credit_code <=", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeLike(String value) {
            addCriterion("buyer_credit_code like", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeNotLike(String value) {
            addCriterion("buyer_credit_code not like", value, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeIn(List<String> values) {
            addCriterion("buyer_credit_code in", values, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeNotIn(List<String> values) {
            addCriterion("buyer_credit_code not in", values, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeBetween(String value1, String value2) {
            addCriterion("buyer_credit_code between", value1, value2, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerCreditCodeNotBetween(String value1, String value2) {
            addCriterion("buyer_credit_code not between", value1, value2, "buyerCreditCode");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseIsNull() {
            addCriterion("buyer_business_license is null");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseIsNotNull() {
            addCriterion("buyer_business_license is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseEqualTo(String value) {
            addCriterion("buyer_business_license =", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseNotEqualTo(String value) {
            addCriterion("buyer_business_license <>", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseGreaterThan(String value) {
            addCriterion("buyer_business_license >", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_business_license >=", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseLessThan(String value) {
            addCriterion("buyer_business_license <", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("buyer_business_license <=", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseLike(String value) {
            addCriterion("buyer_business_license like", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseNotLike(String value) {
            addCriterion("buyer_business_license not like", value, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseIn(List<String> values) {
            addCriterion("buyer_business_license in", values, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseNotIn(List<String> values) {
            addCriterion("buyer_business_license not in", values, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseBetween(String value1, String value2) {
            addCriterion("buyer_business_license between", value1, value2, "buyerBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andBuyerBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("buyer_business_license not between", value1, value2, "buyerBusinessLicense");
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