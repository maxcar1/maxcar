package com.maxcar.base.pojo.taobao;

import java.util.ArrayList;
import java.util.List;

public class TaobaoMarketConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaobaoMarketConfigExample() {
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

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNull() {
            addCriterion("market_name is null");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNotNull() {
            addCriterion("market_name is not null");
            return (Criteria) this;
        }

        public Criteria andMarketNameEqualTo(String value) {
            addCriterion("market_name =", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotEqualTo(String value) {
            addCriterion("market_name <>", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThan(String value) {
            addCriterion("market_name >", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThanOrEqualTo(String value) {
            addCriterion("market_name >=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThan(String value) {
            addCriterion("market_name <", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThanOrEqualTo(String value) {
            addCriterion("market_name <=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLike(String value) {
            addCriterion("market_name like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotLike(String value) {
            addCriterion("market_name not like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameIn(List<String> values) {
            addCriterion("market_name in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotIn(List<String> values) {
            addCriterion("market_name not in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameBetween(String value1, String value2) {
            addCriterion("market_name between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotBetween(String value1, String value2) {
            addCriterion("market_name not between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIsNull() {
            addCriterion("session_key is null");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIsNotNull() {
            addCriterion("session_key is not null");
            return (Criteria) this;
        }

        public Criteria andSessionKeyEqualTo(String value) {
            addCriterion("session_key =", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotEqualTo(String value) {
            addCriterion("session_key <>", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyGreaterThan(String value) {
            addCriterion("session_key >", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyGreaterThanOrEqualTo(String value) {
            addCriterion("session_key >=", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLessThan(String value) {
            addCriterion("session_key <", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLessThanOrEqualTo(String value) {
            addCriterion("session_key <=", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyLike(String value) {
            addCriterion("session_key like", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotLike(String value) {
            addCriterion("session_key not like", value, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyIn(List<String> values) {
            addCriterion("session_key in", values, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotIn(List<String> values) {
            addCriterion("session_key not in", values, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyBetween(String value1, String value2) {
            addCriterion("session_key between", value1, value2, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andSessionKeyNotBetween(String value1, String value2) {
            addCriterion("session_key not between", value1, value2, "sessionKey");
            return (Criteria) this;
        }

        public Criteria andCheckSourceIsNull() {
            addCriterion("check_source is null");
            return (Criteria) this;
        }

        public Criteria andCheckSourceIsNotNull() {
            addCriterion("check_source is not null");
            return (Criteria) this;
        }

        public Criteria andCheckSourceEqualTo(Integer value) {
            addCriterion("check_source =", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceNotEqualTo(Integer value) {
            addCriterion("check_source <>", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceGreaterThan(Integer value) {
            addCriterion("check_source >", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_source >=", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceLessThan(Integer value) {
            addCriterion("check_source <", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceLessThanOrEqualTo(Integer value) {
            addCriterion("check_source <=", value, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceIn(List<Integer> values) {
            addCriterion("check_source in", values, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceNotIn(List<Integer> values) {
            addCriterion("check_source not in", values, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceBetween(Integer value1, Integer value2) {
            addCriterion("check_source between", value1, value2, "checkSource");
            return (Criteria) this;
        }

        public Criteria andCheckSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("check_source not between", value1, value2, "checkSource");
            return (Criteria) this;
        }

        public Criteria andFtlNameIsNull() {
            addCriterion("ftl_name is null");
            return (Criteria) this;
        }

        public Criteria andFtlNameIsNotNull() {
            addCriterion("ftl_name is not null");
            return (Criteria) this;
        }

        public Criteria andFtlNameEqualTo(String value) {
            addCriterion("ftl_name =", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameNotEqualTo(String value) {
            addCriterion("ftl_name <>", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameGreaterThan(String value) {
            addCriterion("ftl_name >", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameGreaterThanOrEqualTo(String value) {
            addCriterion("ftl_name >=", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameLessThan(String value) {
            addCriterion("ftl_name <", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameLessThanOrEqualTo(String value) {
            addCriterion("ftl_name <=", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameLike(String value) {
            addCriterion("ftl_name like", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameNotLike(String value) {
            addCriterion("ftl_name not like", value, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameIn(List<String> values) {
            addCriterion("ftl_name in", values, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameNotIn(List<String> values) {
            addCriterion("ftl_name not in", values, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameBetween(String value1, String value2) {
            addCriterion("ftl_name between", value1, value2, "ftlName");
            return (Criteria) this;
        }

        public Criteria andFtlNameNotBetween(String value1, String value2) {
            addCriterion("ftl_name not between", value1, value2, "ftlName");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIsNull() {
            addCriterion("market_phone is null");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIsNotNull() {
            addCriterion("market_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneEqualTo(String value) {
            addCriterion("market_phone =", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotEqualTo(String value) {
            addCriterion("market_phone <>", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneGreaterThan(String value) {
            addCriterion("market_phone >", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("market_phone >=", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLessThan(String value) {
            addCriterion("market_phone <", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLessThanOrEqualTo(String value) {
            addCriterion("market_phone <=", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLike(String value) {
            addCriterion("market_phone like", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotLike(String value) {
            addCriterion("market_phone not like", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIn(List<String> values) {
            addCriterion("market_phone in", values, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotIn(List<String> values) {
            addCriterion("market_phone not in", values, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneBetween(String value1, String value2) {
            addCriterion("market_phone between", value1, value2, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotBetween(String value1, String value2) {
            addCriterion("market_phone not between", value1, value2, "marketPhone");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStuffStatusIsNull() {
            addCriterion("stuff_status is null");
            return (Criteria) this;
        }

        public Criteria andStuffStatusIsNotNull() {
            addCriterion("stuff_status is not null");
            return (Criteria) this;
        }

        public Criteria andStuffStatusEqualTo(String value) {
            addCriterion("stuff_status =", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusNotEqualTo(String value) {
            addCriterion("stuff_status <>", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusGreaterThan(String value) {
            addCriterion("stuff_status >", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stuff_status >=", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusLessThan(String value) {
            addCriterion("stuff_status <", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusLessThanOrEqualTo(String value) {
            addCriterion("stuff_status <=", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusLike(String value) {
            addCriterion("stuff_status like", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusNotLike(String value) {
            addCriterion("stuff_status not like", value, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusIn(List<String> values) {
            addCriterion("stuff_status in", values, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusNotIn(List<String> values) {
            addCriterion("stuff_status not in", values, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusBetween(String value1, String value2) {
            addCriterion("stuff_status between", value1, value2, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andStuffStatusNotBetween(String value1, String value2) {
            addCriterion("stuff_status not between", value1, value2, "stuffStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNull() {
            addCriterion("approve_status is null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNotNull() {
            addCriterion("approve_status is not null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusEqualTo(String value) {
            addCriterion("approve_status =", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotEqualTo(String value) {
            addCriterion("approve_status <>", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThan(String value) {
            addCriterion("approve_status >", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("approve_status >=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThan(String value) {
            addCriterion("approve_status <", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThanOrEqualTo(String value) {
            addCriterion("approve_status <=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLike(String value) {
            addCriterion("approve_status like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotLike(String value) {
            addCriterion("approve_status not like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIn(List<String> values) {
            addCriterion("approve_status in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotIn(List<String> values) {
            addCriterion("approve_status not in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusBetween(String value1, String value2) {
            addCriterion("approve_status between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotBetween(String value1, String value2) {
            addCriterion("approve_status not between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateIsNull() {
            addCriterion("locality_life_expirydate is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateIsNotNull() {
            addCriterion("locality_life_expirydate is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateEqualTo(String value) {
            addCriterion("locality_life_expirydate =", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateNotEqualTo(String value) {
            addCriterion("locality_life_expirydate <>", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateGreaterThan(String value) {
            addCriterion("locality_life_expirydate >", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateGreaterThanOrEqualTo(String value) {
            addCriterion("locality_life_expirydate >=", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateLessThan(String value) {
            addCriterion("locality_life_expirydate <", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateLessThanOrEqualTo(String value) {
            addCriterion("locality_life_expirydate <=", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateLike(String value) {
            addCriterion("locality_life_expirydate like", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateNotLike(String value) {
            addCriterion("locality_life_expirydate not like", value, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateIn(List<String> values) {
            addCriterion("locality_life_expirydate in", values, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateNotIn(List<String> values) {
            addCriterion("locality_life_expirydate not in", values, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateBetween(String value1, String value2) {
            addCriterion("locality_life_expirydate between", value1, value2, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeExpirydateNotBetween(String value1, String value2) {
            addCriterion("locality_life_expirydate not between", value1, value2, "localityLifeExpirydate");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIsNull() {
            addCriterion("is_offline is null");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIsNotNull() {
            addCriterion("is_offline is not null");
            return (Criteria) this;
        }

        public Criteria andIsOfflineEqualTo(String value) {
            addCriterion("is_offline =", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotEqualTo(String value) {
            addCriterion("is_offline <>", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineGreaterThan(String value) {
            addCriterion("is_offline >", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineGreaterThanOrEqualTo(String value) {
            addCriterion("is_offline >=", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineLessThan(String value) {
            addCriterion("is_offline <", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineLessThanOrEqualTo(String value) {
            addCriterion("is_offline <=", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineLike(String value) {
            addCriterion("is_offline like", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotLike(String value) {
            addCriterion("is_offline not like", value, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineIn(List<String> values) {
            addCriterion("is_offline in", values, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotIn(List<String> values) {
            addCriterion("is_offline not in", values, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineBetween(String value1, String value2) {
            addCriterion("is_offline between", value1, value2, "isOffline");
            return (Criteria) this;
        }

        public Criteria andIsOfflineNotBetween(String value1, String value2) {
            addCriterion("is_offline not between", value1, value2, "isOffline");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsIsNull() {
            addCriterion("sku_outer_ids is null");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsIsNotNull() {
            addCriterion("sku_outer_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsEqualTo(String value) {
            addCriterion("sku_outer_ids =", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsNotEqualTo(String value) {
            addCriterion("sku_outer_ids <>", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsGreaterThan(String value) {
            addCriterion("sku_outer_ids >", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsGreaterThanOrEqualTo(String value) {
            addCriterion("sku_outer_ids >=", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsLessThan(String value) {
            addCriterion("sku_outer_ids <", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsLessThanOrEqualTo(String value) {
            addCriterion("sku_outer_ids <=", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsLike(String value) {
            addCriterion("sku_outer_ids like", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsNotLike(String value) {
            addCriterion("sku_outer_ids not like", value, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsIn(List<String> values) {
            addCriterion("sku_outer_ids in", values, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsNotIn(List<String> values) {
            addCriterion("sku_outer_ids not in", values, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsBetween(String value1, String value2) {
            addCriterion("sku_outer_ids between", value1, value2, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andSkuOuterIdsNotBetween(String value1, String value2) {
            addCriterion("sku_outer_ids not between", value1, value2, "skuOuterIds");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andAuctionPointIsNull() {
            addCriterion("auction_point is null");
            return (Criteria) this;
        }

        public Criteria andAuctionPointIsNotNull() {
            addCriterion("auction_point is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionPointEqualTo(Integer value) {
            addCriterion("auction_point =", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointNotEqualTo(Integer value) {
            addCriterion("auction_point <>", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointGreaterThan(Integer value) {
            addCriterion("auction_point >", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("auction_point >=", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointLessThan(Integer value) {
            addCriterion("auction_point <", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointLessThanOrEqualTo(Integer value) {
            addCriterion("auction_point <=", value, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointIn(List<Integer> values) {
            addCriterion("auction_point in", values, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointNotIn(List<Integer> values) {
            addCriterion("auction_point not in", values, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointBetween(Integer value1, Integer value2) {
            addCriterion("auction_point between", value1, value2, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andAuctionPointNotBetween(Integer value1, Integer value2) {
            addCriterion("auction_point not between", value1, value2, "auctionPoint");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceIsNull() {
            addCriterion("has_invoice is null");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceIsNotNull() {
            addCriterion("has_invoice is not null");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceEqualTo(Boolean value) {
            addCriterion("has_invoice =", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceNotEqualTo(Boolean value) {
            addCriterion("has_invoice <>", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceGreaterThan(Boolean value) {
            addCriterion("has_invoice >", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_invoice >=", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceLessThan(Boolean value) {
            addCriterion("has_invoice <", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceLessThanOrEqualTo(Boolean value) {
            addCriterion("has_invoice <=", value, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceIn(List<Boolean> values) {
            addCriterion("has_invoice in", values, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceNotIn(List<Boolean> values) {
            addCriterion("has_invoice not in", values, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceBetween(Boolean value1, Boolean value2) {
            addCriterion("has_invoice between", value1, value2, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasInvoiceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_invoice not between", value1, value2, "hasInvoice");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyIsNull() {
            addCriterion("has_warranty is null");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyIsNotNull() {
            addCriterion("has_warranty is not null");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyEqualTo(Boolean value) {
            addCriterion("has_warranty =", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyNotEqualTo(Boolean value) {
            addCriterion("has_warranty <>", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyGreaterThan(Boolean value) {
            addCriterion("has_warranty >", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_warranty >=", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyLessThan(Boolean value) {
            addCriterion("has_warranty <", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyLessThanOrEqualTo(Boolean value) {
            addCriterion("has_warranty <=", value, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyIn(List<Boolean> values) {
            addCriterion("has_warranty in", values, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyNotIn(List<Boolean> values) {
            addCriterion("has_warranty not in", values, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyBetween(Boolean value1, Boolean value2) {
            addCriterion("has_warranty between", value1, value2, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andHasWarrantyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_warranty not between", value1, value2, "hasWarranty");
            return (Criteria) this;
        }

        public Criteria andSubStockIsNull() {
            addCriterion("sub_stock is null");
            return (Criteria) this;
        }

        public Criteria andSubStockIsNotNull() {
            addCriterion("sub_stock is not null");
            return (Criteria) this;
        }

        public Criteria andSubStockEqualTo(Integer value) {
            addCriterion("sub_stock =", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockNotEqualTo(Integer value) {
            addCriterion("sub_stock <>", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockGreaterThan(Integer value) {
            addCriterion("sub_stock >", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_stock >=", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockLessThan(Integer value) {
            addCriterion("sub_stock <", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockLessThanOrEqualTo(Integer value) {
            addCriterion("sub_stock <=", value, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockIn(List<Integer> values) {
            addCriterion("sub_stock in", values, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockNotIn(List<Integer> values) {
            addCriterion("sub_stock not in", values, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockBetween(Integer value1, Integer value2) {
            addCriterion("sub_stock between", value1, value2, "subStock");
            return (Criteria) this;
        }

        public Criteria andSubStockNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_stock not between", value1, value2, "subStock");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesIsNull() {
            addCriterion("sku_properties is null");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesIsNotNull() {
            addCriterion("sku_properties is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesEqualTo(String value) {
            addCriterion("sku_properties =", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesNotEqualTo(String value) {
            addCriterion("sku_properties <>", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesGreaterThan(String value) {
            addCriterion("sku_properties >", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("sku_properties >=", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesLessThan(String value) {
            addCriterion("sku_properties <", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesLessThanOrEqualTo(String value) {
            addCriterion("sku_properties <=", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesLike(String value) {
            addCriterion("sku_properties like", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesNotLike(String value) {
            addCriterion("sku_properties not like", value, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesIn(List<String> values) {
            addCriterion("sku_properties in", values, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesNotIn(List<String> values) {
            addCriterion("sku_properties not in", values, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesBetween(String value1, String value2) {
            addCriterion("sku_properties between", value1, value2, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuPropertiesNotBetween(String value1, String value2) {
            addCriterion("sku_properties not between", value1, value2, "skuProperties");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesIsNull() {
            addCriterion("sku_quantities is null");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesIsNotNull() {
            addCriterion("sku_quantities is not null");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesEqualTo(String value) {
            addCriterion("sku_quantities =", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesNotEqualTo(String value) {
            addCriterion("sku_quantities <>", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesGreaterThan(String value) {
            addCriterion("sku_quantities >", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesGreaterThanOrEqualTo(String value) {
            addCriterion("sku_quantities >=", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesLessThan(String value) {
            addCriterion("sku_quantities <", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesLessThanOrEqualTo(String value) {
            addCriterion("sku_quantities <=", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesLike(String value) {
            addCriterion("sku_quantities like", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesNotLike(String value) {
            addCriterion("sku_quantities not like", value, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesIn(List<String> values) {
            addCriterion("sku_quantities in", values, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesNotIn(List<String> values) {
            addCriterion("sku_quantities not in", values, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesBetween(String value1, String value2) {
            addCriterion("sku_quantities between", value1, value2, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSkuQuantitiesNotBetween(String value1, String value2) {
            addCriterion("sku_quantities not between", value1, value2, "skuQuantities");
            return (Criteria) this;
        }

        public Criteria andSubscriptionIsNull() {
            addCriterion("subscription is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionIsNotNull() {
            addCriterion("subscription is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEqualTo(String value) {
            addCriterion("subscription =", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionNotEqualTo(String value) {
            addCriterion("subscription <>", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionGreaterThan(String value) {
            addCriterion("subscription >", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionGreaterThanOrEqualTo(String value) {
            addCriterion("subscription >=", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionLessThan(String value) {
            addCriterion("subscription <", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionLessThanOrEqualTo(String value) {
            addCriterion("subscription <=", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionLike(String value) {
            addCriterion("subscription like", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionNotLike(String value) {
            addCriterion("subscription not like", value, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionIn(List<String> values) {
            addCriterion("subscription in", values, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionNotIn(List<String> values) {
            addCriterion("subscription not in", values, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionBetween(String value1, String value2) {
            addCriterion("subscription between", value1, value2, "subscription");
            return (Criteria) this;
        }

        public Criteria andSubscriptionNotBetween(String value1, String value2) {
            addCriterion("subscription not between", value1, value2, "subscription");
            return (Criteria) this;
        }

        public Criteria andLocationStateIsNull() {
            addCriterion("location_state is null");
            return (Criteria) this;
        }

        public Criteria andLocationStateIsNotNull() {
            addCriterion("location_state is not null");
            return (Criteria) this;
        }

        public Criteria andLocationStateEqualTo(String value) {
            addCriterion("location_state =", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateNotEqualTo(String value) {
            addCriterion("location_state <>", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateGreaterThan(String value) {
            addCriterion("location_state >", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateGreaterThanOrEqualTo(String value) {
            addCriterion("location_state >=", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateLessThan(String value) {
            addCriterion("location_state <", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateLessThanOrEqualTo(String value) {
            addCriterion("location_state <=", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateLike(String value) {
            addCriterion("location_state like", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateNotLike(String value) {
            addCriterion("location_state not like", value, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateIn(List<String> values) {
            addCriterion("location_state in", values, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateNotIn(List<String> values) {
            addCriterion("location_state not in", values, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateBetween(String value1, String value2) {
            addCriterion("location_state between", value1, value2, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationStateNotBetween(String value1, String value2) {
            addCriterion("location_state not between", value1, value2, "locationState");
            return (Criteria) this;
        }

        public Criteria andLocationCityIsNull() {
            addCriterion("location_city is null");
            return (Criteria) this;
        }

        public Criteria andLocationCityIsNotNull() {
            addCriterion("location_city is not null");
            return (Criteria) this;
        }

        public Criteria andLocationCityEqualTo(String value) {
            addCriterion("location_city =", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityNotEqualTo(String value) {
            addCriterion("location_city <>", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityGreaterThan(String value) {
            addCriterion("location_city >", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityGreaterThanOrEqualTo(String value) {
            addCriterion("location_city >=", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityLessThan(String value) {
            addCriterion("location_city <", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityLessThanOrEqualTo(String value) {
            addCriterion("location_city <=", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityLike(String value) {
            addCriterion("location_city like", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityNotLike(String value) {
            addCriterion("location_city not like", value, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityIn(List<String> values) {
            addCriterion("location_city in", values, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityNotIn(List<String> values) {
            addCriterion("location_city not in", values, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityBetween(String value1, String value2) {
            addCriterion("location_city between", value1, value2, "locationCity");
            return (Criteria) this;
        }

        public Criteria andLocationCityNotBetween(String value1, String value2) {
            addCriterion("location_city not between", value1, value2, "locationCity");
            return (Criteria) this;
        }

        public Criteria andValidThruIsNull() {
            addCriterion("valid_thru is null");
            return (Criteria) this;
        }

        public Criteria andValidThruIsNotNull() {
            addCriterion("valid_thru is not null");
            return (Criteria) this;
        }

        public Criteria andValidThruEqualTo(Integer value) {
            addCriterion("valid_thru =", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruNotEqualTo(Integer value) {
            addCriterion("valid_thru <>", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruGreaterThan(Integer value) {
            addCriterion("valid_thru >", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid_thru >=", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruLessThan(Integer value) {
            addCriterion("valid_thru <", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruLessThanOrEqualTo(Integer value) {
            addCriterion("valid_thru <=", value, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruIn(List<Integer> values) {
            addCriterion("valid_thru in", values, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruNotIn(List<Integer> values) {
            addCriterion("valid_thru not in", values, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruBetween(Integer value1, Integer value2) {
            addCriterion("valid_thru between", value1, value2, "validThru");
            return (Criteria) this;
        }

        public Criteria andValidThruNotBetween(Integer value1, Integer value2) {
            addCriterion("valid_thru not between", value1, value2, "validThru");
            return (Criteria) this;
        }

        public Criteria andSellPromiseIsNull() {
            addCriterion("sell_promise is null");
            return (Criteria) this;
        }

        public Criteria andSellPromiseIsNotNull() {
            addCriterion("sell_promise is not null");
            return (Criteria) this;
        }

        public Criteria andSellPromiseEqualTo(Boolean value) {
            addCriterion("sell_promise =", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseNotEqualTo(Boolean value) {
            addCriterion("sell_promise <>", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseGreaterThan(Boolean value) {
            addCriterion("sell_promise >", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sell_promise >=", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseLessThan(Boolean value) {
            addCriterion("sell_promise <", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseLessThanOrEqualTo(Boolean value) {
            addCriterion("sell_promise <=", value, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseIn(List<Boolean> values) {
            addCriterion("sell_promise in", values, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseNotIn(List<Boolean> values) {
            addCriterion("sell_promise not in", values, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseBetween(Boolean value1, Boolean value2) {
            addCriterion("sell_promise between", value1, value2, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andSellPromiseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sell_promise not between", value1, value2, "sellPromise");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantIsNull() {
            addCriterion("locality_life_merchant is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantIsNotNull() {
            addCriterion("locality_life_merchant is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantEqualTo(String value) {
            addCriterion("locality_life_merchant =", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantNotEqualTo(String value) {
            addCriterion("locality_life_merchant <>", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantGreaterThan(String value) {
            addCriterion("locality_life_merchant >", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantGreaterThanOrEqualTo(String value) {
            addCriterion("locality_life_merchant >=", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantLessThan(String value) {
            addCriterion("locality_life_merchant <", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantLessThanOrEqualTo(String value) {
            addCriterion("locality_life_merchant <=", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantLike(String value) {
            addCriterion("locality_life_merchant like", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantNotLike(String value) {
            addCriterion("locality_life_merchant not like", value, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantIn(List<String> values) {
            addCriterion("locality_life_merchant in", values, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantNotIn(List<String> values) {
            addCriterion("locality_life_merchant not in", values, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantBetween(String value1, String value2) {
            addCriterion("locality_life_merchant between", value1, value2, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeMerchantNotBetween(String value1, String value2) {
            addCriterion("locality_life_merchant not between", value1, value2, "localityLifeMerchant");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioIsNull() {
            addCriterion("locality_life_onsale_auto_refund_ratio is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioIsNotNull() {
            addCriterion("locality_life_onsale_auto_refund_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioEqualTo(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio =", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioNotEqualTo(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio <>", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioGreaterThan(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio >", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioGreaterThanOrEqualTo(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio >=", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioLessThan(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio <", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioLessThanOrEqualTo(Integer value) {
            addCriterion("locality_life_onsale_auto_refund_ratio <=", value, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioIn(List<Integer> values) {
            addCriterion("locality_life_onsale_auto_refund_ratio in", values, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioNotIn(List<Integer> values) {
            addCriterion("locality_life_onsale_auto_refund_ratio not in", values, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioBetween(Integer value1, Integer value2) {
            addCriterion("locality_life_onsale_auto_refund_ratio between", value1, value2, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeOnsaleAutoRefundRatioNotBetween(Integer value1, Integer value2) {
            addCriterion("locality_life_onsale_auto_refund_ratio not between", value1, value2, "localityLifeOnsaleAutoRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioIsNull() {
            addCriterion("locality_life_refund_ratio is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioIsNotNull() {
            addCriterion("locality_life_refund_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioEqualTo(Integer value) {
            addCriterion("locality_life_refund_ratio =", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioNotEqualTo(Integer value) {
            addCriterion("locality_life_refund_ratio <>", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioGreaterThan(Integer value) {
            addCriterion("locality_life_refund_ratio >", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioGreaterThanOrEqualTo(Integer value) {
            addCriterion("locality_life_refund_ratio >=", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioLessThan(Integer value) {
            addCriterion("locality_life_refund_ratio <", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioLessThanOrEqualTo(Integer value) {
            addCriterion("locality_life_refund_ratio <=", value, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioIn(List<Integer> values) {
            addCriterion("locality_life_refund_ratio in", values, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioNotIn(List<Integer> values) {
            addCriterion("locality_life_refund_ratio not in", values, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioBetween(Integer value1, Integer value2) {
            addCriterion("locality_life_refund_ratio between", value1, value2, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeRefundRatioNotBetween(Integer value1, Integer value2) {
            addCriterion("locality_life_refund_ratio not between", value1, value2, "localityLifeRefundRatio");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisIsNull() {
            addCriterion("locality_life_choose_logis is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisIsNotNull() {
            addCriterion("locality_life_choose_logis is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisEqualTo(String value) {
            addCriterion("locality_life_choose_logis =", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisNotEqualTo(String value) {
            addCriterion("locality_life_choose_logis <>", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisGreaterThan(String value) {
            addCriterion("locality_life_choose_logis >", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisGreaterThanOrEqualTo(String value) {
            addCriterion("locality_life_choose_logis >=", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisLessThan(String value) {
            addCriterion("locality_life_choose_logis <", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisLessThanOrEqualTo(String value) {
            addCriterion("locality_life_choose_logis <=", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisLike(String value) {
            addCriterion("locality_life_choose_logis like", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisNotLike(String value) {
            addCriterion("locality_life_choose_logis not like", value, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisIn(List<String> values) {
            addCriterion("locality_life_choose_logis in", values, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisNotIn(List<String> values) {
            addCriterion("locality_life_choose_logis not in", values, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisBetween(String value1, String value2) {
            addCriterion("locality_life_choose_logis between", value1, value2, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeChooseLogisNotBetween(String value1, String value2) {
            addCriterion("locality_life_choose_logis not between", value1, value2, "localityLifeChooseLogis");
            return (Criteria) this;
        }

        public Criteria andFreightPayerIsNull() {
            addCriterion("freight_payer is null");
            return (Criteria) this;
        }

        public Criteria andFreightPayerIsNotNull() {
            addCriterion("freight_payer is not null");
            return (Criteria) this;
        }

        public Criteria andFreightPayerEqualTo(String value) {
            addCriterion("freight_payer =", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerNotEqualTo(String value) {
            addCriterion("freight_payer <>", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerGreaterThan(String value) {
            addCriterion("freight_payer >", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerGreaterThanOrEqualTo(String value) {
            addCriterion("freight_payer >=", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerLessThan(String value) {
            addCriterion("freight_payer <", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerLessThanOrEqualTo(String value) {
            addCriterion("freight_payer <=", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerLike(String value) {
            addCriterion("freight_payer like", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerNotLike(String value) {
            addCriterion("freight_payer not like", value, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerIn(List<String> values) {
            addCriterion("freight_payer in", values, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerNotIn(List<String> values) {
            addCriterion("freight_payer not in", values, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerBetween(String value1, String value2) {
            addCriterion("freight_payer between", value1, value2, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andFreightPayerNotBetween(String value1, String value2) {
            addCriterion("freight_payer not between", value1, value2, "freightPayer");
            return (Criteria) this;
        }

        public Criteria andPostFeeIsNull() {
            addCriterion("post_fee is null");
            return (Criteria) this;
        }

        public Criteria andPostFeeIsNotNull() {
            addCriterion("post_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPostFeeEqualTo(String value) {
            addCriterion("post_fee =", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotEqualTo(String value) {
            addCriterion("post_fee <>", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeGreaterThan(String value) {
            addCriterion("post_fee >", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeGreaterThanOrEqualTo(String value) {
            addCriterion("post_fee >=", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeLessThan(String value) {
            addCriterion("post_fee <", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeLessThanOrEqualTo(String value) {
            addCriterion("post_fee <=", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeLike(String value) {
            addCriterion("post_fee like", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotLike(String value) {
            addCriterion("post_fee not like", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeIn(List<String> values) {
            addCriterion("post_fee in", values, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotIn(List<String> values) {
            addCriterion("post_fee not in", values, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeBetween(String value1, String value2) {
            addCriterion("post_fee between", value1, value2, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotBetween(String value1, String value2) {
            addCriterion("post_fee not between", value1, value2, "postFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIsNull() {
            addCriterion("express_fee is null");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIsNotNull() {
            addCriterion("express_fee is not null");
            return (Criteria) this;
        }

        public Criteria andExpressFeeEqualTo(String value) {
            addCriterion("express_fee =", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotEqualTo(String value) {
            addCriterion("express_fee <>", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeGreaterThan(String value) {
            addCriterion("express_fee >", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeGreaterThanOrEqualTo(String value) {
            addCriterion("express_fee >=", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeLessThan(String value) {
            addCriterion("express_fee <", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeLessThanOrEqualTo(String value) {
            addCriterion("express_fee <=", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeLike(String value) {
            addCriterion("express_fee like", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotLike(String value) {
            addCriterion("express_fee not like", value, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeIn(List<String> values) {
            addCriterion("express_fee in", values, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotIn(List<String> values) {
            addCriterion("express_fee not in", values, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeBetween(String value1, String value2) {
            addCriterion("express_fee between", value1, value2, "expressFee");
            return (Criteria) this;
        }

        public Criteria andExpressFeeNotBetween(String value1, String value2) {
            addCriterion("express_fee not between", value1, value2, "expressFee");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketIsNull() {
            addCriterion("locality_life_eticket is null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketIsNotNull() {
            addCriterion("locality_life_eticket is not null");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketEqualTo(String value) {
            addCriterion("locality_life_eticket =", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketNotEqualTo(String value) {
            addCriterion("locality_life_eticket <>", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketGreaterThan(String value) {
            addCriterion("locality_life_eticket >", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketGreaterThanOrEqualTo(String value) {
            addCriterion("locality_life_eticket >=", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketLessThan(String value) {
            addCriterion("locality_life_eticket <", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketLessThanOrEqualTo(String value) {
            addCriterion("locality_life_eticket <=", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketLike(String value) {
            addCriterion("locality_life_eticket like", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketNotLike(String value) {
            addCriterion("locality_life_eticket not like", value, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketIn(List<String> values) {
            addCriterion("locality_life_eticket in", values, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketNotIn(List<String> values) {
            addCriterion("locality_life_eticket not in", values, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketBetween(String value1, String value2) {
            addCriterion("locality_life_eticket between", value1, value2, "localityLifeEticket");
            return (Criteria) this;
        }

        public Criteria andLocalityLifeEticketNotBetween(String value1, String value2) {
            addCriterion("locality_life_eticket not between", value1, value2, "localityLifeEticket");
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