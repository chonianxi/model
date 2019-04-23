package com.model.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject  arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        //System.out.println("----AddFunction----");
        return new AviatorBigInt(left.longValue() + right.longValue());
    }
    public String getName() {
        return "add";
    }
}
