package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbCollectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCollectExample() {
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

        public Criteria andCollectIdIsNull() {
            addCriterion("collect_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectIdIsNotNull() {
            addCriterion("collect_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectIdEqualTo(Integer value) {
            addCriterion("collect_id =", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotEqualTo(Integer value) {
            addCriterion("collect_id <>", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThan(Integer value) {
            addCriterion("collect_id >", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_id >=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThan(Integer value) {
            addCriterion("collect_id <", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThanOrEqualTo(Integer value) {
            addCriterion("collect_id <=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdIn(List<Integer> values) {
            addCriterion("collect_id in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotIn(List<Integer> values) {
            addCriterion("collect_id not in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdBetween(Integer value1, Integer value2) {
            addCriterion("collect_id between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_id not between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andCollectNameIsNull() {
            addCriterion("collect_name is null");
            return (Criteria) this;
        }

        public Criteria andCollectNameIsNotNull() {
            addCriterion("collect_name is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNameEqualTo(String value) {
            addCriterion("collect_name =", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameNotEqualTo(String value) {
            addCriterion("collect_name <>", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameGreaterThan(String value) {
            addCriterion("collect_name >", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameGreaterThanOrEqualTo(String value) {
            addCriterion("collect_name >=", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameLessThan(String value) {
            addCriterion("collect_name <", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameLessThanOrEqualTo(String value) {
            addCriterion("collect_name <=", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameLike(String value) {
            addCriterion("collect_name like", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameNotLike(String value) {
            addCriterion("collect_name not like", value, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameIn(List<String> values) {
            addCriterion("collect_name in", values, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameNotIn(List<String> values) {
            addCriterion("collect_name not in", values, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameBetween(String value1, String value2) {
            addCriterion("collect_name between", value1, value2, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectNameNotBetween(String value1, String value2) {
            addCriterion("collect_name not between", value1, value2, "collectName");
            return (Criteria) this;
        }

        public Criteria andCollectImageIsNull() {
            addCriterion("collect_image is null");
            return (Criteria) this;
        }

        public Criteria andCollectImageIsNotNull() {
            addCriterion("collect_image is not null");
            return (Criteria) this;
        }

        public Criteria andCollectImageEqualTo(String value) {
            addCriterion("collect_image =", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageNotEqualTo(String value) {
            addCriterion("collect_image <>", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageGreaterThan(String value) {
            addCriterion("collect_image >", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageGreaterThanOrEqualTo(String value) {
            addCriterion("collect_image >=", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageLessThan(String value) {
            addCriterion("collect_image <", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageLessThanOrEqualTo(String value) {
            addCriterion("collect_image <=", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageLike(String value) {
            addCriterion("collect_image like", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageNotLike(String value) {
            addCriterion("collect_image not like", value, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageIn(List<String> values) {
            addCriterion("collect_image in", values, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageNotIn(List<String> values) {
            addCriterion("collect_image not in", values, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageBetween(String value1, String value2) {
            addCriterion("collect_image between", value1, value2, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectImageNotBetween(String value1, String value2) {
            addCriterion("collect_image not between", value1, value2, "collectImage");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateIsNull() {
            addCriterion("collect_newness_rate is null");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateIsNotNull() {
            addCriterion("collect_newness_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateEqualTo(Double value) {
            addCriterion("collect_newness_rate =", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateNotEqualTo(Double value) {
            addCriterion("collect_newness_rate <>", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateGreaterThan(Double value) {
            addCriterion("collect_newness_rate >", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateGreaterThanOrEqualTo(Double value) {
            addCriterion("collect_newness_rate >=", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateLessThan(Double value) {
            addCriterion("collect_newness_rate <", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateLessThanOrEqualTo(Double value) {
            addCriterion("collect_newness_rate <=", value, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateIn(List<Double> values) {
            addCriterion("collect_newness_rate in", values, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateNotIn(List<Double> values) {
            addCriterion("collect_newness_rate not in", values, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateBetween(Double value1, Double value2) {
            addCriterion("collect_newness_rate between", value1, value2, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectNewnessRateNotBetween(Double value1, Double value2) {
            addCriterion("collect_newness_rate not between", value1, value2, "collectNewnessRate");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointIsNull() {
            addCriterion("collect_sell_point is null");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointIsNotNull() {
            addCriterion("collect_sell_point is not null");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointEqualTo(String value) {
            addCriterion("collect_sell_point =", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointNotEqualTo(String value) {
            addCriterion("collect_sell_point <>", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointGreaterThan(String value) {
            addCriterion("collect_sell_point >", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointGreaterThanOrEqualTo(String value) {
            addCriterion("collect_sell_point >=", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointLessThan(String value) {
            addCriterion("collect_sell_point <", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointLessThanOrEqualTo(String value) {
            addCriterion("collect_sell_point <=", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointLike(String value) {
            addCriterion("collect_sell_point like", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointNotLike(String value) {
            addCriterion("collect_sell_point not like", value, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointIn(List<String> values) {
            addCriterion("collect_sell_point in", values, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointNotIn(List<String> values) {
            addCriterion("collect_sell_point not in", values, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointBetween(String value1, String value2) {
            addCriterion("collect_sell_point between", value1, value2, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectSellPointNotBetween(String value1, String value2) {
            addCriterion("collect_sell_point not between", value1, value2, "collectSellPoint");
            return (Criteria) this;
        }

        public Criteria andCollectPriceIsNull() {
            addCriterion("collect_price is null");
            return (Criteria) this;
        }

        public Criteria andCollectPriceIsNotNull() {
            addCriterion("collect_price is not null");
            return (Criteria) this;
        }

        public Criteria andCollectPriceEqualTo(Long value) {
            addCriterion("collect_price =", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceNotEqualTo(Long value) {
            addCriterion("collect_price <>", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceGreaterThan(Long value) {
            addCriterion("collect_price >", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("collect_price >=", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceLessThan(Long value) {
            addCriterion("collect_price <", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceLessThanOrEqualTo(Long value) {
            addCriterion("collect_price <=", value, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceIn(List<Long> values) {
            addCriterion("collect_price in", values, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceNotIn(List<Long> values) {
            addCriterion("collect_price not in", values, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceBetween(Long value1, Long value2) {
            addCriterion("collect_price between", value1, value2, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectPriceNotBetween(Long value1, Long value2) {
            addCriterion("collect_price not between", value1, value2, "collectPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceIsNull() {
            addCriterion("collect_original_price is null");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceIsNotNull() {
            addCriterion("collect_original_price is not null");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceEqualTo(Long value) {
            addCriterion("collect_original_price =", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceNotEqualTo(Long value) {
            addCriterion("collect_original_price <>", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceGreaterThan(Long value) {
            addCriterion("collect_original_price >", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("collect_original_price >=", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceLessThan(Long value) {
            addCriterion("collect_original_price <", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceLessThanOrEqualTo(Long value) {
            addCriterion("collect_original_price <=", value, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceIn(List<Long> values) {
            addCriterion("collect_original_price in", values, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceNotIn(List<Long> values) {
            addCriterion("collect_original_price not in", values, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceBetween(Long value1, Long value2) {
            addCriterion("collect_original_price between", value1, value2, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectOriginalPriceNotBetween(Long value1, Long value2) {
            addCriterion("collect_original_price not between", value1, value2, "collectOriginalPrice");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolIsNull() {
            addCriterion("collect_sell_school is null");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolIsNotNull() {
            addCriterion("collect_sell_school is not null");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolEqualTo(String value) {
            addCriterion("collect_sell_school =", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolNotEqualTo(String value) {
            addCriterion("collect_sell_school <>", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolGreaterThan(String value) {
            addCriterion("collect_sell_school >", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("collect_sell_school >=", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolLessThan(String value) {
            addCriterion("collect_sell_school <", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolLessThanOrEqualTo(String value) {
            addCriterion("collect_sell_school <=", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolLike(String value) {
            addCriterion("collect_sell_school like", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolNotLike(String value) {
            addCriterion("collect_sell_school not like", value, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolIn(List<String> values) {
            addCriterion("collect_sell_school in", values, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolNotIn(List<String> values) {
            addCriterion("collect_sell_school not in", values, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolBetween(String value1, String value2) {
            addCriterion("collect_sell_school between", value1, value2, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectSellSchoolNotBetween(String value1, String value2) {
            addCriterion("collect_sell_school not between", value1, value2, "collectSellSchool");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNull() {
            addCriterion("collect_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNotNull() {
            addCriterion("collect_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusEqualTo(Byte value) {
            addCriterion("collect_status =", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotEqualTo(Byte value) {
            addCriterion("collect_status <>", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThan(Byte value) {
            addCriterion("collect_status >", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("collect_status >=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThan(Byte value) {
            addCriterion("collect_status <", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThanOrEqualTo(Byte value) {
            addCriterion("collect_status <=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIn(List<Byte> values) {
            addCriterion("collect_status in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotIn(List<Byte> values) {
            addCriterion("collect_status not in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusBetween(Byte value1, Byte value2) {
            addCriterion("collect_status between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("collect_status not between", value1, value2, "collectStatus");
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