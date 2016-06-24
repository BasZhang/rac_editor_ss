package com.ourpalm.editor.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.dto.TableTitle;
import com.ourpalm.editor.dto.Tree;
import com.ourpalm.editor.util.BaseQEntity;

public interface TablesService {
	
	/**
	 * 默认的导出文件夹
	 */
	String DEFAULT_ROOT_FOLDER = "_tt_toPath_root";

	public <T extends TableEntity, I extends Serializable> T getDataById(Class<T> t, I id);

	public <T extends TableEntity> List<T> findTableListByIds(Class<T> t, Long[] ids);

	public <T extends TableEntity> BaseQEntity<T> getPage(Class<T> t, BaseQEntity<T> b, Map<String, Object> searchParams);

	public <T extends TableEntity, I extends Serializable> void deleteDataById(Class<T> t, I id);

	/**
	 * 新增一行数据。
	 * 
	 * @param t
	 *            数据对象
	 */
	public void insertData(TableEntity t);

	/**
	 * 更新一行已有数据。
	 * 
	 * @param t
	 *            数据对象
	 */
	public void updateData(TableEntity t);

	/**
	 * 给{@code TableTitleDto}的@{@code editorType}和{@code editorOptions}字段赋参数。
	 * 
	 * @param dto
	 *            被赋值对象
	 * @param beanClass
	 *            对象的类
	 * @param table
	 *            表名
	 * @param fieldName
	 *            字段名
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public <T extends TableEntity> void setEditor(TableTitle dto, Class<T> beanClass, String table, String fieldName);

	/**
	 * 通过关联信息，判断{@code tableB}表的值对象{@code t}是否可以删除。
	 * 
	 * @param tableB
	 *            表名
	 * @param t
	 *            表记录值对象
	 * @return {@code true}表示可删，{@code false}表示不可删。
	 */
	public boolean isDeletable(String tableB, TableEntity t);

	/**
	 * 得到下载表的内容。
	 * 
	 * @param t
	 *            数据表类
	 * @return 下载表的内容。
	 */
	public <T extends TableEntity> String downloadContent(Class<T> t);

	/**
	 * 根据包名得到包内数据表的树状结构。
	 * <p>
	 * 如果{@code packageName}为{@code null}，得到一个虚拟的根节点。
	 * 
	 * @param packageName
	 *            包名
	 * @return 包名得到包内数据表的树状结构。
	 */
	public List<Tree> getTreeLayer(String packageName);

	/**
	 * 根据表名和一个字段的名字，去数组配置表查找这个字段已配的可能值。
	 * 
	 * @param tableName
	 *            表名
	 * @param field
	 *            字段名
	 * @return 数组配置表中配的，这个表这个字段的可能值集合。
	 */
	public Set<String> getKeyOptions(String tableName, String field);

	/**
	 * 根据表名，得到将要导入到的文件夹位置路径。
	 * 
	 * @param tableName
	 *            表名
	 * @return 生成目标文件路径，或得不到相关配置返回<code>null</code>。
	 */
	public String getTransToPath(String tableName);

	/**
	 * 获得配置的变量值。
	 * 
	 * @param key 变量名
	 * @return 变量值。
	 */
	public String getSysProp(String key);

}
