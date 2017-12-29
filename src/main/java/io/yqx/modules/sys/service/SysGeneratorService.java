package io.yqx.modules.sys.service;

import io.yqx.modules.sys.dao.SysGeneratorDao;
import io.yqx.common.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */

public interface SysGeneratorService {

	public List<Map<String, Object>> queryList(Map<String, Object> map);

	public int queryTotal(Map<String, Object> map) ;

	public Map<String, String> queryTable(String tableName);

	public List<Map<String, String>> queryColumns(String tableName);

	public byte[] generatorCode(String[] tableNames) ;
}
