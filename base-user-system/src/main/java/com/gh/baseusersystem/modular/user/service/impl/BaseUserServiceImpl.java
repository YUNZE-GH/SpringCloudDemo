package com.gh.baseusersystem.modular.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gh.baseusersystem.modular.user.entity.BaseUser;
import com.gh.baseusersystem.modular.user.mapper.BaseUserMapper;
import com.gh.baseusersystem.modular.user.service.BaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.baseusersystem.utils.JwtUtil;
import com.gh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    @Autowired(required = false)
    private RedisUtil redisUtil;

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    public List<BaseUser> getListAll(BaseUser bo, int page, int rows) {
        return baseMapper.selectList(null);
    }

    @Override
    public int getCountAll(BaseUser bo) {
        return baseMapper.selectCount(null);
    }

    @Override
    public String loginVerify(String account, String password) throws Exception {
        String pwd = baseMapper.loginVerify(account);
        if (!StringUtils.isEmpty(pwd) && this.getMD5String(password).equals(pwd)) {
            BaseUser bo = baseMapper.selectOneByUserAccount(account);
            // 通过登录校验，将信息存入redis，并返回token
            String token = JwtUtil.sign(bo.getUserAccount(), bo.getUserId());
            redisUtil.insertOrUpdate(token, JSONObject.toJSONString(bo));
            return token;
        }
        throw new Exception("账号或密码错误！");
    }


    public String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
