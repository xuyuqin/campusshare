package io.yqx.modules.sys.oauth2;

import io.yqx.common.utils.ShiroUtils;
import io.yqx.modules.campusshare.entity.CRegisterEntity;
import io.yqx.modules.sys.entity.SysUserEntity;
import io.yqx.modules.sys.entity.SysUserTokenEntity;
import io.yqx.modules.sys.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 14:00
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject=ShiroUtils.getSubject();
        if("io.yqx.modules.sys.entity.SysUserEntity".equals(subject.getPrincipal().getClass().getName())) {
            System.out.println("管理员授权中......");
            SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
            Long userId = user.getUserId();

            //用户权限列表
            Set<String> permsSet = shiroService.getUserPermissions(userId);

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setStringPermissions(permsSet);
            return info;
        }else{
            System.out.println("用户授权中......");
            CRegisterEntity register = (CRegisterEntity) principals.getPrimaryPrincipal();
            Long registerId=register.getId();

            //用户权限列表
            Set<String> permsSet = shiroService.getUserPermissions(registerId);

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setStringPermissions(permsSet);
            return info;
        }
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        if (tokenEntity.getIdentity()==0) {
            SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
            //账号锁定
            if (user.getStatus() == 0) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
            return info;
        }else{
            CRegisterEntity register=shiroService.queryRegister(tokenEntity.getUserId());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(register, accessToken, getName());
            return info;
        }
    }
}
