package cn.com.hik.lamp.common.service.system;

import cn.com.hik.lamp.common.entity.LampRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-菜单 服务类
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
public interface ILampRoleMenuService extends IService<LampRoleMenu> {
    /**
     *
     * @param url url
     * @return 拥有url的相关角色权限
     */
    List<String> getRolesByUrl(String url);
}
