package dev.devzero.api.util.enums;

import java.util.EnumSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({ "rawtypes", "unchecked" })
public enum OperationType {

    GT("gt", "Mayor que"),
    GE("goe", "Mayor o igual que"),
    LT("lt", "Menor que"),
    LE("loe", "Menor o igual que"),
    BETWEEN("between", "Entre"),
    IN("in", "Dentro"),
    NOT_IN("notIn", "No dentro"),
    EQ("eq", "Igual"),
    CT("contains", "Contiene"),
    SW("startsWith", "Inicia Con"),
    EW("endsWith", "Finaliza Con"),
    SI("isTrue", "SI"),
    NO("isFalse", "NO"),
    NE("ne", "Diferente"),
    NULL("isNull", "Nulo"),
    NOT_NULL("isNotNull", "NO Nulo");

    @Getter
    @NonNull
    String code;

    @Getter
    @NonNull
    String description;

    @Getter
    private static Set typesForString;

    @Getter
    private static Set typesForNumber;

    @Getter
    private static Set typesForBoolean;

    static {
        typesForString = EnumSet.range(EQ, EW);
        typesForString.addAll(EnumSet.range(NE, NOT_NULL));
        typesForNumber = EnumSet.range(GT, EQ);
        typesForNumber.addAll(EnumSet.range(NE, NOT_NULL));
        typesForBoolean = EnumSet.range(SI, NO);
        typesForBoolean.addAll(EnumSet.range(NE, NOT_NULL));
    }

    public static OperationType getOperationType(final String code) {
        OperationType ret = null;
        for (OperationType ienum : values()) {
            if (ienum.getCode().equals(code)) {
                ret = ienum;
                break;
            }
        }
        return ret;
    }

    public static Set getOptionsForType(String type) {
        Set typesToReturn;
        switch (type.trim().toLowerCase()) {
            case "integer":
            case "double":
            case "float":
            case "long":
            case "short":
            case "calendar":
            case "date":
                typesToReturn = typesForNumber;
                break;

            case "string":
                typesToReturn = typesForString;
                break;

            case "boolean":
                typesToReturn = typesForBoolean;
                break;

            default:
                typesToReturn = typesForNumber;
                break;
        }
        typesToReturn.addAll(EnumSet.range(NE, NOT_NULL));
        return typesToReturn;
    }
}
