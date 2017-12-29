package io.yqx.modules.campusshare.service;

import io.yqx.modules.campusshare.entity.CRegisterEntity;

import java.util.List;
import java.util.Map;

/**
 * 注册用户
 * 
 * @author yq.x
 * @email yq.xu@unicall.net.cn
 * @date 2017-12-29 13:46:12
 */
public interface CRegisterService {
	
	CRegisterEntity queryObject(Long id);
	
	List<CRegisterEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CRegisterEntity cRegister);
	
	void update(CRegisterEntity cRegister);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	CRegisterEntity queryByMobile(String mobile);
}
