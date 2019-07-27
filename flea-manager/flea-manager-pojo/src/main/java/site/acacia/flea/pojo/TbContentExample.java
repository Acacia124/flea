package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbContentExample() {
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

        public Criteria andContentIdIsNull() {
            addCriterion("content_id is null");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNotNull() {
            addCriterion("content_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentIdEqualTo(Integer value) {
            addCriterion("content_id =", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotEqualTo(Integer value) {
            addCriterion("content_id <>", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThan(Integer value) {
            addCriterion("content_id >", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_id >=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThan(Integer value) {
            addCriterion("content_id <", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_id <=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdIn(List<Integer> values) {
            addCriterion("content_id in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotIn(List<Integer> values) {
            addCriterion("content_id not in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdBetween(Integer value1, Integer value2) {
            addCriterion("content_id between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_id not between", value1, value2, "contentId");
            return (Criteria) this;
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

        public Criteria andContentTitleIsNull() {
            addCriterion("content_title is null");
            return (Criteria) this;
        }

        public Criteria andContentTitleIsNotNull() {
            addCriterion("content_title is not null");
            return (Criteria) this;
        }

        public Criteria andContentTitleEqualTo(String value) {
            addCriterion("content_title =", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotEqualTo(String value) {
            addCriterion("content_title <>", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleGreaterThan(String value) {
            addCriterion("content_title >", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleGreaterThanOrEqualTo(String value) {
            addCriterion("content_title >=", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLessThan(String value) {
            addCriterion("content_title <", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLessThanOrEqualTo(String value) {
            addCriterion("content_title <=", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLike(String value) {
            addCriterion("content_title like", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotLike(String value) {
            addCriterion("content_title not like", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleIn(List<String> values) {
            addCriterion("content_title in", values, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotIn(List<String> values) {
            addCriterion("content_title not in", values, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleBetween(String value1, String value2) {
            addCriterion("content_title between", value1, value2, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotBetween(String value1, String value2) {
            addCriterion("content_title not between", value1, value2, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNull() {
            addCriterion("sub_title is null");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNotNull() {
            addCriterion("sub_title is not null");
            return (Criteria) this;
        }

        public Criteria andSubTitleEqualTo(String value) {
            addCriterion("sub_title =", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotEqualTo(String value) {
            addCriterion("sub_title <>", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThan(String value) {
            addCriterion("sub_title >", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_title >=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThan(String value) {
            addCriterion("sub_title <", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThanOrEqualTo(String value) {
            addCriterion("sub_title <=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLike(String value) {
            addCriterion("sub_title like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotLike(String value) {
            addCriterion("sub_title not like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleIn(List<String> values) {
            addCriterion("sub_title in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotIn(List<String> values) {
            addCriterion("sub_title not in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleBetween(String value1, String value2) {
            addCriterion("sub_title between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotBetween(String value1, String value2) {
            addCriterion("sub_title not between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andTitleDescIsNull() {
            addCriterion("title_desc is null");
            return (Criteria) this;
        }

        public Criteria andTitleDescIsNotNull() {
            addCriterion("title_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTitleDescEqualTo(String value) {
            addCriterion("title_desc =", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotEqualTo(String value) {
            addCriterion("title_desc <>", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescGreaterThan(String value) {
            addCriterion("title_desc >", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescGreaterThanOrEqualTo(String value) {
            addCriterion("title_desc >=", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLessThan(String value) {
            addCriterion("title_desc <", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLessThanOrEqualTo(String value) {
            addCriterion("title_desc <=", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLike(String value) {
            addCriterion("title_desc like", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotLike(String value) {
            addCriterion("title_desc not like", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescIn(List<String> values) {
            addCriterion("title_desc in", values, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotIn(List<String> values) {
            addCriterion("title_desc not in", values, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescBetween(String value1, String value2) {
            addCriterion("title_desc between", value1, value2, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotBetween(String value1, String value2) {
            addCriterion("title_desc not between", value1, value2, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andContentPicIsNull() {
            addCriterion("content_pic is null");
            return (Criteria) this;
        }

        public Criteria andContentPicIsNotNull() {
            addCriterion("content_pic is not null");
            return (Criteria) this;
        }

        public Criteria andContentPicEqualTo(String value) {
            addCriterion("content_pic =", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicNotEqualTo(String value) {
            addCriterion("content_pic <>", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicGreaterThan(String value) {
            addCriterion("content_pic >", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicGreaterThanOrEqualTo(String value) {
            addCriterion("content_pic >=", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicLessThan(String value) {
            addCriterion("content_pic <", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicLessThanOrEqualTo(String value) {
            addCriterion("content_pic <=", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicLike(String value) {
            addCriterion("content_pic like", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicNotLike(String value) {
            addCriterion("content_pic not like", value, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicIn(List<String> values) {
            addCriterion("content_pic in", values, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicNotIn(List<String> values) {
            addCriterion("content_pic not in", values, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicBetween(String value1, String value2) {
            addCriterion("content_pic between", value1, value2, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentPicNotBetween(String value1, String value2) {
            addCriterion("content_pic not between", value1, value2, "contentPic");
            return (Criteria) this;
        }

        public Criteria andContentUrlIsNull() {
            addCriterion("content_url is null");
            return (Criteria) this;
        }

        public Criteria andContentUrlIsNotNull() {
            addCriterion("content_url is not null");
            return (Criteria) this;
        }

        public Criteria andContentUrlEqualTo(String value) {
            addCriterion("content_url =", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotEqualTo(String value) {
            addCriterion("content_url <>", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlGreaterThan(String value) {
            addCriterion("content_url >", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlGreaterThanOrEqualTo(String value) {
            addCriterion("content_url >=", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLessThan(String value) {
            addCriterion("content_url <", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLessThanOrEqualTo(String value) {
            addCriterion("content_url <=", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLike(String value) {
            addCriterion("content_url like", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotLike(String value) {
            addCriterion("content_url not like", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlIn(List<String> values) {
            addCriterion("content_url in", values, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotIn(List<String> values) {
            addCriterion("content_url not in", values, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlBetween(String value1, String value2) {
            addCriterion("content_url between", value1, value2, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotBetween(String value1, String value2) {
            addCriterion("content_url not between", value1, value2, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentCreateIsNull() {
            addCriterion("content_create is null");
            return (Criteria) this;
        }

        public Criteria andContentCreateIsNotNull() {
            addCriterion("content_create is not null");
            return (Criteria) this;
        }

        public Criteria andContentCreateEqualTo(Date value) {
            addCriterion("content_create =", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateNotEqualTo(Date value) {
            addCriterion("content_create <>", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateGreaterThan(Date value) {
            addCriterion("content_create >", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("content_create >=", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateLessThan(Date value) {
            addCriterion("content_create <", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateLessThanOrEqualTo(Date value) {
            addCriterion("content_create <=", value, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateIn(List<Date> values) {
            addCriterion("content_create in", values, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateNotIn(List<Date> values) {
            addCriterion("content_create not in", values, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateBetween(Date value1, Date value2) {
            addCriterion("content_create between", value1, value2, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentCreateNotBetween(Date value1, Date value2) {
            addCriterion("content_create not between", value1, value2, "contentCreate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateIsNull() {
            addCriterion("content_update is null");
            return (Criteria) this;
        }

        public Criteria andContentUpdateIsNotNull() {
            addCriterion("content_update is not null");
            return (Criteria) this;
        }

        public Criteria andContentUpdateEqualTo(Date value) {
            addCriterion("content_update =", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateNotEqualTo(Date value) {
            addCriterion("content_update <>", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateGreaterThan(Date value) {
            addCriterion("content_update >", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("content_update >=", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateLessThan(Date value) {
            addCriterion("content_update <", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateLessThanOrEqualTo(Date value) {
            addCriterion("content_update <=", value, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateIn(List<Date> values) {
            addCriterion("content_update in", values, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateNotIn(List<Date> values) {
            addCriterion("content_update not in", values, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateBetween(Date value1, Date value2) {
            addCriterion("content_update between", value1, value2, "contentUpdate");
            return (Criteria) this;
        }

        public Criteria andContentUpdateNotBetween(Date value1, Date value2) {
            addCriterion("content_update not between", value1, value2, "contentUpdate");
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