package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbInformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbInformationExample() {
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

        public Criteria andInformationIdIsNull() {
            addCriterion("information_id is null");
            return (Criteria) this;
        }

        public Criteria andInformationIdIsNotNull() {
            addCriterion("information_id is not null");
            return (Criteria) this;
        }

        public Criteria andInformationIdEqualTo(Integer value) {
            addCriterion("information_id =", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdNotEqualTo(Integer value) {
            addCriterion("information_id <>", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdGreaterThan(Integer value) {
            addCriterion("information_id >", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("information_id >=", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdLessThan(Integer value) {
            addCriterion("information_id <", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdLessThanOrEqualTo(Integer value) {
            addCriterion("information_id <=", value, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdIn(List<Integer> values) {
            addCriterion("information_id in", values, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdNotIn(List<Integer> values) {
            addCriterion("information_id not in", values, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdBetween(Integer value1, Integer value2) {
            addCriterion("information_id between", value1, value2, "informationId");
            return (Criteria) this;
        }

        public Criteria andInformationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("information_id not between", value1, value2, "informationId");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidIsNull() {
            addCriterion("in_send_openid is null");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidIsNotNull() {
            addCriterion("in_send_openid is not null");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidEqualTo(String value) {
            addCriterion("in_send_openid =", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidNotEqualTo(String value) {
            addCriterion("in_send_openid <>", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidGreaterThan(String value) {
            addCriterion("in_send_openid >", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("in_send_openid >=", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidLessThan(String value) {
            addCriterion("in_send_openid <", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidLessThanOrEqualTo(String value) {
            addCriterion("in_send_openid <=", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidLike(String value) {
            addCriterion("in_send_openid like", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidNotLike(String value) {
            addCriterion("in_send_openid not like", value, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidIn(List<String> values) {
            addCriterion("in_send_openid in", values, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidNotIn(List<String> values) {
            addCriterion("in_send_openid not in", values, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidBetween(String value1, String value2) {
            addCriterion("in_send_openid between", value1, value2, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInSendOpenidNotBetween(String value1, String value2) {
            addCriterion("in_send_openid not between", value1, value2, "inSendOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidIsNull() {
            addCriterion("in_accept_openid is null");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidIsNotNull() {
            addCriterion("in_accept_openid is not null");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidEqualTo(String value) {
            addCriterion("in_accept_openid =", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidNotEqualTo(String value) {
            addCriterion("in_accept_openid <>", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidGreaterThan(String value) {
            addCriterion("in_accept_openid >", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("in_accept_openid >=", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidLessThan(String value) {
            addCriterion("in_accept_openid <", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidLessThanOrEqualTo(String value) {
            addCriterion("in_accept_openid <=", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidLike(String value) {
            addCriterion("in_accept_openid like", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidNotLike(String value) {
            addCriterion("in_accept_openid not like", value, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidIn(List<String> values) {
            addCriterion("in_accept_openid in", values, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidNotIn(List<String> values) {
            addCriterion("in_accept_openid not in", values, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidBetween(String value1, String value2) {
            addCriterion("in_accept_openid between", value1, value2, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInAcceptOpenidNotBetween(String value1, String value2) {
            addCriterion("in_accept_openid not between", value1, value2, "inAcceptOpenid");
            return (Criteria) this;
        }

        public Criteria andInformationContentIsNull() {
            addCriterion("information_content is null");
            return (Criteria) this;
        }

        public Criteria andInformationContentIsNotNull() {
            addCriterion("information_content is not null");
            return (Criteria) this;
        }

        public Criteria andInformationContentEqualTo(String value) {
            addCriterion("information_content =", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentNotEqualTo(String value) {
            addCriterion("information_content <>", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentGreaterThan(String value) {
            addCriterion("information_content >", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentGreaterThanOrEqualTo(String value) {
            addCriterion("information_content >=", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentLessThan(String value) {
            addCriterion("information_content <", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentLessThanOrEqualTo(String value) {
            addCriterion("information_content <=", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentLike(String value) {
            addCriterion("information_content like", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentNotLike(String value) {
            addCriterion("information_content not like", value, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentIn(List<String> values) {
            addCriterion("information_content in", values, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentNotIn(List<String> values) {
            addCriterion("information_content not in", values, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentBetween(String value1, String value2) {
            addCriterion("information_content between", value1, value2, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationContentNotBetween(String value1, String value2) {
            addCriterion("information_content not between", value1, value2, "informationContent");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderIsNull() {
            addCriterion("information_is_reader is null");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderIsNotNull() {
            addCriterion("information_is_reader is not null");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderEqualTo(Byte value) {
            addCriterion("information_is_reader =", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderNotEqualTo(Byte value) {
            addCriterion("information_is_reader <>", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderGreaterThan(Byte value) {
            addCriterion("information_is_reader >", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderGreaterThanOrEqualTo(Byte value) {
            addCriterion("information_is_reader >=", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderLessThan(Byte value) {
            addCriterion("information_is_reader <", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderLessThanOrEqualTo(Byte value) {
            addCriterion("information_is_reader <=", value, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderIn(List<Byte> values) {
            addCriterion("information_is_reader in", values, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderNotIn(List<Byte> values) {
            addCriterion("information_is_reader not in", values, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderBetween(Byte value1, Byte value2) {
            addCriterion("information_is_reader between", value1, value2, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationIsReaderNotBetween(Byte value1, Byte value2) {
            addCriterion("information_is_reader not between", value1, value2, "informationIsReader");
            return (Criteria) this;
        }

        public Criteria andInformationStatusIsNull() {
            addCriterion("information_status is null");
            return (Criteria) this;
        }

        public Criteria andInformationStatusIsNotNull() {
            addCriterion("information_status is not null");
            return (Criteria) this;
        }

        public Criteria andInformationStatusEqualTo(Byte value) {
            addCriterion("information_status =", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusNotEqualTo(Byte value) {
            addCriterion("information_status <>", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusGreaterThan(Byte value) {
            addCriterion("information_status >", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("information_status >=", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusLessThan(Byte value) {
            addCriterion("information_status <", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusLessThanOrEqualTo(Byte value) {
            addCriterion("information_status <=", value, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusIn(List<Byte> values) {
            addCriterion("information_status in", values, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusNotIn(List<Byte> values) {
            addCriterion("information_status not in", values, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusBetween(Byte value1, Byte value2) {
            addCriterion("information_status between", value1, value2, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("information_status not between", value1, value2, "informationStatus");
            return (Criteria) this;
        }

        public Criteria andInformationCreateIsNull() {
            addCriterion("information_create is null");
            return (Criteria) this;
        }

        public Criteria andInformationCreateIsNotNull() {
            addCriterion("information_create is not null");
            return (Criteria) this;
        }

        public Criteria andInformationCreateEqualTo(Date value) {
            addCriterion("information_create =", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateNotEqualTo(Date value) {
            addCriterion("information_create <>", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateGreaterThan(Date value) {
            addCriterion("information_create >", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("information_create >=", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateLessThan(Date value) {
            addCriterion("information_create <", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateLessThanOrEqualTo(Date value) {
            addCriterion("information_create <=", value, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateIn(List<Date> values) {
            addCriterion("information_create in", values, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateNotIn(List<Date> values) {
            addCriterion("information_create not in", values, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateBetween(Date value1, Date value2) {
            addCriterion("information_create between", value1, value2, "informationCreate");
            return (Criteria) this;
        }

        public Criteria andInformationCreateNotBetween(Date value1, Date value2) {
            addCriterion("information_create not between", value1, value2, "informationCreate");
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