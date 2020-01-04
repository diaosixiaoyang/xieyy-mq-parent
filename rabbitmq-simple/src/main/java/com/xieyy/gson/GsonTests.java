package com.xieyy.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import sun.misc.ClassLoaderUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieyyt
 * @date 2020/1/3 15:14
 */
public class GsonTests {

    @Test
    public void testGson() {

        Map map = new HashMap<String, String>();
        map.put("aaa", "bbb");
        map.put("ccc", "ddd");
        Gson gson = new Gson();
        String s = gson.toJson(map);
        System.out.println(s);
        Map map1 = gson.fromJson(s, Map.class);
        Object o = gson.fromJson(s, new TypeToken<Map<String, String>>() {
        }.getType());
        System.out.println(map1);

    }

    @Test
    public void testJSON() {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "bbb");
        map.put("ccc", "ddd");

        String mapStr = JSON.toJSONString(map);
        System.out.println(mapStr);
        Map<String, String> stringStringMap = JSON.parseObject(mapStr, new TypeReference<Map<String, String>>() {
        }.getType());
        System.out.println(stringStringMap);
    }

    @Test
    public void testClass() throws MalformedURLException {
        URL url = new URL("");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
    }

    @Test
    public void testMap() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        System.out.println(list.remove(0));
    }

}
