package com.base.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
    public static ResponseEntity<Object> success(boolean code, Object data){
        Map<String, Object> map = new HashMap<>(4);
        map.put("success", code);
        // 如果data是page对象，需要将其中的records属性提取出来
        // 同时把分页相关的total、size、current等属性也提取出来放在pagination中
        if (data instanceof Page<?> page) {
            map.put("data", page.getRecords());
            Map<String, Object> pagination = new HashMap<>(4);
            pagination.put("total", page.getTotal());
            pagination.put("pageSize", page.getSize());
            pagination.put("current", page.getCurrent());
            pagination.put("pages", page.getPages());
            map.put("pagination", pagination);
        } else {
            map.put("data", data);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> fail(boolean code, String msg){
        Map<String, Object> map = new HashMap<>(4);
        map.put("success", code);
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
