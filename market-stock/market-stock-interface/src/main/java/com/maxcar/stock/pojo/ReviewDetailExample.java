package com.maxcar.stock.pojo;

import java.util.ArrayList;
import java.util.List;

public class ReviewDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewDetailExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andReviewIdIsNull() {
            addCriterion("review_id is null");
            return (Criteria) this;
        }

        public Criteria andReviewIdIsNotNull() {
            addCriterion("review_id is not null");
            return (Criteria) this;
        }

        public Criteria andReviewIdEqualTo(Integer value) {
            addCriterion("review_id =", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotEqualTo(Integer value) {
            addCriterion("review_id <>", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThan(Integer value) {
            addCriterion("review_id >", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_id >=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThan(Integer value) {
            addCriterion("review_id <", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThanOrEqualTo(Integer value) {
            addCriterion("review_id <=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdIn(List<Integer> values) {
            addCriterion("review_id in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotIn(List<Integer> values) {
            addCriterion("review_id not in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdBetween(Integer value1, Integer value2) {
            addCriterion("review_id between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotBetween(Integer value1, Integer value2) {
            addCriterion("review_id not between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdIsNull() {
            addCriterion("review_person_id is null");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdIsNotNull() {
            addCriterion("review_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdEqualTo(String value) {
            addCriterion("review_person_id =", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdNotEqualTo(String value) {
            addCriterion("review_person_id <>", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdGreaterThan(String value) {
            addCriterion("review_person_id >", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("review_person_id >=", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdLessThan(String value) {
            addCriterion("review_person_id <", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdLessThanOrEqualTo(String value) {
            addCriterion("review_person_id <=", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdLike(String value) {
            addCriterion("review_person_id like", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdNotLike(String value) {
            addCriterion("review_person_id not like", value, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdIn(List<String> values) {
            addCriterion("review_person_id in", values, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdNotIn(List<String> values) {
            addCriterion("review_person_id not in", values, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdBetween(String value1, String value2) {
            addCriterion("review_person_id between", value1, value2, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewPersonIdNotBetween(String value1, String value2) {
            addCriterion("review_person_id not between", value1, value2, "reviewPersonId");
            return (Criteria) this;
        }

        public Criteria andReviewResultIsNull() {
            addCriterion("review_result is null");
            return (Criteria) this;
        }

        public Criteria andReviewResultIsNotNull() {
            addCriterion("review_result is not null");
            return (Criteria) this;
        }

        public Criteria andReviewResultEqualTo(Integer value) {
            addCriterion("review_result =", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultNotEqualTo(Integer value) {
            addCriterion("review_result <>", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultGreaterThan(Integer value) {
            addCriterion("review_result >", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_result >=", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultLessThan(Integer value) {
            addCriterion("review_result <", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultLessThanOrEqualTo(Integer value) {
            addCriterion("review_result <=", value, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultIn(List<Integer> values) {
            addCriterion("review_result in", values, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultNotIn(List<Integer> values) {
            addCriterion("review_result not in", values, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultBetween(Integer value1, Integer value2) {
            addCriterion("review_result between", value1, value2, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewResultNotBetween(Integer value1, Integer value2) {
            addCriterion("review_result not between", value1, value2, "reviewResult");
            return (Criteria) this;
        }

        public Criteria andReviewDescIsNull() {
            addCriterion("review_desc is null");
            return (Criteria) this;
        }

        public Criteria andReviewDescIsNotNull() {
            addCriterion("review_desc is not null");
            return (Criteria) this;
        }

        public Criteria andReviewDescEqualTo(String value) {
            addCriterion("review_desc =", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescNotEqualTo(String value) {
            addCriterion("review_desc <>", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescGreaterThan(String value) {
            addCriterion("review_desc >", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescGreaterThanOrEqualTo(String value) {
            addCriterion("review_desc >=", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescLessThan(String value) {
            addCriterion("review_desc <", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescLessThanOrEqualTo(String value) {
            addCriterion("review_desc <=", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescLike(String value) {
            addCriterion("review_desc like", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescNotLike(String value) {
            addCriterion("review_desc not like", value, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescIn(List<String> values) {
            addCriterion("review_desc in", values, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescNotIn(List<String> values) {
            addCriterion("review_desc not in", values, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescBetween(String value1, String value2) {
            addCriterion("review_desc between", value1, value2, "reviewDesc");
            return (Criteria) this;
        }

        public Criteria andReviewDescNotBetween(String value1, String value2) {
            addCriterion("review_desc not between", value1, value2, "reviewDesc");
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