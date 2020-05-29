package cn.com.hik.lamp.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色-菜单
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LampRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private Long menuId;


}
