package com.hsuhau.repository;

import com.hsuhau.annotation.StringRepository;

import java.util.Arrays;
import java.util.List;

@StringRepository("chineseNameRepository")
public class NameRepository {
    /**
     * 查找所有名称
     *
     * @return non-null List
     */
    public List<String> findAll() {
        return Arrays.asList("张三", "李四", "王五");
    }
}
