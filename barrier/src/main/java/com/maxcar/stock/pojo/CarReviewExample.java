package com.maxcar.stock.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarReviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CarReviewExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOutReasonIsNull() {
            addCriterion("out_reason is null");
            return (Criteria) this;
        }

        public Criteria andOutReasonIsNotNull() {
            addCriterion("out_reason is not null");
            return (Criteria) this;
        }

        public Criteria andOutReasonEqualTo(String value) {
            addCriterion("out_reason =", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonNotEqualTo(String value) {
            addCriterion("out_reason <>", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonGreaterThan(String value) {
            addCriterion("out_reason >", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonGreaterThanOrEqualTo(String value) {
            addCriterion("out_reason >=", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonLessThan(String value) {
            addCriterion("out_reason <", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonLessThanOrEqualTo(String value) {
            addCriterion("out_reason <=", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonLike(String value) {
            addCriterion("out_reason like", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonNotLike(String value) {
            addCriterion("out_reason not like", value, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonIn(List<String> values) {
            addCriterion("out_reason in", values, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonNotIn(List<String> values) {
            addCriterion("out_reason not in", values, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonBetween(String value1, String value2) {
            addCriterion("out_reason between", value1, value2, "outReason");
            return (Criteria) this;
        }

        public Criteria andOutReasonNotBetween(String value1, String value2) {
            addCriterion("out_reason not between", value1, value2, "outReason");
            return (Criteria) this;
        }

        public Criteria andBackTimeIsNull() {
            addCriterion("back_time is null");
            return (Criteria) this;
        }

        public Criteria andBackTimeIsNotNull() {
            addCriterion("back_time is not null");
            return (Criteria) this;
        }

        public Criteria andBackTimeEqualTo(Date value) {
            addCriterion("back_time =", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotEqualTo(Date value) {
            addCriterion("back_time <>", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeGreaterThan(Date value) {
            addCriterion("back_time >", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("back_time >=", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeLessThan(Date value) {
            addCriterion("back_time <", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeLessThanOrEqualTo(Date value) {
            addCriterion("back_time <=", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeIn(List<Date> values) {
            addCriterion("back_time in", values, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotIn(List<Date> values) {
            addCriterion("back_time not in", values, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeBetween(Date value1, Date value2) {
            addCriterion("back_time between", value1, value2, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotBetween(Date value1, Date value2) {
            addCriterion("back_time not between", value1, value2, "backTime");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNull() {
            addCriterion("is_pass is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("is_pass is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(Integer value) {
            addCriterion("is_pass =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(Integer value) {
            addCriterion("is_pass <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(Integer value) {
            addCriterion("is_pass >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pass >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(Integer value) {
            addCriterion("is_pass <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(Integer value) {
            addCriterion("is_pass <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<Integer> values) {
            addCriterion("is_pass in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<Integer> values) {
            addCriterion("is_pass not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(Integer value1, Integer value2) {
            addCriterion("is_pass between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pass not between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andReasonDescIsNull() {
            addCriterion("reason_desc is null");
            return (Criteria) this;
        }

        public Criteria andReasonDescIsNotNull() {
            addCriterion("reason_desc is not null");
            return (Criteria) this;
        }

        public Criteria andReasonDescEqualTo(String value) {
            addCriterion("reason_desc =", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotEqualTo(String value) {
            addCriterion("reason_desc <>", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescGreaterThan(String value) {
            addCriterion("reason_desc >", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescGreaterThanOrEqualTo(String value) {
            addCriterion("reason_desc >=", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLessThan(String value) {
            addCriterion("reason_desc <", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLessThanOrEqualTo(String value) {
            addCriterion("reason_desc <=", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLike(String value) {
            addCriterion("reason_desc like", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotLike(String value) {
            addCriterion("reason_desc not like", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescIn(List<String> values) {
            addCriterion("reason_desc in", values, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotIn(List<String> values) {
            addCriterion("reason_desc not in", values, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescBetween(String value1, String value2) {
            addCriterion("reason_desc between", value1, value2, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotBetween(String value1, String value2) {
            addCriterion("reason_desc not between", value1, value2, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andIsPledgeIsNull() {
            addCriterion("is_pledge is null");
            return (Criteria) this;
        }

        public Criteria andIsPledgeIsNotNull() {
            addCriterion("is_pledge is not null");
            return (Criteria) this;
        }

        public Criteria andIsPledgeEqualTo(Integer value) {
            addCriterion("is_pledge =", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeNotEqualTo(Integer value) {
            addCriterion("is_pledge <>", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeGreaterThan(Integer value) {
            addCriterion("is_pledge >", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pledge >=", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeLessThan(Integer value) {
            addCriterion("is_pledge <", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeLessThanOrEqualTo(Integer value) {
            addCriterion("is_pledge <=", value, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeIn(List<Integer> values) {
            addCriterion("is_pledge in", values, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeNotIn(List<Integer> values) {
            addCriterion("is_pledge not in", values, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeBetween(Integer value1, Integer value2) {
            addCriterion("is_pledge between", value1, value2, "isPledge");
            return (Criteria) this;
        }

        public Criteria andIsPledgeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pledge not between", value1, value2, "isPledge");
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

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(Integer value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(Integer value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(Integer value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(Integer value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(Integer value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<Integer> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<Integer> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(Integer value1, Integer value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(Integer value1, Integer value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andStepLevelIsNull() {
            addCriterion("step_level is null");
            return (Criteria) this;
        }

        public Criteria andStepLevelIsNotNull() {
            addCriterion("step_level is not null");
            return (Criteria) this;
        }

        public Criteria andStepLevelEqualTo(Integer value) {
            addCriterion("step_level =", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelNotEqualTo(Integer value) {
            addCriterion("step_level <>", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelGreaterThan(Integer value) {
            addCriterion("step_level >", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_level >=", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelLessThan(Integer value) {
            addCriterion("step_level <", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelLessThanOrEqualTo(Integer value) {
            addCriterion("step_level <=", value, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelIn(List<Integer> values) {
            addCriterion("step_level in", values, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelNotIn(List<Integer> values) {
            addCriterion("step_level not in", values, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelBetween(Integer value1, Integer value2) {
            addCriterion("step_level between", value1, value2, "stepLevel");
            return (Criteria) this;
        }

        public Criteria andStepLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("step_level not between", value1, value2, "stepLevel");
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