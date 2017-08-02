package org.after90.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by zhaogj on 09/07/2017.
 */
@Slf4j
public class JSONUtils {
    //去除重复项
    public static JSONObject cleanJSONObject(JSONObject json) {
        //log.info("begin cleanJSONObject:{}", json.toJSONString());
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            //log.info("key:{}, value:{}", entry.getKey(), entry.getValue());
            Object objValue = entry.getValue();
            if (objValue == null) {
                //这种情况跳过，不处理
                continue;
            }
            String strClass = objValue.getClass().getSimpleName();
//            log.info("class:{}", strClass);
            if ("JSONArray".equals(strClass)) {
                objValue = cleanJSONArray((JSONArray) objValue);
            } else if ("JSONObject".equals(strClass)) {
                objValue = cleanJSONObject((JSONObject) objValue);
            }
        }
//        log.info("end cleanJSONObject:{}", json.toJSONString());
        return json;
    }

    //去除重复项
    public static JSONArray cleanJSONArray(JSONArray jsonArray) {
//        log.info("begin cleanJSONArray:{}", jsonArray.toJSONString());
        HashSet hashSet = new HashSet(jsonArray);
        jsonArray.clear();
        jsonArray.addAll(hashSet);
        for (Object obj : jsonArray) {
            if (obj == null) {
                //这种情况跳过，不处理
                continue;
            }
            String strClass = obj.getClass().getSimpleName();
            if ("JSONArray".equals(strClass)) {
                obj = cleanJSONArray((JSONArray) obj);
            } else if ("JSONObject".equals(strClass)) {
                obj = cleanJSONObject((JSONObject) obj);
            }
        }
//        log.info("end cleanJSONArray:{}", jsonArray.toJSONString());
        return jsonArray;
    }

    //将A的数据更新到B中去,A为更新数据，B为ES中原始数据
    public static JSONObject mergeJSONObject(JSONObject jsonA, JSONObject jsonB) {
        //log.info("mergeJSONObject A:{},B:{}", jsonA, jsonB);
        //log.info("begin merge");
        for (Map.Entry<String, Object> entry : jsonA.entrySet()) {
            String strKey = entry.getKey();
            Object objValue = entry.getValue();
            if (objValue == null) {
                //这种情况跳过，不处理
                continue;
            }
            String strClass = objValue.getClass().getSimpleName();
            if ("String".equals(strClass)) {
                jsonB.put(strKey, objValue);
            } else if ("JSONObject".equals(strClass)) {
                if (jsonB.get(strKey) == null) {
                    jsonB.put(strKey, objValue);
                } else {
                    mergeJSONObject((JSONObject) jsonA.get(strKey), (JSONObject) jsonB.get(strKey));
                }
            } else if ("JSONArray".equals(strClass)) {
                if (jsonB.get(strKey) == null) {
                    jsonB.put(strKey, objValue);
                } else {
                    ((JSONArray) jsonB.get(strKey)).addAll((JSONArray) jsonA.get(strKey));
                }
            }
        }
        //log.info("end merge");
        return jsonB;
    }
}
