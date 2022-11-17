package org.margin.bank.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author sunkailun
 * @DateTime 2018/5/9  下午1:53
 * @email 376253703@qq.com
 * @phone 13777579028
 * @explain
 */
public class RsaUtils {

    /**
     * 生成签名
     *
     * @param map: 参数
     * @return java.lang.String
     * @author sunkailun
     * @DateTime 2018/5/9  下午2:01
     * @email 376253703@qq.com
     * @phone 13777579028
     */
    public static String generateSign(TreeMap<String, Object> map, String privateKey) {
        //签名规则
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, null);
        //参数值
        StringBuffer param = new StringBuffer();
        //循环拼接参数
        mapToString(map,param);
        //将String转换为byte
        byte[] data = Convert.toStr(param).getBytes();
        //签名
        byte[] signed = sign.sign(data);
        return Base64.encode(signed);
    }

    /**
     * map转字符串
     *
     * @param map   集合
     * @param param 追加参数
     * @return
     */
    public static void mapToString(TreeMap<String, Object> map, StringBuffer param) {
        //循环集合
        for (String key : map.keySet()) {
            //值
            Object obj = map.get(key);
            //判断不同类型，执行不同参数转换
            if (obj instanceof List) {
                // 转list
                List<TreeMap<String,Object>> list = Convert.convert(List.class,obj);
                // 递归遍历
                for (Object m : list) {
                    mapToString(Convert.convert(TreeMap.class,m), param);
                }
            } else if (obj instanceof Map) {
                // 递归遍历
                mapToString(Convert.convert(TreeMap.class,obj), param);
            } else {
                //判断是否为空
                if (StrUtil.isNotBlank(Convert.toStr(obj))) {
                    //附值
                    param.append(Convert.toStr(obj));
                }
            }
        }
    }
}
