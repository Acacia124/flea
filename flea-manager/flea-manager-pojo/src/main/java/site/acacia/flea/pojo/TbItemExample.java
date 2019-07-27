package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbItemExample() {
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

        public Criteria andCatIdIsNull() {
            addCriterion("cat_id is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("cat_id =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("cat_id <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("cat_id >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_id >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("cat_id <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("cat_id <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("cat_id in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("cat_id not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("cat_id between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_id not between", value1, value2, "catId");
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

        public Criteria andItemTitleIsNull() {
            addCriterion("item_title is null");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNotNull() {
            addCriterion("item_title is not null");
            return (Criteria) this;
        }

        public Criteria andItemTitleEqualTo(String value) {
            addCriterion("item_title =", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotEqualTo(String value) {
            addCriterion("item_title <>", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThan(String value) {
            addCriterion("item_title >", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("item_title >=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThan(String value) {
            addCriterion("item_title <", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThanOrEqualTo(String value) {
            addCriterion("item_title <=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLike(String value) {
            addCriterion("item_title like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotLike(String value) {
            addCriterion("item_title not like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleIn(List<String> values) {
            addCriterion("item_title in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotIn(List<String> values) {
            addCriterion("item_title not in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleBetween(String value1, String value2) {
            addCriterion("item_title between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotBetween(String value1, String value2) {
            addCriterion("item_title not between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andSellPointIsNull() {
            addCriterion("sell_point is null");
            return (Criteria) this;
        }

        public Criteria andSellPointIsNotNull() {
            addCriterion("sell_point is not null");
            return (Criteria) this;
        }

        public Criteria andSellPointEqualTo(String value) {
            addCriterion("sell_point =", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotEqualTo(String value) {
            addCriterion("sell_point <>", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointGreaterThan(String value) {
            addCriterion("sell_point >", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointGreaterThanOrEqualTo(String value) {
            addCriterion("sell_point >=", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLessThan(String value) {
            addCriterion("sell_point <", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLessThanOrEqualTo(String value) {
            addCriterion("sell_point <=", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLike(String value) {
            addCriterion("sell_point like", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotLike(String value) {
            addCriterion("sell_point not like", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointIn(List<String> values) {
            addCriterion("sell_point in", values, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotIn(List<String> values) {
            addCriterion("sell_point not in", values, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointBetween(String value1, String value2) {
            addCriterion("sell_point between", value1, value2, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotBetween(String value1, String value2) {
            addCriterion("sell_point not between", value1, value2, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(Long value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(Long value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(Long value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(Long value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(Long value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<Long> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<Long> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(Long value1, Long value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(Long value1, Long value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andNewnessRateIsNull() {
            addCriterion("newness_rate is null");
            return (Criteria) this;
        }

        public Criteria andNewnessRateIsNotNull() {
            addCriterion("newness_rate is not null");
            return (Criteria) this;
        }

        public Criteria andNewnessRateEqualTo(Double value) {
            addCriterion("newness_rate =", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateNotEqualTo(Double value) {
            addCriterion("newness_rate <>", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateGreaterThan(Double value) {
            addCriterion("newness_rate >", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateGreaterThanOrEqualTo(Double value) {
            addCriterion("newness_rate >=", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateLessThan(Double value) {
            addCriterion("newness_rate <", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateLessThanOrEqualTo(Double value) {
            addCriterion("newness_rate <=", value, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateIn(List<Double> values) {
            addCriterion("newness_rate in", values, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateNotIn(List<Double> values) {
            addCriterion("newness_rate not in", values, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateBetween(Double value1, Double value2) {
            addCriterion("newness_rate between", value1, value2, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andNewnessRateNotBetween(Double value1, Double value2) {
            addCriterion("newness_rate not between", value1, value2, "newnessRate");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNull() {
            addCriterion("item_image is null");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNotNull() {
            addCriterion("item_image is not null");
            return (Criteria) this;
        }

        public Criteria andItemImageEqualTo(String value) {
            addCriterion("item_image =", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotEqualTo(String value) {
            addCriterion("item_image <>", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThan(String value) {
            addCriterion("item_image >", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThanOrEqualTo(String value) {
            addCriterion("item_image >=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThan(String value) {
            addCriterion("item_image <", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThanOrEqualTo(String value) {
            addCriterion("item_image <=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLike(String value) {
            addCriterion("item_image like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotLike(String value) {
            addCriterion("item_image not like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageIn(List<String> values) {
            addCriterion("item_image in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotIn(List<String> values) {
            addCriterion("item_image not in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageBetween(String value1, String value2) {
            addCriterion("item_image between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotBetween(String value1, String value2) {
            addCriterion("item_image not between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andSellAddressIsNull() {
            addCriterion("sell_address is null");
            return (Criteria) this;
        }

        public Criteria andSellAddressIsNotNull() {
            addCriterion("sell_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellAddressEqualTo(String value) {
            addCriterion("sell_address =", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotEqualTo(String value) {
            addCriterion("sell_address <>", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThan(String value) {
            addCriterion("sell_address >", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThanOrEqualTo(String value) {
            addCriterion("sell_address >=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThan(String value) {
            addCriterion("sell_address <", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThanOrEqualTo(String value) {
            addCriterion("sell_address <=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLike(String value) {
            addCriterion("sell_address like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotLike(String value) {
            addCriterion("sell_address not like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressIn(List<String> values) {
            addCriterion("sell_address in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotIn(List<String> values) {
            addCriterion("sell_address not in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressBetween(String value1, String value2) {
            addCriterion("sell_address between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotBetween(String value1, String value2) {
            addCriterion("sell_address not between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNull() {
            addCriterion("collect_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNotNull() {
            addCriterion("collect_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNumEqualTo(Integer value) {
            addCriterion("collect_num =", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotEqualTo(Integer value) {
            addCriterion("collect_num <>", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThan(Integer value) {
            addCriterion("collect_num >", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_num >=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThan(Integer value) {
            addCriterion("collect_num <", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThanOrEqualTo(Integer value) {
            addCriterion("collect_num <=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumIn(List<Integer> values) {
            addCriterion("collect_num in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotIn(List<Integer> values) {
            addCriterion("collect_num not in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumBetween(Integer value1, Integer value2) {
            addCriterion("collect_num between", value1, value2, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_num not between", value1, value2, "collectNum");
            return (Criteria) this;
        }

        public Criteria andSellQqIsNull() {
            addCriterion("sell_qq is null");
            return (Criteria) this;
        }

        public Criteria andSellQqIsNotNull() {
            addCriterion("sell_qq is not null");
            return (Criteria) this;
        }

        public Criteria andSellQqEqualTo(String value) {
            addCriterion("sell_qq =", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqNotEqualTo(String value) {
            addCriterion("sell_qq <>", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqGreaterThan(String value) {
            addCriterion("sell_qq >", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqGreaterThanOrEqualTo(String value) {
            addCriterion("sell_qq >=", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqLessThan(String value) {
            addCriterion("sell_qq <", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqLessThanOrEqualTo(String value) {
            addCriterion("sell_qq <=", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqLike(String value) {
            addCriterion("sell_qq like", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqNotLike(String value) {
            addCriterion("sell_qq not like", value, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqIn(List<String> values) {
            addCriterion("sell_qq in", values, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqNotIn(List<String> values) {
            addCriterion("sell_qq not in", values, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqBetween(String value1, String value2) {
            addCriterion("sell_qq between", value1, value2, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellQqNotBetween(String value1, String value2) {
            addCriterion("sell_qq not between", value1, value2, "sellQq");
            return (Criteria) this;
        }

        public Criteria andSellPhoneIsNull() {
            addCriterion("sell_phone is null");
            return (Criteria) this;
        }

        public Criteria andSellPhoneIsNotNull() {
            addCriterion("sell_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSellPhoneEqualTo(String value) {
            addCriterion("sell_phone =", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneNotEqualTo(String value) {
            addCriterion("sell_phone <>", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneGreaterThan(String value) {
            addCriterion("sell_phone >", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("sell_phone >=", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneLessThan(String value) {
            addCriterion("sell_phone <", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneLessThanOrEqualTo(String value) {
            addCriterion("sell_phone <=", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneLike(String value) {
            addCriterion("sell_phone like", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneNotLike(String value) {
            addCriterion("sell_phone not like", value, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneIn(List<String> values) {
            addCriterion("sell_phone in", values, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneNotIn(List<String> values) {
            addCriterion("sell_phone not in", values, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneBetween(String value1, String value2) {
            addCriterion("sell_phone between", value1, value2, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellPhoneNotBetween(String value1, String value2) {
            addCriterion("sell_phone not between", value1, value2, "sellPhone");
            return (Criteria) this;
        }

        public Criteria andSellWechatIsNull() {
            addCriterion("sell_wechat is null");
            return (Criteria) this;
        }

        public Criteria andSellWechatIsNotNull() {
            addCriterion("sell_wechat is not null");
            return (Criteria) this;
        }

        public Criteria andSellWechatEqualTo(String value) {
            addCriterion("sell_wechat =", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatNotEqualTo(String value) {
            addCriterion("sell_wechat <>", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatGreaterThan(String value) {
            addCriterion("sell_wechat >", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatGreaterThanOrEqualTo(String value) {
            addCriterion("sell_wechat >=", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatLessThan(String value) {
            addCriterion("sell_wechat <", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatLessThanOrEqualTo(String value) {
            addCriterion("sell_wechat <=", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatLike(String value) {
            addCriterion("sell_wechat like", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatNotLike(String value) {
            addCriterion("sell_wechat not like", value, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatIn(List<String> values) {
            addCriterion("sell_wechat in", values, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatNotIn(List<String> values) {
            addCriterion("sell_wechat not in", values, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatBetween(String value1, String value2) {
            addCriterion("sell_wechat between", value1, value2, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andSellWechatNotBetween(String value1, String value2) {
            addCriterion("sell_wechat not between", value1, value2, "sellWechat");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNull() {
            addCriterion("buy_time is null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNotNull() {
            addCriterion("buy_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeEqualTo(Date value) {
            addCriterion("buy_time =", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotEqualTo(Date value) {
            addCriterion("buy_time <>", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThan(Date value) {
            addCriterion("buy_time >", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buy_time >=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThan(Date value) {
            addCriterion("buy_time <", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThanOrEqualTo(Date value) {
            addCriterion("buy_time <=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIn(List<Date> values) {
            addCriterion("buy_time in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotIn(List<Date> values) {
            addCriterion("buy_time not in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeBetween(Date value1, Date value2) {
            addCriterion("buy_time between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotBetween(Date value1, Date value2) {
            addCriterion("buy_time not between", value1, value2, "buyTime");
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

        public Criteria andItemCreatedIsNull() {
            addCriterion("item_created is null");
            return (Criteria) this;
        }

        public Criteria andItemCreatedIsNotNull() {
            addCriterion("item_created is not null");
            return (Criteria) this;
        }

        public Criteria andItemCreatedEqualTo(Date value) {
            addCriterion("item_created =", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedNotEqualTo(Date value) {
            addCriterion("item_created <>", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedGreaterThan(Date value) {
            addCriterion("item_created >", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("item_created >=", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedLessThan(Date value) {
            addCriterion("item_created <", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedLessThanOrEqualTo(Date value) {
            addCriterion("item_created <=", value, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedIn(List<Date> values) {
            addCriterion("item_created in", values, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedNotIn(List<Date> values) {
            addCriterion("item_created not in", values, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedBetween(Date value1, Date value2) {
            addCriterion("item_created between", value1, value2, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemCreatedNotBetween(Date value1, Date value2) {
            addCriterion("item_created not between", value1, value2, "itemCreated");
            return (Criteria) this;
        }

        public Criteria andItemUpdateIsNull() {
            addCriterion("item_update is null");
            return (Criteria) this;
        }

        public Criteria andItemUpdateIsNotNull() {
            addCriterion("item_update is not null");
            return (Criteria) this;
        }

        public Criteria andItemUpdateEqualTo(Date value) {
            addCriterion("item_update =", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateNotEqualTo(Date value) {
            addCriterion("item_update <>", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateGreaterThan(Date value) {
            addCriterion("item_update >", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("item_update >=", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateLessThan(Date value) {
            addCriterion("item_update <", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateLessThanOrEqualTo(Date value) {
            addCriterion("item_update <=", value, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateIn(List<Date> values) {
            addCriterion("item_update in", values, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateNotIn(List<Date> values) {
            addCriterion("item_update not in", values, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateBetween(Date value1, Date value2) {
            addCriterion("item_update between", value1, value2, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemUpdateNotBetween(Date value1, Date value2) {
            addCriterion("item_update not between", value1, value2, "itemUpdate");
            return (Criteria) this;
        }

        public Criteria andItemDownIsNull() {
            addCriterion("item_down is null");
            return (Criteria) this;
        }

        public Criteria andItemDownIsNotNull() {
            addCriterion("item_down is not null");
            return (Criteria) this;
        }

        public Criteria andItemDownEqualTo(Date value) {
            addCriterion("item_down =", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownNotEqualTo(Date value) {
            addCriterion("item_down <>", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownGreaterThan(Date value) {
            addCriterion("item_down >", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownGreaterThanOrEqualTo(Date value) {
            addCriterion("item_down >=", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownLessThan(Date value) {
            addCriterion("item_down <", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownLessThanOrEqualTo(Date value) {
            addCriterion("item_down <=", value, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownIn(List<Date> values) {
            addCriterion("item_down in", values, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownNotIn(List<Date> values) {
            addCriterion("item_down not in", values, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownBetween(Date value1, Date value2) {
            addCriterion("item_down between", value1, value2, "itemDown");
            return (Criteria) this;
        }

        public Criteria andItemDownNotBetween(Date value1, Date value2) {
            addCriterion("item_down not between", value1, value2, "itemDown");
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