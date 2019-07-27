package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageUserExample() {
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

        public Criteria andMessageUserIdIsNull() {
            addCriterion("message_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNotNull() {
            addCriterion("message_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdEqualTo(Integer value) {
            addCriterion("message_user_id =", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotEqualTo(Integer value) {
            addCriterion("message_user_id <>", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThan(Integer value) {
            addCriterion("message_user_id >", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_user_id >=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThan(Integer value) {
            addCriterion("message_user_id <", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_user_id <=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIn(List<Integer> values) {
            addCriterion("message_user_id in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotIn(List<Integer> values) {
            addCriterion("message_user_id not in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdBetween(Integer value1, Integer value2) {
            addCriterion("message_user_id between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_user_id not between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNull() {
            addCriterion("message_type is null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNotNull() {
            addCriterion("message_type is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeEqualTo(String value) {
            addCriterion("message_type =", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotEqualTo(String value) {
            addCriterion("message_type <>", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThan(String value) {
            addCriterion("message_type >", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("message_type >=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThan(String value) {
            addCriterion("message_type <", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThanOrEqualTo(String value) {
            addCriterion("message_type <=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLike(String value) {
            addCriterion("message_type like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotLike(String value) {
            addCriterion("message_type not like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIn(List<String> values) {
            addCriterion("message_type in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotIn(List<String> values) {
            addCriterion("message_type not in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeBetween(String value1, String value2) {
            addCriterion("message_type between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotBetween(String value1, String value2) {
            addCriterion("message_type not between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdIsNull() {
            addCriterion("message_info_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdIsNotNull() {
            addCriterion("message_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdEqualTo(Integer value) {
            addCriterion("message_info_id =", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdNotEqualTo(Integer value) {
            addCriterion("message_info_id <>", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdGreaterThan(Integer value) {
            addCriterion("message_info_id >", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_info_id >=", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdLessThan(Integer value) {
            addCriterion("message_info_id <", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_info_id <=", value, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdIn(List<Integer> values) {
            addCriterion("message_info_id in", values, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdNotIn(List<Integer> values) {
            addCriterion("message_info_id not in", values, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("message_info_id between", value1, value2, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_info_id not between", value1, value2, "messageInfoId");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusIsNull() {
            addCriterion("message_user_status is null");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusIsNotNull() {
            addCriterion("message_user_status is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusEqualTo(Integer value) {
            addCriterion("message_user_status =", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusNotEqualTo(Integer value) {
            addCriterion("message_user_status <>", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusGreaterThan(Integer value) {
            addCriterion("message_user_status >", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_user_status >=", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusLessThan(Integer value) {
            addCriterion("message_user_status <", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusLessThanOrEqualTo(Integer value) {
            addCriterion("message_user_status <=", value, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusIn(List<Integer> values) {
            addCriterion("message_user_status in", values, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusNotIn(List<Integer> values) {
            addCriterion("message_user_status not in", values, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusBetween(Integer value1, Integer value2) {
            addCriterion("message_user_status between", value1, value2, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageUserStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("message_user_status not between", value1, value2, "messageUserStatus");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(Date value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(Date value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(Date value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(Date value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(Date value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(Date value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<Date> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<Date> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(Date value1, Date value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(Date value1, Date value2) {
            addCriterion("message not between", value1, value2, "message");
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