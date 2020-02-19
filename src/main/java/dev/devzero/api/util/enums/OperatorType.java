package dev.devzero.api.util.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum OperatorType {
   AND("AND", "Y"),
   OR("OR", "O"),
   MULTIPLE("MULTIPLE", "M");

   @Getter
   @NonNull
   String code;
   
   @Getter
   @NonNull
   String description;
   
   public static OperatorType getOperatorType(final String code) {
     OperatorType ret = null;
     
     for (OperatorType ienum : values()) {
         if (ienum.getCode().equals(code)) {
             ret= ienum;
             break;
         }
     }
     return ret;
   }


}