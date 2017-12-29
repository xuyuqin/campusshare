package io.yqx.modules.campusshare.dao;

import io.yqx.modules.campusshare.entity.CRegisterEntity;
import io.yqx.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 注册用户
 * 
 * @author yq.x
 * @email yq.xu@unicall.net.cn
 * @date 2017-12-29 13:46:12
 */
@Mapper
public interface CRegisterDao extends BaseDao<CRegisterEntity> {
	CRegisterEntity queryByMobile(String mobile);
}
