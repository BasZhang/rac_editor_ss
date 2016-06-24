package com.ourpalm.editor.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;
import com.ourpalm.editor.dao.TablesDao;
import com.ourpalm.editor.dto.TableTitle;
import com.ourpalm.editor.dto.Tree;
import com.ourpalm.editor.entity.tables.conf.ArrayRule;
import com.ourpalm.editor.entity.tables.conf.Constraint;
import com.ourpalm.editor.entity.tables.conf.FileTransfer;
import com.ourpalm.editor.entity.tables.conf.SysProperty;
import com.ourpalm.editor.service.TablesService;
import com.ourpalm.editor.util.BaseQEntity;
import com.ourpalm.editor.util.ClassUtils;
import com.ourpalm.editor.util.DynamicSpecifications;
import com.ourpalm.editor.util.EditorType;
import com.ourpalm.editor.util.EntityUtils;
import com.ourpalm.editor.util.FileUtils;
import com.ourpalm.editor.util.ReflectionUtils;
import com.ourpalm.editor.util.SearchFilter;

@Service("tablesService")
public class TablesServiceImpl implements TablesService {

	@Autowired
	TablesDao tablesDao;

	@Override
	public <T extends TableEntity, I extends Serializable> T getDataById(Class<T> t, I id) {
		return tablesDao.get(t, id);
	}

	@Override
	public <T extends TableEntity> List<T> findTableListByIds(Class<T> t, Long[] ids) {
		SimpleExpression[] list = new SimpleExpression[ids.length];
		for (int i = 0; i < ids.length; i++) {
			list[i] = Restrictions.eq("code", Long.valueOf(ids[i]));
		}
		List<T> all = tablesDao.getAll(t, Restrictions.or(list));
		return all;
	}

	@Override
	public void insertData(TableEntity t) {
		tablesDao.save(t);
	}

	@Override
	public void updateData(TableEntity t) {
		tablesDao.update(t);
	}

	public <T extends TableEntity, I extends Serializable> void deleteDataById(Class<T> t, I id) {
		tablesDao.delete(t, id);
	}

	@Override
	public <T extends TableEntity> void setEditor(TableTitle dto, Class<T> beanClass, String table, String fieldName) {
		Field field = ReflectionUtils.getDeclaredField(beanClass, fieldName);
		// 得到数据表table中column列的外表关联参数。
		List<Constraint> constraints = tablesDao.getAll(Constraint.class, Restrictions.eq("aname", table), Restrictions.eq("apos", fieldName));
		String[] editorOptionsForeign = null;
		if (constraints != null && constraints.size() > 0) {
			// 数组表示参数，数组下标{@code [0]}对应的表B名称，下标{@code [1]}表示此关联在表B中的列
			Constraint cons = constraints.get(0);
			editorOptionsForeign = new String[] { cons.getBname(), cons.getBpos() };
		}
		List<ArrayRule> arrayRules_key = tablesDao.getAll(ArrayRule.class, Restrictions.eq("tableName", table), Restrictions.eq("keyField", fieldName));
		EntityUtils.setEditorType(dto, field, editorOptionsForeign, arrayRules_key);
		if (dto.getEditorType() == EditorType.Array) {
			List<ArrayRule> arrayRules_target = tablesDao.getAll(ArrayRule.class, Restrictions.eq("tableName", table), Restrictions.eq("targetField", fieldName));
			// Map<控制列名, Map<可能的控制值, List<ArrayRule>>>
			Map<String, Map<String, List<ArrayRule>>> map = Maps.newHashMap();
			for (ArrayRule arrayRule : arrayRules_target) {
				Map<String, List<ArrayRule>> bySameKeyField = map.get(arrayRule.getKeyField());
				if (bySameKeyField == null) {
					bySameKeyField = Maps.newHashMap();
					map.put(arrayRule.getKeyField(), bySameKeyField);
				}
				List<ArrayRule> bySameKeyValue = bySameKeyField.get(arrayRule.getKeyValue());
				if (bySameKeyValue == null) {
					bySameKeyValue = Lists.newArrayList();
					bySameKeyField.put(arrayRule.getKeyValue(), bySameKeyValue);
				}
				bySameKeyValue.add(arrayRule);
			}
			String json = JSON.toJSONString(map);
			String[] editorOptions = new String[] { json };
			dto.setEditorOptions(editorOptions);
		}
	}

