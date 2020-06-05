package cn.com.hik.lamp.config.security.url;

import cn.com.hik.lamp.autoconfig.LampConfig;
import cn.com.hik.lamp.common.service.system.ILampRoleMenuService;
import cn.com.hik.lamp.utils.Constants;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p> 获取访问该url所需要的用户角色权限信息 </p>
 *
 * @author : zhengqing
 * @description : 执行完之后到 `UrlAccessDecisionManager` 中认证权限
 * @date : 2019/10/15 14:36
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private ILampRoleMenuService lampRoleMenuServiceImpl;

    @Resource
    private LampConfig lampConfig;

    /***
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     * @return:
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {

        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        //判断url是否为忽略的url并跳过权限检验
        String[] postUrls = Optional.of(lampConfig.getWebConfig().getPostIgoreUrl()).orElse("").split(",");
        String[] getUrls = Optional.of(lampConfig.getWebConfig().getPostIgoreUrl()).orElse("").split(",");


        //跳过swagger请求认证
        String reqOrg = ((FilterInvocation) object).getRequest().getHeader("Request-Origion");

        //根据配置是否忽略swaager发送的请求的权限校验
        if (Boolean.TRUE.equals(lampConfig.getWebConfig().getIgoreSwaggerRequest()) && "SwaggerBootstrapUi".equals(reqOrg)) {
            return SecurityConfig.createList(Constants.ROLE_IGORE);
        }

        for (String url : postUrls) {
            if (requestUrl.equals(url)) {
                return SecurityConfig.createList(Constants.ROLE_IGORE);
            }
        }

        for (String url : getUrls) {
            if (requestUrl.equals(url)) {
                return SecurityConfig.createList(Constants.ROLE_IGORE);
            }
        }

        List<String> roles = lampRoleMenuServiceImpl.getRolesByUrl(requestUrl);


        if (CollectionUtils.isEmpty(roles)) {
            return SecurityConfig.createList(Constants.ROLE_LOGIN);
        }
        return SecurityConfig.createList(roles.toArray(new String[0]));


//        for (String ignoreUrl : myProperties.getAuth().getIgnoreUrls()) {
//            if (ignoreUrl.equals(requestUrl)){
//                return null;
//            }
//        }
//
//        if (requestUrl.contains("/login")){
        //          return null;
//        }
//
//        // 数据库中所有url
//        List<Menu> permissionList = menuMapper.selectList(null);
//        for (Menu permission : permissionList) {
//            // 获取该url所对应的权限
//            if (("/api" + permission.getUrl()).equals(requestUrl)) {
//                List<RoleMenu> permissions = roleMenuMapper.selectList(new EntityWrapper<RoleMenu>().eq("menu_id", permission.getId()));
//                List<String> roles = new LinkedList<>();
//                if (!CollectionUtils.isEmpty(permissions)){
//                    permissions.forEach( e -> {
//                        Integer roleId = e.getRoleId();
//                        Role role = roleMapper.selectById(roleId);
//                        roles.add(role.getCode());
//                    });
//                }
//                // 保存该url对应角色权限信息
//                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
//            }
//        }
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        //  return null;//SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
