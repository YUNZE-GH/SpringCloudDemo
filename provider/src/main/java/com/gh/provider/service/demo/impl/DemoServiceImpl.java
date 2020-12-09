package com.gh.provider.service.demo.impl;

import com.gh.provider.entity.demo.Demo;
import com.gh.provider.mapper.demo.DemoMapper;
import com.gh.provider.service.demo.DemoService;
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
