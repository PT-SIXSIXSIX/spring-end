package com.PT.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SettleAccRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SettleAccRecordExample() {
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

        public Criteria andSetAccIdIsNull() {
            addCriterion("set_acc_id is null");
            return (Criteria) this;
        }

        public Criteria andSetAccIdIsNotNull() {
            addCriterion("set_acc_id is not null");
            return (Criteria) this;
        }

        public Criteria andSetAccIdEqualTo(Integer value) {
            addCriterion("set_acc_id =", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdNotEqualTo(Integer value) {
            addCriterion("set_acc_id <>", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdGreaterThan(Integer value) {
            addCriterion("set_acc_id >", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("set_acc_id >=", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdLessThan(Integer value) {
            addCriterion("set_acc_id <", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdLessThanOrEqualTo(Integer value) {
            addCriterion("set_acc_id <=", value, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdIn(List<Integer> values) {
            addCriterion("set_acc_id in", values, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdNotIn(List<Integer> values) {
            addCriterion("set_acc_id not in", values, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdBetween(Integer value1, Integer value2) {
            addCriterion("set_acc_id between", value1, value2, "setAccId");
            return (Criteria) this;
        }

        public Criteria andSetAccIdNotBetween(Integer value1, Integer value2) {
            addCriterion("set_acc_id not between", value1, value2, "setAccId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRevMonIdIsNull() {
            addCriterion("rev_mon_id is null");
            return (Criteria) this;
        }

        public Criteria andRevMonIdIsNotNull() {
            addCriterion("rev_mon_id is not null");
            return (Criteria) this;
        }

        public Criteria andRevMonIdEqualTo(Integer value) {
            addCriterion("rev_mon_id =", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdNotEqualTo(Integer value) {
            addCriterion("rev_mon_id <>", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdGreaterThan(Integer value) {
            addCriterion("rev_mon_id >", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rev_mon_id >=", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdLessThan(Integer value) {
            addCriterion("rev_mon_id <", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdLessThanOrEqualTo(Integer value) {
            addCriterion("rev_mon_id <=", value, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdIn(List<Integer> values) {
            addCriterion("rev_mon_id in", values, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdNotIn(List<Integer> values) {
            addCriterion("rev_mon_id not in", values, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdBetween(Integer value1, Integer value2) {
            addCriterion("rev_mon_id between", value1, value2, "revMonId");
            return (Criteria) this;
        }

        public Criteria andRevMonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rev_mon_id not between", value1, value2, "revMonId");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
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