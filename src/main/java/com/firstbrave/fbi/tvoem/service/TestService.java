package com.firstbrave.fbi.tvoem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firstbrave.fbi.tvoem.mapper.TestMapper;
import com.firstbrave.fbi.tvoem.po.TestPo;
import org.springframework.stereotype.Service;

@Service
public class TestService extends ServiceImpl<TestMapper, TestPo> {
}