	@Override
	public boolean isDeletable(String tableB, TableEntity t) {
		// 去Constraint表取，所有bname列和参数tableB完全匹配的所有记录（不只是数量）。若数量大于0，说明有这样的配置（但这一行记录不一定被关联），此时得知aname，apos，bpos。
		List<Constraint> findByTb = tablesDao.getAll(Constraint.class, Restrictions.eq("bname", tableB));
		if (findByTb.size() > 0) {
			for (Constraint con : findByTb) {
				String tableA = con.getAname();
				String posA = con.getApos();
				String posB = con.getBpos();
				Object posBValue = null;
				try {
					posBValue = PropertyUtils.getProperty(t, posB);
				} catch (Exception e) {
					e.printStackTrace();
					// 异常说明t没有posB名字的字段，这可能是因为这条con的posB没有配正确，对判断关联没影响。
					continue;
				}
				Class<TableEntity> clazzA = EntityUtils.getMappedClass(tableA);
				Field posAField = ReflectionUtils.getDeclaredField(clazzA, posA);
				// 如果aname.apos不是数组，去aname表中查，如果aname.apos字段的值等于
				// t.bpos字段值的记录多于1条，说明被关联，不能删除
				if (!posAField.isAnnotationPresent(ArrayData.class)) {
					int count = tablesDao.count(clazzA, Restrictions.eq(posA, posBValue));
					if (count > 0) {
						return false;
					}
				}
				// 如果aname.apos是数组，去aname表中查，逐条分析是否存在aname.apos数组包含t.bpos值（并且二者是关联配置的），如果有，立即返回不能删除。
				else {
					// 根据con.getCode()和ArrayRule表中constraint_id相对应的那条，我们才能知道要查什么样的关键词。
					List<ArrayRule> keyWordsShouldBe = tablesDao.getAll(ArrayRule.class, Restrictions.eq("tableName", tableA), Restrictions.eq("constraint_id", con.getCode()));
					// 从keyWordsShouldBe任取一个，得到查询关键词。
					for (ArrayRule queryKeyWords : keyWordsShouldBe) {
						List<ArrayRule> rulesWeNeed = tablesDao.getAll(ArrayRule.class, Restrictions.eq("tableName", tableA), Restrictions.eq("keyField", queryKeyWords.getKeyField()), Restrictions
								.eq("keyValue", queryKeyWords.getKeyValue()), Restrictions.eq("targetField", posA));
						Collections.sort(rulesWeNeed, new Comparator<ArrayRule>() {
							// 根据数组下标排序rulesWeNeed。
							@Override
							public int compare(ArrayRule o1, ArrayRule o2) {
								return (o1.getIdx() > o2.getIdx()) ? 1 : (o1.getIdx() == o2.getIdx()) ? 0 : -1;
							}
						});
						for (int i = 0; i < rulesWeNeed.size(); ++i) {
							if (con.getCode().equals(rulesWeNeed.get(i).getConstraint_id())) {
								// 数组若干个格中的哪一个下标
								int index = i;
								// 去aname找aname.apos的格式匹配
								Field targetField = ReflectionUtils.getDeclaredField(clazzA, con.getApos());
								String sqlRestriction = EntityUtils.makeContainRestriction(targetField.getAnnotation(Column.class).name(), rulesWeNeed.size(), index, String.valueOf(posBValue));
								Class<?> keyFieldType = ReflectionUtils.getDeclaredField(clazzA, queryKeyWords.getKeyField()).getType();
								int count = tablesDao.count(clazzA, Restrictions.eq(queryKeyWords.getKeyField(), ReflectionUtils.convertStringToObject(queryKeyWords.getKeyValue(), keyFieldType)),
										Restrictions.sqlRestriction(sqlRestriction));
								if (count > 0) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public <T extends TableEntity> BaseQEntity<T> getPage(Class<T> t, BaseQEntity<T> b, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> specification = DynamicSpecifications.bySearchFilter(filters.values(), t);
		b = tablesDao.getPage(t, b, specification);
		return b;
	}

	@Override
	public <T extends TableEntity> String downloadContent(Class<T> t) {
		List<Field> declaredFields = ReflectionUtils.getDeclaredFields(t);
		// 获得所有数组的field
		Map<Field, Long> arrayFields = Maps.newHashMap();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(ArrayData.class)) {
				long fieldMax = tablesDao.count(t, field);
				arrayFields.put(field, fieldMax);
			}
		}
		StringBuffer spellTitle = EntityUtils.spellTitle(t, declaredFields, arrayFields);
		List<T> data = tablesDao.getAll(t);
		StringBuffer spellContent = EntityUtils.spellContent(t, declaredFields, arrayFields, data);

		return spellTitle.append(spellContent).toString();
	}

	@Override
	public List<Tree> getTreeLayer(String packageName) {
		List<Tree> list = Lists.newArrayList();
		if (packageName == null) {
			Tree tree = new Tree(EntityUtils.PACKAGE, "表格编辑", "closed");
			list.add(tree);
			return list;
		}
		Set<String> names;
		try {
			names = ClassUtils.getDirectChildrenNames(packageName);
			for (String f : names) {
				if (f.endsWith(".class")) { // Class
					String className = f.substring(0, f.indexOf(".class"));
					Class<?> c = null;
					try {
						c = Thread.currentThread().getContextClassLoader().loadClass(className);
						if (!c.isInterface() && TableEntity.class.isAssignableFrom(c)) {
							String tableName = c.getAnnotation(Table.class).name();
							String tableDesc = c.isAnnotationPresent(Comment.class) ? c.getAnnotation(Comment.class).desc() : c.getSimpleName();
							list.add(new Tree(tableName, tableDesc, "open"));
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else { // Package
					String packageDesc = f.substring(f.lastIndexOf('.') + 1);
					Class<?> c = ClassUtils.findPackageInfo(f);
					if (c != null && c.isAnnotationPresent(Comment.class)) {
						packageDesc = c.getAnnotation(Comment.class).desc();
					}
					list.add(new Tree(f, packageDesc, "closed"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(list, new Comparator<Tree>() {
			@Override
			public int compare(Tree o1, Tree o2) {
				if (o2.getState().compareTo(o1.getState()) > 0)
					return 1;
				else if (o2.getState().compareTo(o1.getState()) < 0)
					return -1;
				else
					return o1.getText().compareTo(o2.getText());
			}
		});
		return list;
	}

	@Override
	public Set<String> getKeyOptions(String tableName, String field) {
		List<ArrayRule> alist = tablesDao.getAll(ArrayRule.class, Restrictions.eq("tableName", tableName), Restrictions.eq("keyField", field));
		Set<String> set = Sets.newTreeSet();
		for (ArrayRule one : alist) {
			set.add(one.getKeyValue());
		}
		return set;
	}

	@Override
	public String getTransToPath(String tableName) {
		Class<? extends TableEntity> tableCls = EntityUtils.getMappedClass(tableName);
		if (tableCls == null)
			return null;
		FileTransfer ft = tablesDao.getFirst(FileTransfer.class, Restrictions.eq("tableName", tableName));
		if (ft != null) {
			return ft.getToPath(); // 如果直接配置了这个类的输出路径，则返回配置的
		}
		ft = tablesDao.getFirst(FileTransfer.class, Restrictions.eq("tableName", DEFAULT_ROOT_FOLDER));
		if (ft != null) {
			// 退而如果配置了资源的根目录，以包结构对应出一个文件在资源根目录下的位置
			String toPathRoot = ft.getToPath();
			String fileName = tableCls.getSimpleName() + ".txt";
			return FileUtils.migrateResPath(fileName, tableCls, toPathRoot);
		}
		return null;
	}

	@Override
	public String getSysProp(String key) {
		SysProperty property = tablesDao.getUniq(SysProperty.class, Restrictions.eq("propKey", key));
		if (property == null)
			return null;
		else
			return property.getPropValue();
	}
}
