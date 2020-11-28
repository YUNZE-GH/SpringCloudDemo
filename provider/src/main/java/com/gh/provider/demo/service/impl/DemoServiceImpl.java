package com.gh.provider.demo.service.impl;

import com.gh.provider.demo.entity.Demo;
import com.gh.provider.demo.mapper.DemoMapper;
import com.gh.provider.demo.service.DemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2020-11-26
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
