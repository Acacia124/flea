package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbContentCatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbContentCatExample() {
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

        public Criteria andConCatIdIsNull() {
            addCriterion("con_cat_id is null");
            return (Criteria) this;
        }

        public Criteria andConCatIdIsNotNull() {
            addCriterion("con_cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andConCatIdEqualTo(Integer value) {
            addCriterion("con_cat_id =", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdNotEqualTo(Integer value) {
            addCriterion("con_cat_id <>", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdGreaterThan(Integer value) {
            addCriterion("con_cat_id >", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("con_cat_id >=", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdLessThan(Integer value) {
            addCriterion("con_cat_id <", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("con_cat_id <=", value, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdIn(List<Integer> values) {
            addCriterion("con_cat_id in", values, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdNotIn(List<Integer> values) {
            addCriterion("con_cat_id not in", values, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_id between", value1, value2, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_id not between", value1, value2, "conCatId");
            return (Criteria) this;
        }

        public Criteria andConCatNameIsNull() {
            addCriterion("con_cat_name is null");
            return (Criteria) this;
        }

        public Criteria andConCatNameIsNotNull() {
            addCriterion("con_cat_name is not null");
            return (Criteria) this;
        }

        public Criteria andConCatNameEqualTo(String value) {
            addCriterion("con_cat_name =", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameNotEqualTo(String value) {
            addCriterion("con_cat_name <>", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameGreaterThan(String value) {
            addCriterion("con_cat_name >", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameGreaterThanOrEqualTo(String value) {
            addCriterion("con_cat_name >=", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameLessThan(String value) {
            addCriterion("con_cat_name <", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameLessThanOrEqualTo(String value) {
            addCriterion("con_cat_name <=", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameLike(String value) {
            addCriterion("con_cat_name like", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameNotLike(String value) {
            addCriterion("con_cat_name not like", value, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameIn(List<String> values) {
            addCriterion("con_cat_name in", values, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameNotIn(List<String> values) {
            addCriterion("con_cat_name not in", values, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameBetween(String value1, String value2) {
            addCriterion("con_cat_name between", value1, value2, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatNameNotBetween(String value1, String value2) {
            addCriterion("con_cat_name not between", value1, value2, "conCatName");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdIsNull() {
            addCriterion("con_cat_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdIsNotNull() {
            addCriterion("con_cat_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdEqualTo(Integer value) {
            addCriterion("con_cat_parent_id =", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdNotEqualTo(Integer value) {
            addCriterion("con_cat_parent_id <>", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdGreaterThan(Integer value) {
            addCriterion("con_cat_parent_id >", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("con_cat_parent_id >=", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdLessThan(Integer value) {
            addCriterion("con_cat_parent_id <", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("con_cat_parent_id <=", value, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdIn(List<Integer> values) {
            addCriterion("con_cat_parent_id in", values, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdNotIn(List<Integer> values) {
            addCriterion("con_cat_parent_id not in", values, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_parent_id between", value1, value2, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_parent_id not between", value1, value2, "conCatParentId");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderIsNull() {
            addCriterion("con_cat_sort_order is null");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderIsNotNull() {
            addCriterion("con_cat_sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderEqualTo(Integer value) {
            addCriterion("con_cat_sort_order =", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderNotEqualTo(Integer value) {
            addCriterion("con_cat_sort_order <>", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderGreaterThan(Integer value) {
            addCriterion("con_cat_sort_order >", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("con_cat_sort_order >=", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderLessThan(Integer value) {
            addCriterion("con_cat_sort_order <", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderLessThanOrEqualTo(Integer value) {
            addCriterion("con_cat_sort_order <=", value, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderIn(List<Integer> values) {
            addCriterion("con_cat_sort_order in", values, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderNotIn(List<Integer> values) {
            addCriterion("con_cat_sort_order not in", values, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_sort_order between", value1, value2, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatSortOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("con_cat_sort_order not between", value1, value2, "conCatSortOrder");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentIsNull() {
            addCriterion("con_cat_is_parent is null");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentIsNotNull() {
            addCriterion("con_cat_is_parent is not null");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentEqualTo(Byte value) {
            addCriterion("con_cat_is_parent =", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentNotEqualTo(Byte value) {
            addCriterion("con_cat_is_parent <>", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentGreaterThan(Byte value) {
            addCriterion("con_cat_is_parent >", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentGreaterThanOrEqualTo(Byte value) {
            addCriterion("con_cat_is_parent >=", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentLessThan(Byte value) {
            addCriterion("con_cat_is_parent <", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentLessThanOrEqualTo(Byte value) {
            addCriterion("con_cat_is_parent <=", value, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentIn(List<Byte> values) {
            addCriterion("con_cat_is_parent in", values, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentNotIn(List<Byte> values) {
            addCriterion("con_cat_is_parent not in", values, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentBetween(Byte value1, Byte value2) {
            addCriterion("con_cat_is_parent between", value1, value2, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatIsParentNotBetween(Byte value1, Byte value2) {
            addCriterion("con_cat_is_parent not between", value1, value2, "conCatIsParent");
            return (Criteria) this;
        }

        public Criteria andConCatStatusIsNull() {
            addCriterion("con_cat_status is null");
            return (Criteria) this;
        }

        public Criteria andConCatStatusIsNotNull() {
            addCriterion("con_cat_status is not null");
            return (Criteria) this;
        }

        public Criteria andConCatStatusEqualTo(Byte value) {
            addCriterion("con_cat_status =", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusNotEqualTo(Byte value) {
            addCriterion("con_cat_status <>", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusGreaterThan(Byte value) {
            addCriterion("con_cat_status >", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("con_cat_status >=", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusLessThan(Byte value) {
            addCriterion("con_cat_status <", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusLessThanOrEqualTo(Byte value) {
            addCriterion("con_cat_status <=", value, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusIn(List<Byte> values) {
            addCriterion("con_cat_status in", values, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusNotIn(List<Byte> values) {
            addCriterion("con_cat_status not in", values, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusBetween(Byte value1, Byte value2) {
            addCriterion("con_cat_status between", value1, value2, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andConCatStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("con_cat_status not between", value1, value2, "conCatStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
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