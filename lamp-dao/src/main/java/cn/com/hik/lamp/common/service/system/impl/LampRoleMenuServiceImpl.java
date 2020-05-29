package cn.com.hik.lamp.common.service.system.impl;

import cn.com.hik.lamp.common.entity.LampRoleMenu;
import cn.com.hik.lamp.common.mapper.LampRoleMenuMapper;
import cn.com.hik.lamp.common.service.system.ILampRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色-菜单 服务实现类
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
@Service
public class LampRoleMenuServiceImpl extends ServiceImpl<LampRoleMenuMapper, LampRoleMenu> implements ILampRoleMenuService {

    @Resource
    private LampRoleMenuMapper lampRoleMenuMapper;

    public List<String> getRolesByUrl( String url){
        return  lampRoleMenuMapper.getRolesByUrl(url);

    }
}
