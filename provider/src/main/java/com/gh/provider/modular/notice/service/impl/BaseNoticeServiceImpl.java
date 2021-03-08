package com.gh.provider.modular.notice.service.impl;

import com.gh.provider.modular.notice.entity.BaseNotice;
import com.gh.provider.modular.notice.mapper.BaseNoticeMapper;
import com.gh.provider.modular.notice.service.BaseNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基础-通知表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Service
public class BaseNoticeServiceImpl extends ServiceImpl<BaseNoticeMapper, BaseNotice> implements BaseNoticeService {

}
