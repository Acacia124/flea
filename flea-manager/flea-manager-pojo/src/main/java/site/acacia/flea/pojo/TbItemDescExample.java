package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbItemDescExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbItemDescExample() {
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

        public Criteria andItemDescIdIsNull() {
            addCriterion("item_desc_id is null");
            return (Criteria) this;
        }

        public Criteria andItemDescIdIsNotNull() {
            addCriterion("item_desc_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescIdEqualTo(Integer value) {
            addCriterion("item_desc_id =", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdNotEqualTo(Integer value) {
            addCriterion("item_desc_id <>", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdGreaterThan(Integer value) {
            addCriterion("item_desc_id >", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_desc_id >=", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdLessThan(Integer value) {
            addCriterion("item_desc_id <", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdLessThanOrEqualTo(Integer value) {
            addCriterion("item_desc_id <=", value, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdIn(List<Integer> values) {
            addCriterion("item_desc_id in", values, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdNotIn(List<Integer> values) {
            addCriterion("item_desc_id not in", values, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdBetween(Integer value1, Integer value2) {
            addCriterion("item_desc_id between", value1, value2, "itemDescId");
            return (Criteria) this;
        }

        public Criteria andItemDescIdNotBetween(Integer value1, Integer value2) {
            addCriterion("item_desc_id not between", value1, value2, "itemDescId");
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

        public Criteria andDescCreateIsNull() {
            addCriterion("desc_create is null");
            return (Criteria) this;
        }

        public Criteria andDescCreateIsNotNull() {
            addCriterion("desc_create is not null");
            return (Criteria) this;
        }

        public Criteria andDescCreateEqualTo(Date value) {
            addCriterion("desc_create =", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateNotEqualTo(Date value) {
            addCriterion("desc_create <>", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateGreaterThan(Date value) {
            addCriterion("desc_create >", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("desc_create >=", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateLessThan(Date value) {
            addCriterion("desc_create <", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateLessThanOrEqualTo(Date value) {
            addCriterion("desc_create <=", value, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateIn(List<Date> values) {
            addCriterion("desc_create in", values, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateNotIn(List<Date> values) {
            addCriterion("desc_create not in", values, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateBetween(Date value1, Date value2) {
            addCriterion("desc_create between", value1, value2, "descCreate");
            return (Criteria) this;
        }

        public Criteria andDescCreateNotBetween(Date value1, Date value2) {
            addCriterion("desc_create not between", value1, value2, "descCreate");
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