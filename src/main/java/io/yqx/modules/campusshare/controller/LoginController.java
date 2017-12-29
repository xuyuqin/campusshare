package io.yqx.modules.campusshare.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.yqx.common.utils.R;
import io.yqx.common.utils.ShiroUtils;
import io.yqx.modules.campusshare.entity.CRegisterEntity;
import io.yqx.modules.campusshare.service.CRegisterService;
import io.yqx.modules.sys.controller.AbstractController;
import io.yqx.modules.sys.entity.SysUserEntity;
import io.yqx.modules.sys.service.SysUserService;
import io.yqx.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
@RequestMapping("campusshare")
public class LoginController extends AbstractController {
	@Autowired
	private Producer producer;
	@Autowired
	private CRegisterService cRegisterService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	/**
	 * 验证码
	 */
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(String mobile, String password)throws IOException {
		//本项目已实现，前后端完全分离，但页面还是跟项目放在一起了，所以还是会依赖session
		//如果想把页面单独放到nginx里，实现前后端完全分离，则需要把验证码注释掉(因为不再依赖session了)
//		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//		if(!captcha.equalsIgnoreCase(kaptcha)){
//			return R.error("验证码不正确");
//		}

		//用户信息
		CRegisterEntity register = cRegisterService.queryByMobile(mobile);

		//账号不存在、密码错误
		if(register == null || !register.getPassword().equals(new Sha256Hash(password, register.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(register.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(register.getId(),1);
		return r;
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(String mobile, String password,String vcode)throws IOException {

		CRegisterEntity register = cRegisterService.queryByMobile(mobile);
		if (register!=null){
			return R.error("该用户已经存在");
		}
		register=new CRegisterEntity();
                  register.setMobile(mobile);
		register.setPassword(password);
		cRegisterService.save(register);
		return R.ok("注册成功");
	}



	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
