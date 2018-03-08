package com.PT.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdIsNull() {
            addCriterion("opt_usr_id is null");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdIsNotNull() {
            addCriterion("opt_usr_id is not null");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdEqualTo(Integer value) {
            addCriterion("opt_usr_id =", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdNotEqualTo(Integer value) {
            addCriterion("opt_usr_id <>", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdGreaterThan(Integer value) {
            addCriterion("opt_usr_id >", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("opt_usr_id >=", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdLessThan(Integer value) {
            addCriterion("opt_usr_id <", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdLessThanOrEqualTo(Integer value) {
            addCriterion("opt_usr_id <=", value, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdIn(List<Integer> values) {
            addCriterion("opt_usr_id in", values, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdNotIn(List<Integer> values) {
            addCriterion("opt_usr_id not in", values, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdBetween(Integer value1, Integer value2) {
            addCriterion("opt_usr_id between", value1, value2, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptUsrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("opt_usr_id not between", value1, value2, "optUsrId");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("opt_type is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("opt_type is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(String value) {
            addCriterion("opt_type =", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(String value) {
            addCriterion("opt_type <>", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(String value) {
            addCriterion("opt_type >", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("opt_type >=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(String value) {
            addCriterion("opt_type <", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(String value) {
            addCriterion("opt_type <=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLike(String value) {
            addCriterion("opt_type like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotLike(String value) {
            addCriterion("opt_type not like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<String> values) {
            addCriterion("opt_type in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<String> values) {
            addCriterion("opt_type not in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(String value1, String value2) {
            addCriterion("opt_type between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(String value1, String value2) {
            addCriterion("opt_type not between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptDescpIsNull() {
            addCriterion("opt_descp is null");
            return (Criteria) this;
        }

        public Criteria andOptDescpIsNotNull() {
            addCriterion("opt_descp is not null");
            return (Criteria) this;
        }

        public Criteria andOptDescpEqualTo(String value) {
            addCriterion("opt_descp =", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpNotEqualTo(String value) {
            addCriterion("opt_descp <>", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpGreaterThan(String value) {
            addCriterion("opt_descp >", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpGreaterThanOrEqualTo(String value) {
            addCriterion("opt_descp >=", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpLessThan(String value) {
            addCriterion("opt_descp <", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpLessThanOrEqualTo(String value) {
            addCriterion("opt_descp <=", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpLike(String value) {
            addCriterion("opt_descp like", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpNotLike(String value) {
            addCriterion("opt_descp not like", value, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpIn(List<String> values) {
            addCriterion("opt_descp in", values, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpNotIn(List<String> values) {
            addCriterion("opt_descp not in", values, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpBetween(String value1, String value2) {
            addCriterion("opt_descp between", value1, value2, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOptDescpNotBetween(String value1, String value2) {
            addCriterion("opt_descp not between", value1, value2, "optDescp");
            return (Criteria) this;
        }

        public Criteria andOperationAtIsNull() {
            addCriterion("operation_at is null");
            return (Criteria) this;
        }

        public Criteria andOperationAtIsNotNull() {
            addCriterion("operation_at is not null");
            return (Criteria) this;
        }

        public Criteria andOperationAtEqualTo(Date value) {
            addCriterion("operation_at =", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtNotEqualTo(Date value) {
            addCriterion("operation_at <>", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtGreaterThan(Date value) {
            addCriterion("operation_at >", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtGreaterThanOrEqualTo(Date value) {
            addCriterion("operation_at >=", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtLessThan(Date value) {
            addCriterion("operation_at <", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtLessThanOrEqualTo(Date value) {
            addCriterion("operation_at <=", value, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtIn(List<Date> values) {
            addCriterion("operation_at in", values, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtNotIn(List<Date> values) {
            addCriterion("operation_at not in", values, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtBetween(Date value1, Date value2) {
            addCriterion("operation_at between", value1, value2, "operationAt");
            return (Criteria) this;
        }

        public Criteria andOperationAtNotBetween(Date value1, Date value2) {
            addCriterion("operation_at not between", value1, value2, "operationAt");
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