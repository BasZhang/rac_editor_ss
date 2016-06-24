package com.ourpalm.editor.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.dto.TableTitle;
import com.ourpalm.editor.dto.Tree;
import com.ourpalm.editor.interceptor.LockTableInterceptor;
import com.ourpalm.editor.service.TablesService;
import com.ourpalm.editor.util.BaseQEntity;
import com.ourpalm.editor.util.ClassUtils;
import com.ourpalm.editor.util.EntityUtils;
import com.ourpalm.editor.util.FileUtils;
import com.ourpalm.editor.util.ReflectionUtils;
import com.ourpalm.editor.util.Servlets;

@Controller
public class EditorController {
	
	static final Logger logger = LoggerFactory.getLogger(EditorController.class);

	@Autowired
	TablesService tablesService;

	@ResponseBody
	@RequestMapping(value = "/getTree", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Tree> getTree(String id) {
		List<Tree> list = tablesService.getTreeLayer(id);
		return list;
	}

	/**
	 * 表头信息
	 * 
	 * @param model
	 * @param table
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{table}/open", method = RequestMethod.GET)
	public String openTable(Model model, @PathVariable String table) {
		model.addAttribute("tablename", table);
		Class<TableEntity> beanClass = EntityUtils.getMappedClass(table);
		if (beanClass != null) {
			List<TableTitle> titles = EntityUtils.getSimpleTitles(beanClass);
			for (TableTitle title : titles) {
				tablesService.setEditor(title, beanClass, table, title.getName());
			}
			model.addAttribute("columns", titles);
		}
		model.addAttribute("lockTableIP", LockTableInterceptor.lockTableIP(table));
		return "table";
	}

	/**
	 * 加载表数据
	 * 
	 * @param tablename
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{tablename}/loaddata", method = RequestMethod.POST)
	public BaseQEntity<TableEntity> loadData(@PathVariable String tablename, @RequestParam(required = true) int page, @RequestParam(required = true) int rows, HttpServletRequest request) {
		Class<TableEntity> beanPrototype = EntityUtils.getMappedClass(tablename);
		BaseQEntity<TableEntity> b = BaseQEntity.<TableEntity> newInstance().setCurrentPage(page).setPerPageRows(rows);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		tablesService.getPage(beanPrototype, b, searchParams);
		return b;
	}

	/**
	 * 保存表数据
	 * 
	 * @param model
	 * @param tablename
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{tablename}/save", method = { RequestMethod.POST })
	public Map<String, Object> saveRow(Model model, @PathVariable TableEntity tablename, HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		if (parameterMap.isEmpty()) {
			return map;
		} else {
			TableEntity t = EntityUtils.getTableEntity(tablename, parameterMap);
			tablesService.insertData(t);
			Long code = t.getCode();
			map.put("code", code);
			return map;
		}
	}

	/**
	 * 更新表数据
	 * 
	 * @param model
	 * @param tablename
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{tablename}/update", method = RequestMethod.POST)
	public Map<String, Object> updateRow(Model model, @PathVariable TableEntity tablename, HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		if (!parameterMap.isEmpty()) {
			TableEntity t = EntityUtils.getTableEntity(tablename, parameterMap);
			if (t.getCode() != null) {
				tablesService.updateData(t);
				map.put("code", t.getCode());
				return map;
			}
		}
		map.put("success", false);
		return map;
	}

	/**
	 * 删除表数据
	 * 
	 * @param model
	 * @param tablename
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{tablename}/delete", method = { RequestMethod.POST })
	public Map<String, Object> deleteRow(Model model, @PathVariable String tablename, @RequestParam(required = true) Long id) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		if (id != null) {
			Class<TableEntity> beanClass = EntityUtils.getMappedClass(tablename);
			TableEntity t = tablesService.getDataById(beanClass, id);
			// // 验证关联，考虑数组的情况。
			boolean isDeletable = tablesService.isDeletable(tablename, t);
			if (isDeletable) {
				tablesService.deleteDataById(beanClass, t.getCode());
				return map;
			}
		}
		map.put("isError", true);
		map.put("errorMsg", "存在关联，不能删除！");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/validateCombobox", method = RequestMethod.POST)
	public Map<String, Object> getKeyFiledValues(@RequestParam(required = true) String tableName, @RequestParam(required = true) String field) {
		Map<String, Object> model = Maps.newHashMap();
		Set<String> valueSet = tablesService.getKeyOptions(tableName, field);
		model.put("options", valueSet);
		model.put("dataType", ReflectionUtils.getDeclaredField(EntityUtils.getMappedClass(tableName), field).getType().getSimpleName());
		return model;
	}

	/**
	 * 用于下拉框选择表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTables", method = { RequestMethod.POST })
	public List<String[]> getTables() {
		List<String[]> tableNames = EntityUtils.getTableNames();
		return tableNames;
	}

	/**
	 * 表头信息用于ajax
	 * 
	 * @param table
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{table}/columns", method = RequestMethod.POST)
	public List<TableTitle> openTable(@PathVariable String table) {
		Class<TableEntity> beanClass = EntityUtils.getMappedClass(table);
		List<TableTitle> titles = null;
		if (beanClass != null) {
			titles = EntityUtils.getSimpleTitles(beanClass);
		}
		return titles;
	}

	/**
	 * 为弹出{@code datagrid}层赋必要的值。
	 * 
	 * @param model
	 *            存值的对象
	 * @param relatedtable
	 *            弹出层查询的表名
	 * @param relatedfield
	 *            弹出层表的关联列字段名
	 * @param table
	 *            父层表名
	 * @param field
	 *            父层的关联字段名。
	 * @param arrayLength
	 *            可填元素个数
	 * @return 转移到的页面
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{relatedtable}/popopen")
	public String openPopupTable(Model model, @PathVariable String relatedtable, @RequestParam(required = true) String relatedfield, @RequestParam(required = true) String table,
			@RequestParam(required = true) String field) {
		model.addAttribute("tablename", relatedtable);
		Class<TableEntity> relatedClass = EntityUtils.getMappedClass(relatedtable);
		if (relatedClass != null) {
			List<TableTitle> titles = EntityUtils.getSimpleTitles(relatedClass);
			model.addAttribute("columns", titles);
			model.addAttribute("relatedfield", relatedfield);
			model.addAttribute("field", field);
			String defaultLiteral = EntityUtils.getDefaultLiteral(table, field);
			model.addAttribute("defaultliteral", defaultLiteral);
		}
		return "table_popup";
	}

	/**
	 * 根据id获得表数据
	 * 
	 * @param model
	 * @param table
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{table}/find", method = { RequestMethod.POST })
	public List<TableEntity> findTableListByIds(Model model, @PathVariable String table, @RequestParam(required = true) Long[] id) {
		Class<TableEntity> beanClass = EntityUtils.getMappedClass(table);
		List<TableEntity> data = tablesService.findTableListByIds(beanClass, id);
		return data;
	}

	/**
	 * 锁定表
	 * 
	 * @param table
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{table}/lockTable", method = { RequestMethod.POST })
	public String lockTable(@PathVariable String table, HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		LockTableInterceptor.lockTable(table, remoteAddr);
		return "OK";
	}

	/**
	 * 解锁表
	 * 
	 * @param table
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{table}/unLockTable", method = { RequestMethod.POST })
	public String unLockTable(@PathVariable String table) {
		LockTableInterceptor.unLockTable(table);
		return "OK";
	}

	/**
	 * 下载表数据
	 * 
	 * @param table
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{table}/download", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> downloadTable(@PathVariable String table) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", table + ".txt");

		Class<TableEntity> t = EntityUtils.getMappedClass(table);
		String downloadContent = tablesService.downloadContent(t);

		return new ResponseEntity<byte[]>(downloadContent.getBytes(Charset.forName("utf-8")), headers, HttpStatus.CREATED);
	}

	/**
	 * 将表格数据导出txt，传到指定的路径
	 * 
	 * @param table
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{table}/transfer", method = { RequestMethod.POST })
	public Map<String, Object> transferTable(@PathVariable String table) {
		Map<String, Object> map = Maps.newHashMap();
		String toPath = tablesService.getTransToPath(table);
		if (toPath == null) {
			map.put("msg", "未找到" + table + "的输出文件路径，请在“conf:导入服务器”中配置" + table + "或_tt_toFile_root");
		} else {
			Class<TableEntity> t = EntityUtils.getMappedClass(table);
			String content = tablesService.downloadContent(t);
			try {
				FileUtils.makeFile(toPath, content);
				map.put("successed", true);
				map.put("msg", "已将" + table + "导入到" + toPath);
			} catch (IOException e) {
				e.printStackTrace();
				map.put("msg", "将" + table + "导入到" + toPath + "发生错误！\n" + e);
			}
		}
		return map;
	}

	/**
	 * 下载文件夹
	 * 
	 * @param table
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{folder}/downloadFolder", method = { RequestMethod.GET })
	public void downloadFolder(@PathVariable String folder, HttpServletResponse response) throws IOException, ClassNotFoundException {
		String name = folder.substring(folder.lastIndexOf('.'));

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=" + name + ".zip");
		response.setContentType("application/octet-stream; charset=utf-8");

		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		out.setEncoding("utf-8");
		List<Class<TableEntity>> classes = Lists.newArrayList();
		classes.addAll(ClassUtils.getSubClasses(folder, TableEntity.class));
		for (Class<TableEntity> t : classes) {
			if (!t.isInterface() && !t.getName().contains("conf")) {
				String downloadContent = tablesService.downloadContent(t);
				String path = t.getName().replaceAll(EntityUtils.PACKAGE + ".", "").replace(".", "/");
				out.putNextEntry(new ZipEntry(path + ".txt"));
				out.write(downloadContent.getBytes(Charset.forName("utf-8")));
			}
		}
		out.flush();
		out.close();
	}
}
