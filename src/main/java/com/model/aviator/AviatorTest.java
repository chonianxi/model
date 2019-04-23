package com.model.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;
import com.model.util.JsonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AviatorTest {

    /**
     * 或
     */
    public static final String CONDITIONOPERATION_OR = "or";
    /**
     * 与
     */
    public static final String CONDITIONOPERATION_AND = "and";

    public static final String TYPE_STRING = "string";
    public static final String TYPE_ENUM = "enum";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_DATA = "data";
    public static final String TYPE_AREA = "area";
    public static final String TYPE_DEPT = "dept";
    public static final String TYPE_EXPENSETYPE = "expenseType";
    public static final String TYPE_EMPLOYEE = "employee";

    public static final String DATE_FORMATE1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE2 = "yyyy-MM-dd";

    public static final SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMATE2);
    public static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMATE1);

    //等于
    public static  final String EQUAL = "equal";
    public static final String NOT_EQUAL = "not_equal";
    //包含
    public static final String CONTAIN = "contain";
    public static final String NOT_CONTAIN = "not_contain";

    //大于
    public static final String GREATER_THAN = "greaterthan";
    public static final String GREATER_THANOREQUAL = "greaterthanorequal";
    //小于
    public static final String LESS_THAN = "lessthan";
    public static final String LESS_THANOREQUAL = "lessthanorequal";
    //范围于
    public static final String BETWEEN = "between";
    public static final String NOT_BETWEEN = "not_between";

    public static final String HOU_ZHUI = "oazhui";

    public static Long dateToString (Object value){
        if (value.toString().length()>10){
            try {
                return sdf2.parse(value.toString()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                return sdf1.parse(value.toString()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String dateOpertion(String propertyName, String opertion, List<Values> values){
        String returnString = "( ";
        //等于 不等于 大于 大于等于 小于 小于等于 范围于
        if (opertion.equals(EQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "==" + dateToString(object) + ") " ;
                }
            }
        }
        if (opertion.equals(NOT_EQUAL)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (" + propertyName + "<>" + dateToString(object) + ") " ;
                }
            }
        }
        if (opertion.equals(GREATER_THAN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + ">" + dateToString(object) + ") " ;
                }
            }
        }
        if (opertion.equals(GREATER_THANOREQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + ">=" + dateToString(object) + ") " ;
                }
            }
        }
        if (opertion.equals(LESS_THAN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "<" + dateToString(object) + ") " ;
                }
            }
        }
        if (opertion.equals(LESS_THANOREQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "<=" + dateToString(object) + ") " ;
                }
            }
        }

        if (opertion.equals(BETWEEN)){
            if (values.size()==2){
                returnString += " (" + propertyName + ">=" + dateToString(values.get(0).getValue()) + " && " + propertyName + "<=" + dateToString(values.get(1).getValue()) +") " ;
            }
        }
        if (opertion.equals(NOT_BETWEEN)){
            if (values.size()==2){
                returnString += " !(" + propertyName + ">=" + dateToString(values.get(0).getValue()) + " && " + propertyName + "<=" + dateToString(values.get(1).getValue()) +") " ;
            }
        }

        returnString += " ) ";
        return returnString;
    }

    public static String enumOpertion(String propertyName,String opertion,List<Values> values){
        String returnString = "( ";
        //只有等于，不等于，包含，不包含
        if (opertion.equals(EQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "==" + object + ") " ;
                }
            }
        }

        if (opertion.equals(NOT_EQUAL)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (" + propertyName + "<>" + object + ") " ;
                }
            }
        }

        if (opertion.equals(CONTAIN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (string.contains(" + propertyName + ",'" + object + "')) " ;
                }
            }
        }

        if (opertion.equals(NOT_CONTAIN)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (!string.contains(" + propertyName + ",'" + object + "')) " ;
                }
            }
        }
        returnString += " )";
        return returnString;
    }

    public static String numberOpertion(String propertyName,String opertion,List<Values> values){
        String returnString = "( ";
        //等于 不等于 大于 大于等于 小于 小于等于 范围于
        if (opertion.equals(EQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "==" + object + ") " ;
                }
            }
        }
        if (opertion.equals(NOT_EQUAL)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (" + propertyName + "<>" + object + ") " ;
                }
            }
        }
        if (opertion.equals(GREATER_THAN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + ">" + object + ") " ;
                }
            }
        }
        if (opertion.equals(GREATER_THANOREQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + ">=" + object + ") " ;
                }
            }
        }
        if (opertion.equals(LESS_THAN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "<" + object + ") " ;
                }
            }
        }
        if (opertion.equals(LESS_THANOREQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "<=" + object + ") " ;
                }
            }
        }

        if (opertion.equals(BETWEEN)){
            if (values.size()==2){
                returnString += " (" + propertyName + ">=" + values.get(0).getValue() + " && " + propertyName + "<=" + values.get(1).getValue() +") " ;
            }
        }
        if (opertion.equals(NOT_BETWEEN)){
            if (values.size()==2){
                returnString += " !(" + propertyName + ">=" + values.get(0).getValue() + " && " + propertyName + "<=" + values.get(1).getValue() +") " ;
            }
        }

        returnString += ")";
        return returnString;
    }


    public static String stringOpertion(String propertyName,String opertion,List<Values> values){
        String returnString = "( ";
        //String propertyNameZhui = propertyName + HOU_ZHUI;
        //只有等于，不等于，包含，不包含
        if (opertion.equals(EQUAL)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (" + propertyName + "=='" + object + "') " ;
                }
            }
        }

        //只有等于，不等于，包含，不包含
        if (opertion.equals(NOT_EQUAL)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (" + propertyName + "!='" + object + "') " ;
                }
            }
        }

        //只有等于，不等于，包含，不包含
        if (opertion.equals(CONTAIN)){
            returnString += " 1<>1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " || (string.contains(" + propertyName + ",'" + object + "')) " ;
                }
            }
        }

        //只有等于，不等于，包含，不包含
        if (opertion.equals(NOT_CONTAIN)){
            returnString += " 1==1 ";
            for (Values value:values){
                for (Object object : value.getValue()){
                    returnString += " && (!string.contains(" + propertyName + ",'" + object + "')) " ;
                }

            }
        }

        returnString += ")";
        return returnString;
    }

    public static Object mapToObject(Object object, Map map){
        try {
            BeanUtils.populate(object, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args){

        System.out.println("-----begin-----"+System.currentTimeMillis());
        AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        instance.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);

        AviatorEvaluator.execute("print('hello world'); 1+2+3 ; 100-1");
        //System.out.println(AviatorEvaluator.execute("print('hello world'); 1+2+3 ; 100-1"));

        String yourName = "Michael";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("yourName", yourName);
        String resultString = (String) AviatorEvaluator.execute(" 'hello ' + yourName ", env);
        //System.out.println(resultString);  // hello Michael


        //注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        AviatorEvaluator.execute("add(1, 2)");
        //System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0

        env = new HashMap<String, Object>();
        env.put("a", 332);
        env.put("b", 302);
        AviatorEvaluator.execute("add(a, b)",env);
        System.out.println(AviatorEvaluator.execute("a>b",env));
        //System.out.println(AviatorEvaluator.execute("add(a, b)",env));           // 3.0
        AviatorEvaluator.execute("add(add(1, 2), 100)");
        //System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0

        Map<String, Object> env1 = new HashMap<String, Object>();
        env1.put("real",true);
        env1.put("real1",false);

        env1 = new HashMap<String, Object>();
        env1.put("real","aa");
        env1.put("real1","aa");
        AviatorEvaluator.execute("real == real1",env1);
        //System.out.println(AviatorEvaluator.execute("real == real1",env1));
        System.out.println("-----end-----"+System.currentTimeMillis());






















        String json = "[{\"propertyName\":\"orgRoute\",\"frontBrackets\":\"(\",\"postBrackets\":\")\",\"conditionOperation\":\"\",\"operation\":\"equal\",\"type\":\"string\",\"values\":[{\"label\":\"客服中心\",\"value\":[\"6456\"],\"subDeptFlag\":true}]}," +
                "{\"propertyName\":\"orgRoute\",\"frontBrackets\":\"(\",\"postBrackets\":\")\",\"conditionOperation\":\"\",\"operation\":\"equal\",\"type\":\"string\",\"values\":[{\"label\":\"客服中心\",\"value\":[\"6456\"],\"subDeptFlag\":true}]}]";

        //List<Map> ruleModelZcs = JsonUtils.serializable(json,List.class);
        RuleModelZc[] ruleModelZcs = (RuleModelZc[]) JsonUtils.jsonToObj(json,RuleModelZc[].class);
        StringBuffer sb = new StringBuffer("");
        for (int i=0;i<ruleModelZcs.length;i++){
            RuleModelZc ruleModel  = new RuleModelZc();
            ruleModel = ruleModelZcs[i];
            //mapToObject(ruleModel,ruleModelZcs.get(i));
            /*try {
                BeanUtils.populate(ruleModel, ruleModelZcs.get(i));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }*/
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ruleModel.getFrontBrackets())) {
                sb.append(ruleModel.getFrontBrackets());
            }
            if (ruleModel.getType().equals(TYPE_STRING)){
                sb.append(stringOpertion(ruleModel.getPropertyName(),ruleModel.getOperation(),ruleModel.getValues()));
            }else if (ruleModel.getType().equals(TYPE_NUMBER)){
                sb.append(numberOpertion(ruleModel.getPropertyName(),ruleModel.getOperation(),ruleModel.getValues()));
            }else if (ruleModel.getType().equals(TYPE_ENUM)){
                sb.append(enumOpertion(ruleModel.getPropertyName(),ruleModel.getOperation(),ruleModel.getValues()));
            }else if (ruleModel.getType().equals(TYPE_DATA)){
                sb.append(dateOpertion(ruleModel.getPropertyName(),ruleModel.getOperation(),ruleModel.getValues()));
            }else {
                System.out.println("类型异常");
            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(ruleModel.getPostBrackets())) {
                sb.append(ruleModel.getPostBrackets());
            }

            if (StringUtils.isNotEmpty(ruleModel.getConditionOperation())) {
                if (ruleModel.getConditionOperation().equals(CONDITIONOPERATION_OR)) {
                    sb.append(" || ");
                }
                if (ruleModel.getConditionOperation().equals(CONDITIONOPERATION_AND)) {
                    sb.append(" && ");
                }
            }
        }

    }

}
