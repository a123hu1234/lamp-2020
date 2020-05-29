package cn.com.hik.lamp.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LampUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户类型
            1 普通账号
            8 第三方用户 
            9 超级管理员
            
     */
    private String userType;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属部门
     */
    private Long departmentId;

    /**
     * 所属角色
     */
    private Long roleId;

    /**
     * 账户过期时间
     */
    private LocalDate invalidDate;

    /**
     * 消息接收方式 1短信  2app推送 3停用
     */
    private String receivingMode;

    /**
     * 消息接收类型，多个逗号隔开 1失去连接  2回路异常 3灯具异常 4线路故障  5人为操作
     */
    private String receivingType;

    /**
     * 电脑端设备标识
     */
    private String pcSn;

    /**
     * 移动端设备标识
     */
    private String appSn;

    /**
     * 删除标志 y/n
     */
    private String del;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
