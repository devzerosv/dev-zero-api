package dev.devzero.api.util.safe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang3.time.DateUtils;
import org.mvel2.MVEL;
import org.springframework.util.NumberUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.TemporalExpression;

import dev.devzero.api.common.BaseService;
import dev.devzero.api.util.enums.OperationType;
import dev.devzero.api.util.enums.OperatorType;
import lombok.extern.java.Log;

@Log
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AbsSpec {

    protected static Predicate toPredicate(String expression, Map<String, Object> vars) {
        Predicate p = null;
        try {
            p = (Predicate) MVEL.eval(expression, vars);
        } catch (Exception ex) {
            // nothing
        }

        return p;
    }

    protected static Predicate[] toPredicate(Collection<Predicate> collection) {
        if (collection.isEmpty()) {
            return null;
        }
        return (Predicate[]) collection.toArray(new Predicate[collection.size()]);
    }

    public static BooleanBuilder builder(Map<String, Object> vars, Set<ValueHolder> conditions) {
        BooleanBuilder bb = new BooleanBuilder();
        OperatorType lastOperator = OperatorType.AND;
        for (final ValueHolder item : conditions) {
            String condition = toCondition(item, vars);
            if (condition != null) {
                Predicate pred = toPredicate(condition, vars);
                if (lastOperator != null) {
                    if (OperatorType.AND.equals(lastOperator)) {
                        bb.and(pred);
                    } else if (OperatorType.OR.equals(lastOperator)) {
                        bb.or(pred);
                    }
                } else {
                    bb.and(pred);
                }

                if (item.getOperatorType() != null) {
                    lastOperator = item.getOperatorType();
                } else {
                    lastOperator = OperatorType.AND;
                }
            }
        }
        return bb;
    }


    public static BooleanBuilder builder(Map<String, Object> vars, boolean any, boolean custom, Set<ValueHolder> conditions) {
        BooleanBuilder bb = new BooleanBuilder();
        Collection<Predicate> predicates = new ArrayList<>();
        String condition;
        for (final ValueHolder item : conditions) {
            condition = toCondition(item, vars);
            if (condition != null) {
                Predicate pred = toPredicate(condition, vars);
                predicates.add(pred);
                if (custom) {
                    if (item.getOperatorType() != null) {
                        if (item.getOperatorType().equals(OperatorType.AND)) {
                            bb.and(pred);
                        } else if (item.getOperatorType().equals(OperatorType.OR)) {
                            bb.or(pred);
                        }
                    } else {
                        bb.and(pred);
                    }
                }
            }
        }

        if (!predicates.isEmpty()) {
            if (!custom) {
                if (any) {
                    bb.andAnyOf(toPredicate(predicates));
                } else {
                    BooleanBuilder or = new BooleanBuilder();
                    or.orAllOf(toPredicate(predicates));
                    bb.and(or);
                }
            }
        }

        return bb;
    }

    public static String toCondition(final ValueHolder valueHolder, Map vars) {
        String condition = null;
        String attr;
        boolean isJoin = valueHolder.isJoin();

        if (!isJoin) {
            attr = (valueHolder.getField() != null ? !valueHolder.getField().trim().equals("") ? valueHolder.getField() + "." : "" : BaseService.ENTITY_VAR + ".") + valueHolder.getName();
        } else {
            attr = valueHolder.getName();
        }
        String oper = valueHolder.getOperator();

        try {
          
            if (valueHolder.isMultiValor()) {
                // TODO: code difference?
            }
            if (valueHolder.isVigenteVigente()) {
                return valueHolder.getExpressionVigente();
            }
            if (valueHolder.isVigenteVigenteNested()) {
                return valueHolder.getExpressionVigenteNested(attr);
            }
            if (valueHolder.isVigente()) {
                vars.put("ovalue", valueHolder.getFecha1());
                return valueHolder.getFilterVigencia();
            }
            if (valueHolder.isNovigente()) {
                vars.put("ovalue", valueHolder.getFecha1());
                return valueHolder.getFilterNoVigente();
            }
            Object attrib = MVEL.eval(attr, vars);
            if (attrib instanceof TemporalExpression) {
                Class attrtype = ((TemporalExpression) attrib).getType();
                if (attrtype.equals(java.util.Date.class)) {
                    if (valueHolder.getFecha1() != null) {
                        vars.put("ovalue", valueHolder.getFecha1());
                        if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                            vars.put("ovalue2", valueHolder.getFecha2());
                            condition = attr + "." + oper + "(ovalue,ovalue2)";
                        } else {
                            condition = attr + "." + oper + "(ovalue)";
                        }
                    } else if (valueHolder.getValue() != null) {
                        boolean isvalid = false;
                        try {
                            Date odate = DateUtils.parseDate(valueHolder.getValue().toString(), new String[]{"dd/MM/yyyy"});
                            vars.put("ovalue", odate);
                            isvalid = true;
                        } catch (ParseException ex) {
                            // nothing
                        }
                        if (isvalid) {
                            vars.put("resultingClass", Date.class);
                            condition = "com.mysema.query.types.template.DateTemplate.create(resultingClass, \"trunc({0})\", " + attr + ").eq(ovalue)";
                        } else {
                            vars.put("ovalue", valueHolder.getValue().toString());
                            vars.put("pattern", "dd/MM/yyyy");
                            condition = "com.mysema.query.types.template.StringTemplate.create(\"to_char({0},'{1s}')\", "
                                    + attr
                                    + ", com.mysema.query.types.ConstantImpl.create(pattern)).like("
                                    + "com.mysema.query.support.Expressions.stringTemplate(\"'%'\").concat(com.mysema.query.support.Expressions.stringTemplate(\"{0}\" , ovalue )).concat(com.mysema.query.support.Expressions.stringTemplate(\"'%'\"))"
                                    + ")";
                        }
                    } else if (oper.trim().equals(OperationType.NULL.getCode()) || oper.trim().equals(OperationType.NOT_NULL.getCode())) {
                        condition = attr + "." + oper + "()";
                    }

                } else if (attrtype.equals(java.util.Calendar.class)) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(valueHolder.getFecha1());
                    vars.put("ovalue", cal);
                    if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(valueHolder.getFecha2());
                        vars.put("ovalue2", cal2);
                        condition = attr + "." + oper + "(ovalue,ovalue2)";
                    } else {
                        condition = attr + "." + oper + "(ovalue)";
                    }
                }
            } else if (oper.trim().equals(OperationType.NULL.getCode()) || oper.trim().equals(OperationType.NOT_NULL.getCode())) {
                condition = attr + "." + oper + "()";
            } else if (oper.trim().equals(OperationType.IN.getCode())) {
                Object[] arrayValue = null;
                if (valueHolder.getValue() instanceof Object[]) {
                    arrayValue = (Object[]) valueHolder.getValue();
                } else if (valueHolder.getValue() != null && Collection.class.isAssignableFrom(valueHolder.getValue().getClass())) {
                    Collection coll = (Collection) valueHolder.getValue();
                    if (attrib instanceof NumberPath) {
                        arrayValue = new Number[coll.size()];
                    } else {
                        arrayValue = new Object[coll.size()];
                    }
                    arrayValue = coll.toArray(arrayValue);
                } else if (valueHolder.getValue() instanceof String && ((String) valueHolder.getValue()).contains(",")) {
                    if (attrib instanceof NumberPath) {
                        String[] sarrayValue = ((String) valueHolder.getValue()).split("\\,");
                        if (sarrayValue != null && sarrayValue.length > 0) {
                            arrayValue = new Number[sarrayValue.length];
                            for (int i = 0; i < sarrayValue.length; i++) {
                                arrayValue[i] = NumberUtils.parseNumber(sarrayValue[i], Number.class);
                            }
                        }
                    } else {
                        arrayValue = ((String) valueHolder.getValue()).split("\\,");
                    }
                } else if (valueHolder.getValue() instanceof String) {
                    if (attrib instanceof NumberPath) {
                        String svalue = valueHolder.getValue().toString();
                        if (svalue != null && !svalue.trim().equals("")) {
                            arrayValue = new Number[]{NumberUtils.parseNumber(valueHolder.getValue().toString(), Number.class)};
                        } else {
                            arrayValue = new Number[]{-666};
                        }
                    } else {
                        arrayValue = new String[]{valueHolder.getValue().toString()};
                    }
                } else {
                    arrayValue = new Object[]{valueHolder.getValue()};
                }
                vars.put("arrayValue", arrayValue);
                condition = attr + "." + oper + "(arrayValue)";
            } else if (oper.trim().equals(OperationType.NOT_IN.getCode())) {
                if (valueHolder.getValue() instanceof Collection) {
                    vars.put("arrayValue", (valueHolder.getValue()));
                    condition = attr + "." + oper + "(arrayValue)";
                }
            } else if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                condition = attr + "." + oper + "(" + valueHolder.getValue() + "," + valueHolder.getValue2() + ")";
            } else if (oper.trim().equals(ValueHolder._ILIKE)) {
                if (attrib instanceof StringPath) {
                    if (!valueHolder.isExcluirRegexp()) {
                        condition = "com.mysema.query.types.template.StringTemplate.create(\""
                                + "regexp_replace( "
                                + "    regexp_replace( "
                                + "        regexp_replace( "
                                + "          regexp_replace( "
                                + "            regexp_replace({0},'(\u00e1|\u00c1)','a'),  "
                                + "            '(\u00E9|\u00C9)','e'), "
                                + "          '(\u00ED|\u00CD)','i'), "
                                + "        '(\u00F3|\u00D3)','o'), "
                                + "      '\u00FA|\u00D9','u')"
                                + "\", "
                                + attr
                                + ")" + ".upper()." + oper + "(\"%" + valueHolder.getValue().toString()
                                        .replaceAll("\u00e1|\u00c1", "a")
                                        .replaceAll("\u00E9|\u00C9", "e")
                                        .replaceAll("\u00ED|\u00CD", "i")
                                        .replaceAll("\u00F3|\u00D3", "o")
                                        .replaceAll("\u00FA|\u00D9", "u")
                                        .toUpperCase() + "%\")";
                    } else {
                        condition = attr + "." + oper + "(\"%" + valueHolder.getValue().toString() + "%\")";
                    }
                } else if (attrib instanceof NumberPath) {
                    condition = attr + ".eq(" + valueHolder.getValue().toString() + ")";
                } else {
                    condition = attr + "." + oper + "(\"" + valueHolder.getValue().toString().toUpperCase() + "\")";
                }
            } else if (valueHolder.getValue() instanceof Object[]) {
                Object[] arrayValue = (Object[]) valueHolder.getValue();
                vars.put("arrayValue", arrayValue);
                condition = attr + ".in(arrayValue)";
            } else if (attrib instanceof StringPath) {
                condition = attr + "." + oper + "(\"" + valueHolder.getValue() + "\")";
            } else {
                condition = attr + "." + oper + "(" + valueHolder.getValue() + ")";
            }
        } catch (Exception ex) {
            log.log(Level.OFF, null, ex);
        }
        return condition;
    }

}
