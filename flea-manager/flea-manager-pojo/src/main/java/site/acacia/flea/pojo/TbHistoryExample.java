package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbHistoryExample() {
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

        public Criteria andHisIdIsNull() {
            addCriterion("his_id is null");
            return (Criteria) this;
        }

        public Criteria andHisIdIsNotNull() {
            addCriterion("his_id is not null");
            return (Criteria) this;
        }

        public Criteria andHisIdEqualTo(Integer value) {
            addCriterion("his_id =", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdNotEqualTo(Integer value) {
            addCriterion("his_id <>", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdGreaterThan(Integer value) {
            addCriterion("his_id >", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("his_id >=", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdLessThan(Integer value) {
            addCriterion("his_id <", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdLessThanOrEqualTo(Integer value) {
            addCriterion("his_id <=", value, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdIn(List<Integer> values) {
            addCriterion("his_id in", values, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdNotIn(List<Integer> values) {
            addCriterion("his_id not in", values, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdBetween(Integer value1, Integer value2) {
            addCriterion("his_id between", value1, value2, "hisId");
            return (Criteria) this;
        }

        public Criteria andHisIdNotBetween(Integer value1, Integer value2) {
            addCriterion("his_id not between", value1, value2, "hisId");
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

        public Criteria andHisCreateIsNull() {
            addCriterion("his_create is null");
            return (Criteria) this;
        }

        public Criteria andHisCreateIsNotNull() {
            addCriterion("his_create is not null");
            return (Criteria) this;
        }

        public Criteria andHisCreateEqualTo(Date value) {
            addCriterion("his_create =", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateNotEqualTo(Date value) {
            addCriterion("his_create <>", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateGreaterThan(Date value) {
            addCriterion("his_create >", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("his_create >=", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateLessThan(Date value) {
            addCriterion("his_create <", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateLessThanOrEqualTo(Date value) {
            addCriterion("his_create <=", value, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateIn(List<Date> values) {
            addCriterion("his_create in", values, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateNotIn(List<Date> values) {
            addCriterion("his_create not in", values, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateBetween(Date value1, Date value2) {
            addCriterion("his_create between", value1, value2, "hisCreate");
            return (Criteria) this;
        }

        public Criteria andHisCreateNotBetween(Date value1, Date value2) {
            addCriterion("his_create not between", value1, value2, "hisCreate");
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