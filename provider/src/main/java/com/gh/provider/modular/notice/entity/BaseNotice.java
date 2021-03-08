package com.gh.provider.modular.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础-通知表
 * </p>
 *
 * @author gaohan
 * @since 2021-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseNotice implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通知编号
     */
    private String noticeId;

    /**
     * 通知发送人id
     */
    private String noticeFromUserId;

    /**
     * 通知接收人id
     */
    private String noticeToUserId;

    /**
     * 通知文本内容
     */
    private String noticeContent;

    /**
     * 通知状态：0为等待发送，1为已发送
     */
    private Integer noticeState;

    /**
     * 通知类型：0为普通信息，1为系统信息，2为异常信息
     */
    private Integer noticeType;


}
