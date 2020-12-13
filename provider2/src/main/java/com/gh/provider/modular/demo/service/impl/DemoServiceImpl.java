package com.gh.provider.modular.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.provider.modular.demo.entity.Demo;
import com.gh.provider.modular.demo.mapper.DemoMapper;
import com.gh.provider.modular.demo.service.DemoService;
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
