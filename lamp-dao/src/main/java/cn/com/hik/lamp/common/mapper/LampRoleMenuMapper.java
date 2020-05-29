package cn.com.hik.lamp.common.mapper;

import cn.com.hik.lamp.common.entity.LampRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-菜单 Mapper 接口
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
public interface LampRoleMenuMapper extends BaseMapper<LampRoleMenu> {

    List<String> getRolesByUrl(@Param("url") String url);

}
