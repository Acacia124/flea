package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbItemCatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbItemCatExample() {
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

        public Criteria andCatNameIsNull() {
            addCriterion("cat_name is null");
            return (Criteria) this;
        }

        public Criteria andCatNameIsNotNull() {
            addCriterion("cat_name is not null");
            return (Criteria) this;
        }

        public Criteria andCatNameEqualTo(String value) {
            addCriterion("cat_name =", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotEqualTo(String value) {
            addCriterion("cat_name <>", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThan(String value) {
            addCriterion("cat_name >", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThanOrEqualTo(String value) {
            addCriterion("cat_name >=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThan(String value) {
            addCriterion("cat_name <", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThanOrEqualTo(String value) {
            addCriterion("cat_name <=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLike(String value) {
            addCriterion("cat_name like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotLike(String value) {
            addCriterion("cat_name not like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameIn(List<String> values) {
            addCriterion("cat_name in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotIn(List<String> values) {
            addCriterion("cat_name not in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameBetween(String value1, String value2) {
            addCriterion("cat_name between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotBetween(String value1, String value2) {
            addCriterion("cat_name not between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderIsNull() {
            addCriterion("cat_sort_order is null");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderIsNotNull() {
            addCriterion("cat_sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderEqualTo(Integer value) {
            addCriterion("cat_sort_order =", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderNotEqualTo(Integer value) {
            addCriterion("cat_sort_order <>", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderGreaterThan(Integer value) {
            addCriterion("cat_sort_order >", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_sort_order >=", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderLessThan(Integer value) {
            addCriterion("cat_sort_order <", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderLessThanOrEqualTo(Integer value) {
            addCriterion("cat_sort_order <=", value, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderIn(List<Integer> values) {
            addCriterion("cat_sort_order in", values, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderNotIn(List<Integer> values) {
            addCriterion("cat_sort_order not in", values, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderBetween(Integer value1, Integer value2) {
            addCriterion("cat_sort_order between", value1, value2, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatSortOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_sort_order not between", value1, value2, "catSortOrder");
            return (Criteria) this;
        }

        public Criteria andCatImageIsNull() {
            addCriterion("cat_image is null");
            return (Criteria) this;
        }

        public Criteria andCatImageIsNotNull() {
            addCriterion("cat_image is not null");
            return (Criteria) this;
        }

        public Criteria andCatImageEqualTo(String value) {
            addCriterion("cat_image =", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageNotEqualTo(String value) {
            addCriterion("cat_image <>", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageGreaterThan(String value) {
            addCriterion("cat_image >", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageGreaterThanOrEqualTo(String value) {
            addCriterion("cat_image >=", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageLessThan(String value) {
            addCriterion("cat_image <", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageLessThanOrEqualTo(String value) {
            addCriterion("cat_image <=", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageLike(String value) {
            addCriterion("cat_image like", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageNotLike(String value) {
            addCriterion("cat_image not like", value, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageIn(List<String> values) {
            addCriterion("cat_image in", values, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageNotIn(List<String> values) {
            addCriterion("cat_image not in", values, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageBetween(String value1, String value2) {
            addCriterion("cat_image between", value1, value2, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatImageNotBetween(String value1, String value2) {
            addCriterion("cat_image not between", value1, value2, "catImage");
            return (Criteria) this;
        }

        public Criteria andCatIsParentIsNull() {
            addCriterion("cat_is_parent is null");
            return (Criteria) this;
        }

        public Criteria andCatIsParentIsNotNull() {
            addCriterion("cat_is_parent is not null");
            return (Criteria) this;
        }

        public Criteria andCatIsParentEqualTo(Byte value) {
            addCriterion("cat_is_parent =", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentNotEqualTo(Byte value) {
            addCriterion("cat_is_parent <>", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentGreaterThan(Byte value) {
            addCriterion("cat_is_parent >", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentGreaterThanOrEqualTo(Byte value) {
            addCriterion("cat_is_parent >=", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentLessThan(Byte value) {
            addCriterion("cat_is_parent <", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentLessThanOrEqualTo(Byte value) {
            addCriterion("cat_is_parent <=", value, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentIn(List<Byte> values) {
            addCriterion("cat_is_parent in", values, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentNotIn(List<Byte> values) {
            addCriterion("cat_is_parent not in", values, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentBetween(Byte value1, Byte value2) {
            addCriterion("cat_is_parent between", value1, value2, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatIsParentNotBetween(Byte value1, Byte value2) {
            addCriterion("cat_is_parent not between", value1, value2, "catIsParent");
            return (Criteria) this;
        }

        public Criteria andCatStatusIsNull() {
            addCriterion("cat_status is null");
            return (Criteria) this;
        }

        public Criteria andCatStatusIsNotNull() {
            addCriterion("cat_status is not null");
            return (Criteria) this;
        }

        public Criteria andCatStatusEqualTo(Byte value) {
            addCriterion("cat_status =", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusNotEqualTo(Byte value) {
            addCriterion("cat_status <>", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusGreaterThan(Byte value) {
            addCriterion("cat_status >", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("cat_status >=", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusLessThan(Byte value) {
            addCriterion("cat_status <", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusLessThanOrEqualTo(Byte value) {
            addCriterion("cat_status <=", value, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusIn(List<Byte> values) {
            addCriterion("cat_status in", values, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusNotIn(List<Byte> values) {
            addCriterion("cat_status not in", values, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusBetween(Byte value1, Byte value2) {
            addCriterion("cat_status between", value1, value2, "catStatus");
            return (Criteria) this;
        }

        public Criteria andCatStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("cat_status not between", value1, value2, "catStatus");
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