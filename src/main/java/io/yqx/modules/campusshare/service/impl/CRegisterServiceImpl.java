package io.yqx.modules.campusshare.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.yqx.modules.campusshare.dao.CRegisterDao;
import io.yqx.modules.campusshare.entity.CRegisterEntity;
import io.yqx.modules.campusshare.service.CRegisterService;



@Service("cRegisterService")
public class CRegisterServiceImpl implements CRegisterService {
	@Autowired
	private CRegisterDao cRegisterDao;
	
	@Override
	public CRegisterEntity queryObject(Long id){
		return cRegisterDao.queryObject(id);
	}
	
	@Override
	public List<CRegisterEntity> queryList(Map<String, Object> map){
		return cRegisterDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cRegisterDao.queryTotal(map);
	}
	
	@Override
	public void save(CRegisterEntity cRegister){
		cRegister.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		cRegister.setPassword(new Sha256Hash(cRegister.getPassword(), salt).toHex());
		cRegister.setSalt(salt);
		cRegister.setUsername(cRegister.getMobile());
		cRegisterDao.save(cRegister);
	}
	
	@Override
	public void update(CRegisterEntity cRegister){
		cRegisterDao.update(cRegister);
	}
	
	@Override
	public void delete(Long id){
		cRegisterDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		cRegisterDao.deleteBatch(ids);
	}

	@Override
	public CRegisterEntity queryByMobile(String mobile) {
		return cRegisterDao.queryByMobile(mobile);
	}

}
