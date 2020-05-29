package cn.com.hik.lamp.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LampMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上一级菜单
     */
    private Long parentId;

    /**
     * 菜单类型，0子系统  1功能菜单  2按钮
            
     */
    private String menuType;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * url
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序代码
     */
    private Long sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志 y/n
     */
    private String del;


}
