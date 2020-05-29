package cn.com.hik.lamp.common.service.system.impl;

import cn.com.hik.lamp.common.entity.LampUser;
import cn.com.hik.lamp.common.mapper.LampUserMapper;
import cn.com.hik.lamp.common.service.system.ILampUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author cbhu
 * @since 2020-05-26
 */
@Service
public class LampUserServiceImpl extends ServiceImpl<LampUserMapper, LampUser> implements ILampUserService {

}
