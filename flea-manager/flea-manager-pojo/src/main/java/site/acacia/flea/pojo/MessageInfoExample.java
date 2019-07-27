package site.acacia.flea.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageInfoExample() {
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

        public Criteria andMessageTitleIsNull() {
            addCriterion("message_title is null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNotNull() {
            addCriterion("message_title is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleEqualTo(String value) {
            addCriterion("message_title =", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotEqualTo(String value) {
            addCriterion("message_title <>", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThan(String value) {
            addCriterion("message_title >", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("message_title >=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThan(String value) {
            addCriterion("message_title <", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThanOrEqualTo(String value) {
            addCriterion("message_title <=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLike(String value) {
            addCriterion("message_title like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotLike(String value) {
            addCriterion("message_title not like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIn(List<String> values) {
            addCriterion("message_title in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotIn(List<String> values) {
            addCriterion("message_title not in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleBetween(String value1, String value2) {
            addCriterion("message_title between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotBetween(String value1, String value2) {
            addCriterion("message_title not between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTxtIsNull() {
            addCriterion("message_txt is null");
            return (Criteria) this;
        }

        public Criteria andMessageTxtIsNotNull() {
            addCriterion("message_txt is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTxtEqualTo(String value) {
            addCriterion("message_txt =", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtNotEqualTo(String value) {
            addCriterion("message_txt <>", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtGreaterThan(String value) {
            addCriterion("message_txt >", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtGreaterThanOrEqualTo(String value) {
            addCriterion("message_txt >=", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtLessThan(String value) {
            addCriterion("message_txt <", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtLessThanOrEqualTo(String value) {
            addCriterion("message_txt <=", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtLike(String value) {
            addCriterion("message_txt like", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtNotLike(String value) {
            addCriterion("message_txt not like", value, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtIn(List<String> values) {
            addCriterion("message_txt in", values, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtNotIn(List<String> values) {
            addCriterion("message_txt not in", values, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtBetween(String value1, String value2) {
            addCriterion("message_txt between", value1, value2, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageTxtNotBetween(String value1, String value2) {
            addCriterion("message_txt not between", value1, value2, "messageTxt");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIsNull() {
            addCriterion("message_url is null");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIsNotNull() {
            addCriterion("message_url is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUrlEqualTo(String value) {
            addCriterion("message_url =", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotEqualTo(String value) {
            addCriterion("message_url <>", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlGreaterThan(String value) {
            addCriterion("message_url >", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("message_url >=", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLessThan(String value) {
            addCriterion("message_url <", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLessThanOrEqualTo(String value) {
            addCriterion("message_url <=", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLike(String value) {
            addCriterion("message_url like", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotLike(String value) {
            addCriterion("message_url not like", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIn(List<String> values) {
            addCriterion("message_url in", values, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotIn(List<String> values) {
            addCriterion("message_url not in", values, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlBetween(String value1, String value2) {
            addCriterion("message_url between", value1, value2, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotBetween(String value1, String value2) {
            addCriterion("message_url not between", value1, value2, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeIsNull() {
            addCriterion("message_push_time is null");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeIsNotNull() {
            addCriterion("message_push_time is not null");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeEqualTo(Date value) {
            addCriterion("message_push_time =", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeNotEqualTo(Date value) {
            addCriterion("message_push_time <>", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeGreaterThan(Date value) {
            addCriterion("message_push_time >", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("message_push_time >=", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeLessThan(Date value) {
            addCriterion("message_push_time <", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeLessThanOrEqualTo(Date value) {
            addCriterion("message_push_time <=", value, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeIn(List<Date> values) {
            addCriterion("message_push_time in", values, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeNotIn(List<Date> values) {
            addCriterion("message_push_time not in", values, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeBetween(Date value1, Date value2) {
            addCriterion("message_push_time between", value1, value2, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessagePushTimeNotBetween(Date value1, Date value2) {
            addCriterion("message_push_time not between", value1, value2, "messagePushTime");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorIsNull() {
            addCriterion("message_operator is null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorIsNotNull() {
            addCriterion("message_operator is not null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorEqualTo(String value) {
            addCriterion("message_operator =", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNotEqualTo(String value) {
            addCriterion("message_operator <>", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorGreaterThan(String value) {
            addCriterion("message_operator >", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("message_operator >=", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorLessThan(String value) {
            addCriterion("message_operator <", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorLessThanOrEqualTo(String value) {
            addCriterion("message_operator <=", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorLike(String value) {
            addCriterion("message_operator like", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNotLike(String value) {
            addCriterion("message_operator not like", value, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorIn(List<String> values) {
            addCriterion("message_operator in", values, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNotIn(List<String> values) {
            addCriterion("message_operator not in", values, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorBetween(String value1, String value2) {
            addCriterion("message_operator between", value1, value2, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNotBetween(String value1, String value2) {
            addCriterion("message_operator not between", value1, value2, "messageOperator");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickIsNull() {
            addCriterion("message_operator_nick is null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickIsNotNull() {
            addCriterion("message_operator_nick is not null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickEqualTo(String value) {
            addCriterion("message_operator_nick =", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickNotEqualTo(String value) {
            addCriterion("message_operator_nick <>", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickGreaterThan(String value) {
            addCriterion("message_operator_nick >", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickGreaterThanOrEqualTo(String value) {
            addCriterion("message_operator_nick >=", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickLessThan(String value) {
            addCriterion("message_operator_nick <", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickLessThanOrEqualTo(String value) {
            addCriterion("message_operator_nick <=", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickLike(String value) {
            addCriterion("message_operator_nick like", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickNotLike(String value) {
            addCriterion("message_operator_nick not like", value, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickIn(List<String> values) {
            addCriterion("message_operator_nick in", values, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickNotIn(List<String> values) {
            addCriterion("message_operator_nick not in", values, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickBetween(String value1, String value2) {
            addCriterion("message_operator_nick between", value1, value2, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorNickNotBetween(String value1, String value2) {
            addCriterion("message_operator_nick not between", value1, value2, "messageOperatorNick");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterIsNull() {
            addCriterion("message_operator_avater is null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterIsNotNull() {
            addCriterion("message_operator_avater is not null");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterEqualTo(String value) {
            addCriterion("message_operator_avater =", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterNotEqualTo(String value) {
            addCriterion("message_operator_avater <>", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterGreaterThan(String value) {
            addCriterion("message_operator_avater >", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterGreaterThanOrEqualTo(String value) {
            addCriterion("message_operator_avater >=", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterLessThan(String value) {
            addCriterion("message_operator_avater <", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterLessThanOrEqualTo(String value) {
            addCriterion("message_operator_avater <=", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterLike(String value) {
            addCriterion("message_operator_avater like", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterNotLike(String value) {
            addCriterion("message_operator_avater not like", value, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterIn(List<String> values) {
            addCriterion("message_operator_avater in", values, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterNotIn(List<String> values) {
            addCriterion("message_operator_avater not in", values, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterBetween(String value1, String value2) {
            addCriterion("message_operator_avater between", value1, value2, "messageOperatorAvater");
            return (Criteria) this;
        }

        public Criteria andMessageOperatorAvaterNotBetween(String value1, String value2) {
            addCriterion("message_operator_avater not between", value1, value2, "messageOperatorAvater");
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