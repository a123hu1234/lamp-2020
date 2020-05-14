package cn.com.hik.lamp.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/14 11:15
 */
@Data
@TableName("LAMP_USER")
public class UserRole {
    private String userId;
    private String roleId;
}
