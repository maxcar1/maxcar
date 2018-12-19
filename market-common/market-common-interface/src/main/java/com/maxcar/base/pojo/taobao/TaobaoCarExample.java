package com.maxcar.base.pojo.taobao;

import java.util.ArrayList;
import java.util.List;

public class TaobaoCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaobaoCarExample() {
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

        public Criteria andBrandNameIsNull() {
            addCriterion("brand_name is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("brand_name =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("brand_name <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("brand_name >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_name >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("brand_name <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("brand_name <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("brand_name like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("brand_name not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("brand_name in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("brand_name not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("brand_name between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("brand_name not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandPidIsNull() {
            addCriterion("brand_pid is null");
            return (Criteria) this;
        }

        public Criteria andBrandPidIsNotNull() {
            addCriterion("brand_pid is not null");
            return (Criteria) this;
        }

        public Criteria andBrandPidEqualTo(String value) {
            addCriterion("brand_pid =", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidNotEqualTo(String value) {
            addCriterion("brand_pid <>", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidGreaterThan(String value) {
            addCriterion("brand_pid >", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidGreaterThanOrEqualTo(String value) {
            addCriterion("brand_pid >=", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidLessThan(String value) {
            addCriterion("brand_pid <", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidLessThanOrEqualTo(String value) {
            addCriterion("brand_pid <=", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidLike(String value) {
            addCriterion("brand_pid like", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidNotLike(String value) {
            addCriterion("brand_pid not like", value, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidIn(List<String> values) {
            addCriterion("brand_pid in", values, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidNotIn(List<String> values) {
            addCriterion("brand_pid not in", values, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidBetween(String value1, String value2) {
            addCriterion("brand_pid between", value1, value2, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandPidNotBetween(String value1, String value2) {
            addCriterion("brand_pid not between", value1, value2, "brandPid");
            return (Criteria) this;
        }

        public Criteria andBrandVidIsNull() {
            addCriterion("brand_vid is null");
            return (Criteria) this;
        }

        public Criteria andBrandVidIsNotNull() {
            addCriterion("brand_vid is not null");
            return (Criteria) this;
        }

        public Criteria andBrandVidEqualTo(String value) {
            addCriterion("brand_vid =", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidNotEqualTo(String value) {
            addCriterion("brand_vid <>", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidGreaterThan(String value) {
            addCriterion("brand_vid >", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidGreaterThanOrEqualTo(String value) {
            addCriterion("brand_vid >=", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidLessThan(String value) {
            addCriterion("brand_vid <", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidLessThanOrEqualTo(String value) {
            addCriterion("brand_vid <=", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidLike(String value) {
            addCriterion("brand_vid like", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidNotLike(String value) {
            addCriterion("brand_vid not like", value, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidIn(List<String> values) {
            addCriterion("brand_vid in", values, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidNotIn(List<String> values) {
            addCriterion("brand_vid not in", values, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidBetween(String value1, String value2) {
            addCriterion("brand_vid between", value1, value2, "brandVid");
            return (Criteria) this;
        }

        public Criteria andBrandVidNotBetween(String value1, String value2) {
            addCriterion("brand_vid not between", value1, value2, "brandVid");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNull() {
            addCriterion("series_name is null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNotNull() {
            addCriterion("series_name is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameEqualTo(String value) {
            addCriterion("series_name =", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotEqualTo(String value) {
            addCriterion("series_name <>", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThan(String value) {
            addCriterion("series_name >", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThanOrEqualTo(String value) {
            addCriterion("series_name >=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThan(String value) {
            addCriterion("series_name <", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThanOrEqualTo(String value) {
            addCriterion("series_name <=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLike(String value) {
            addCriterion("series_name like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotLike(String value) {
            addCriterion("series_name not like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIn(List<String> values) {
            addCriterion("series_name in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotIn(List<String> values) {
            addCriterion("series_name not in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameBetween(String value1, String value2) {
            addCriterion("series_name between", value1, value2, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotBetween(String value1, String value2) {
            addCriterion("series_name not between", value1, value2, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesPidIsNull() {
            addCriterion("series_pid is null");
            return (Criteria) this;
        }

        public Criteria andSeriesPidIsNotNull() {
            addCriterion("series_pid is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesPidEqualTo(String value) {
            addCriterion("series_pid =", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidNotEqualTo(String value) {
            addCriterion("series_pid <>", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidGreaterThan(String value) {
            addCriterion("series_pid >", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidGreaterThanOrEqualTo(String value) {
            addCriterion("series_pid >=", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidLessThan(String value) {
            addCriterion("series_pid <", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidLessThanOrEqualTo(String value) {
            addCriterion("series_pid <=", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidLike(String value) {
            addCriterion("series_pid like", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidNotLike(String value) {
            addCriterion("series_pid not like", value, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidIn(List<String> values) {
            addCriterion("series_pid in", values, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidNotIn(List<String> values) {
            addCriterion("series_pid not in", values, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidBetween(String value1, String value2) {
            addCriterion("series_pid between", value1, value2, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesPidNotBetween(String value1, String value2) {
            addCriterion("series_pid not between", value1, value2, "seriesPid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidIsNull() {
            addCriterion("series_vid is null");
            return (Criteria) this;
        }

        public Criteria andSeriesVidIsNotNull() {
            addCriterion("series_vid is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesVidEqualTo(String value) {
            addCriterion("series_vid =", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidNotEqualTo(String value) {
            addCriterion("series_vid <>", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidGreaterThan(String value) {
            addCriterion("series_vid >", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidGreaterThanOrEqualTo(String value) {
            addCriterion("series_vid >=", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidLessThan(String value) {
            addCriterion("series_vid <", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidLessThanOrEqualTo(String value) {
            addCriterion("series_vid <=", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidLike(String value) {
            addCriterion("series_vid like", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidNotLike(String value) {
            addCriterion("series_vid not like", value, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidIn(List<String> values) {
            addCriterion("series_vid in", values, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidNotIn(List<String> values) {
            addCriterion("series_vid not in", values, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidBetween(String value1, String value2) {
            addCriterion("series_vid between", value1, value2, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andSeriesVidNotBetween(String value1, String value2) {
            addCriterion("series_vid not between", value1, value2, "seriesVid");
            return (Criteria) this;
        }

        public Criteria andYearNameIsNull() {
            addCriterion("year_name is null");
            return (Criteria) this;
        }

        public Criteria andYearNameIsNotNull() {
            addCriterion("year_name is not null");
            return (Criteria) this;
        }

        public Criteria andYearNameEqualTo(String value) {
            addCriterion("year_name =", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameNotEqualTo(String value) {
            addCriterion("year_name <>", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameGreaterThan(String value) {
            addCriterion("year_name >", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameGreaterThanOrEqualTo(String value) {
            addCriterion("year_name >=", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameLessThan(String value) {
            addCriterion("year_name <", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameLessThanOrEqualTo(String value) {
            addCriterion("year_name <=", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameLike(String value) {
            addCriterion("year_name like", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameNotLike(String value) {
            addCriterion("year_name not like", value, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameIn(List<String> values) {
            addCriterion("year_name in", values, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameNotIn(List<String> values) {
            addCriterion("year_name not in", values, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameBetween(String value1, String value2) {
            addCriterion("year_name between", value1, value2, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearNameNotBetween(String value1, String value2) {
            addCriterion("year_name not between", value1, value2, "yearName");
            return (Criteria) this;
        }

        public Criteria andYearPidIsNull() {
            addCriterion("year_pid is null");
            return (Criteria) this;
        }

        public Criteria andYearPidIsNotNull() {
            addCriterion("year_pid is not null");
            return (Criteria) this;
        }

        public Criteria andYearPidEqualTo(String value) {
            addCriterion("year_pid =", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidNotEqualTo(String value) {
            addCriterion("year_pid <>", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidGreaterThan(String value) {
            addCriterion("year_pid >", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidGreaterThanOrEqualTo(String value) {
            addCriterion("year_pid >=", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidLessThan(String value) {
            addCriterion("year_pid <", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidLessThanOrEqualTo(String value) {
            addCriterion("year_pid <=", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidLike(String value) {
            addCriterion("year_pid like", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidNotLike(String value) {
            addCriterion("year_pid not like", value, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidIn(List<String> values) {
            addCriterion("year_pid in", values, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidNotIn(List<String> values) {
            addCriterion("year_pid not in", values, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidBetween(String value1, String value2) {
            addCriterion("year_pid between", value1, value2, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearPidNotBetween(String value1, String value2) {
            addCriterion("year_pid not between", value1, value2, "yearPid");
            return (Criteria) this;
        }

        public Criteria andYearVidIsNull() {
            addCriterion("year_vid is null");
            return (Criteria) this;
        }

        public Criteria andYearVidIsNotNull() {
            addCriterion("year_vid is not null");
            return (Criteria) this;
        }

        public Criteria andYearVidEqualTo(String value) {
            addCriterion("year_vid =", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidNotEqualTo(String value) {
            addCriterion("year_vid <>", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidGreaterThan(String value) {
            addCriterion("year_vid >", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidGreaterThanOrEqualTo(String value) {
            addCriterion("year_vid >=", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidLessThan(String value) {
            addCriterion("year_vid <", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidLessThanOrEqualTo(String value) {
            addCriterion("year_vid <=", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidLike(String value) {
            addCriterion("year_vid like", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidNotLike(String value) {
            addCriterion("year_vid not like", value, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidIn(List<String> values) {
            addCriterion("year_vid in", values, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidNotIn(List<String> values) {
            addCriterion("year_vid not in", values, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidBetween(String value1, String value2) {
            addCriterion("year_vid between", value1, value2, "yearVid");
            return (Criteria) this;
        }

        public Criteria andYearVidNotBetween(String value1, String value2) {
            addCriterion("year_vid not between", value1, value2, "yearVid");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNull() {
            addCriterion("model_name is null");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNotNull() {
            addCriterion("model_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelNameEqualTo(String value) {
            addCriterion("model_name =", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotEqualTo(String value) {
            addCriterion("model_name <>", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThan(String value) {
            addCriterion("model_name >", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("model_name >=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThan(String value) {
            addCriterion("model_name <", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThanOrEqualTo(String value) {
            addCriterion("model_name <=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLike(String value) {
            addCriterion("model_name like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotLike(String value) {
            addCriterion("model_name not like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameIn(List<String> values) {
            addCriterion("model_name in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotIn(List<String> values) {
            addCriterion("model_name not in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameBetween(String value1, String value2) {
            addCriterion("model_name between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotBetween(String value1, String value2) {
            addCriterion("model_name not between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelPidIsNull() {
            addCriterion("model_pid is null");
            return (Criteria) this;
        }

        public Criteria andModelPidIsNotNull() {
            addCriterion("model_pid is not null");
            return (Criteria) this;
        }

        public Criteria andModelPidEqualTo(String value) {
            addCriterion("model_pid =", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidNotEqualTo(String value) {
            addCriterion("model_pid <>", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidGreaterThan(String value) {
            addCriterion("model_pid >", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidGreaterThanOrEqualTo(String value) {
            addCriterion("model_pid >=", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidLessThan(String value) {
            addCriterion("model_pid <", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidLessThanOrEqualTo(String value) {
            addCriterion("model_pid <=", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidLike(String value) {
            addCriterion("model_pid like", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidNotLike(String value) {
            addCriterion("model_pid not like", value, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidIn(List<String> values) {
            addCriterion("model_pid in", values, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidNotIn(List<String> values) {
            addCriterion("model_pid not in", values, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidBetween(String value1, String value2) {
            addCriterion("model_pid between", value1, value2, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelPidNotBetween(String value1, String value2) {
            addCriterion("model_pid not between", value1, value2, "modelPid");
            return (Criteria) this;
        }

        public Criteria andModelVidIsNull() {
            addCriterion("model_vid is null");
            return (Criteria) this;
        }

        public Criteria andModelVidIsNotNull() {
            addCriterion("model_vid is not null");
            return (Criteria) this;
        }

        public Criteria andModelVidEqualTo(String value) {
            addCriterion("model_vid =", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidNotEqualTo(String value) {
            addCriterion("model_vid <>", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidGreaterThan(String value) {
            addCriterion("model_vid >", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidGreaterThanOrEqualTo(String value) {
            addCriterion("model_vid >=", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidLessThan(String value) {
            addCriterion("model_vid <", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidLessThanOrEqualTo(String value) {
            addCriterion("model_vid <=", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidLike(String value) {
            addCriterion("model_vid like", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidNotLike(String value) {
            addCriterion("model_vid not like", value, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidIn(List<String> values) {
            addCriterion("model_vid in", values, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidNotIn(List<String> values) {
            addCriterion("model_vid not in", values, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidBetween(String value1, String value2) {
            addCriterion("model_vid between", value1, value2, "modelVid");
            return (Criteria) this;
        }

        public Criteria andModelVidNotBetween(String value1, String value2) {
            addCriterion("model_vid not between", value1, value2, "modelVid");
            return (Criteria) this;
        }

        public Criteria andAliValueIsNull() {
            addCriterion("ali_value is null");
            return (Criteria) this;
        }

        public Criteria andAliValueIsNotNull() {
            addCriterion("ali_value is not null");
            return (Criteria) this;
        }

        public Criteria andAliValueEqualTo(String value) {
            addCriterion("ali_value =", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueNotEqualTo(String value) {
            addCriterion("ali_value <>", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueGreaterThan(String value) {
            addCriterion("ali_value >", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueGreaterThanOrEqualTo(String value) {
            addCriterion("ali_value >=", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueLessThan(String value) {
            addCriterion("ali_value <", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueLessThanOrEqualTo(String value) {
            addCriterion("ali_value <=", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueLike(String value) {
            addCriterion("ali_value like", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueNotLike(String value) {
            addCriterion("ali_value not like", value, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueIn(List<String> values) {
            addCriterion("ali_value in", values, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueNotIn(List<String> values) {
            addCriterion("ali_value not in", values, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueBetween(String value1, String value2) {
            addCriterion("ali_value between", value1, value2, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliValueNotBetween(String value1, String value2) {
            addCriterion("ali_value not between", value1, value2, "aliValue");
            return (Criteria) this;
        }

        public Criteria andAliCodeIsNull() {
            addCriterion("ali_code is null");
            return (Criteria) this;
        }

        public Criteria andAliCodeIsNotNull() {
            addCriterion("ali_code is not null");
            return (Criteria) this;
        }

        public Criteria andAliCodeEqualTo(String value) {
            addCriterion("ali_code =", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeNotEqualTo(String value) {
            addCriterion("ali_code <>", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeGreaterThan(String value) {
            addCriterion("ali_code >", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ali_code >=", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeLessThan(String value) {
            addCriterion("ali_code <", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeLessThanOrEqualTo(String value) {
            addCriterion("ali_code <=", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeLike(String value) {
            addCriterion("ali_code like", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeNotLike(String value) {
            addCriterion("ali_code not like", value, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeIn(List<String> values) {
            addCriterion("ali_code in", values, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeNotIn(List<String> values) {
            addCriterion("ali_code not in", values, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeBetween(String value1, String value2) {
            addCriterion("ali_code between", value1, value2, "aliCode");
            return (Criteria) this;
        }

        public Criteria andAliCodeNotBetween(String value1, String value2) {
            addCriterion("ali_code not between", value1, value2, "aliCode");
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