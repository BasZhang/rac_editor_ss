package com.ourpalm.editor.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ourpalm.editor.service.TablesService;
import com.ourpalm.editor.util.HttpTookit;

@Controller
public class GgmController {

	static final Logger logger = LoggerFactory.getLogger(GgmController.class);

	@Autowired
	private TablesService tableService;

	private SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 跳转展示
	 */
	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_start", method = { RequestMethod.GET })
	public String queryStart() {
		return "ggm_queryStart";
	}

	private String buildUrl(String protocolId) {
		String LOGIC_SERVER_ADDR = tableService.getSysProp("HTTP_REQUEST_PREFIX");
		StringBuilder url = new StringBuilder("http://");
		url.append(LOGIC_SERVER_ADDR);
		if (!LOGIC_SERVER_ADDR.endsWith("/")) {
			url.append('/');
		}
		url.append(protocolId);
		return url.toString();
	}

	private Object parseResponseData(String repStr) {
		try {
			Map<String, Object> response = JSON.<Map<String, Object>> parseObject(repStr, HashMap.class);
			Integer rc = (Integer) response.get("rc");
			if (rc == 0) {
				return response.get("data");
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_queryAll", method = { RequestMethod.POST })
	public String showAll(Model model, String uid, boolean byNick) {
		String url = buildUrl(byNick ? "0x00090002" : "0x00090001");
		Object object = parseResponseData(HttpTookit.doGet(url, (byNick ? "nickName=" : "uid=") + uid, "UTF-8", false));
		if (object == null) {
			model.addAttribute("msg", "找不到用户");
			return "ggm_queryStart";
		} else {
			System.out.println(object);
			model.addAttribute("data", object);
		}
		return "ggm_playerInfo";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_setCoin")
	public String setCoin(Model model, String uid, int amount) {
		String url = buildUrl("0x00090003");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&coin=" + amount, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("setCoin:{}|{}|{}", new Object[] { gm, uid, amount });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_setGem")
	public String setGem(Model model, String uid, int amount) {
		String url = buildUrl("0x00090004");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&gem=" + amount, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("setGem:{}|{}|{}", new Object[] { gm, uid, amount });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_setLevel")
	public String setLevel(Model model, String uid, int amount) {
		String url = buildUrl("0x00090005");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&level=" + amount, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("setLevel:{}|{}|{}", new Object[] { gm, uid, amount });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_setPrestige")
	public String setPrestige(Model model, String uid, int amount) {
		String url = buildUrl("0x00090006");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&prestige=" + amount, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("setPrestige:{}|{}|{}", new Object[] { gm, uid, amount });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_setCertificate")
	public String setCertificate(Model model, String uid, int amount) {
		String url = buildUrl("0x00090007");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&certificate=" + amount, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("setCertificate:{}|{}|{}", new Object[] { gm, uid, amount });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_ban")
	public String ban(Model model, String uid, int banType, String until) {
		String url = buildUrl("0x00090008");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&forever=" + (banType == 2 ? true : false) + "&until=" + until, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Ban:{}|{}|{}|{}", new Object[] { gm, uid, banType, until });
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "ggm_unban")
	public String unban(Model model, String uid) {
		String url = buildUrl("0x00090009");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid, "UTF-8", false));
		if (object == null) {
			return "error_page";
		} else {
			model.addAttribute("echo", "操作成功");
		}
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Unban:{}|{}", gm, uid);
		return showAll(model, uid, false);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "/gm_lookAll", method = { RequestMethod.POST })
	public Object lookAll(Model model, String type, String area, String gate) {
		String url = buildUrl("0x0009000a");
		Object object = parseResponseData(HttpTookit.doGet(url, "type=" + type + "&area=" + area + "&gate=" + gate, "UTF-8", false));

		model.addAttribute("gate", gate);
		model.addAttribute("type", type);
		model.addAttribute("areaId", area);
		model.addAttribute("data", object);
		return "gm_rankResult";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "/gm_lookSelf", method = { RequestMethod.POST })
	public Object lookSelf(Model model, String uid, String type, String area, String gate) {
		String url = buildUrl("0x0009000a");
		Object object = parseResponseData(HttpTookit.doGet(url, "uid=" + uid + "&type=" + type + "&area=" + area + "&gate=" + gate, "UTF-8", false));

		model.addAttribute("uid", uid);
		model.addAttribute("gate", gate);
		model.addAttribute("type", type);
		model.addAttribute("areaId", area);
		model.addAttribute("data", object);
		return "gm_rankResult";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "/gm_rankLook", method = { RequestMethod.GET })
	public String showRankLook(Model model) {
		String url = buildUrl("0x0009000b");
		Object object = parseResponseData(HttpTookit.doGet(url, null, "UTF-8", false));

		JSONObject jsonObject = (JSONObject) object;
		String[] list = jsonObject.getString("content").split("\\|");

		model.addAttribute("list", list);
		return "gm_rankLook";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@ResponseBody
	@RequestMapping(value = "/ggm_sendMany", method = { RequestMethod.POST })
	public Object sendMany(String sender, String title, String content, int coin, int money, int prestige, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date validThru) {
		String url = buildUrl("0x0009000c");
		Map<String, String> map = Maps.newHashMap();
		map.put("sender", sender);
		map.put("title", title);
		map.put("content", content);
		map.put("coin", String.valueOf(coin));
		map.put("gem", String.valueOf(money));
		map.put("prestige", String.valueOf(prestige));
		map.put("validThru", String.valueOf(validThru.getTime()));
		@SuppressWarnings("unused")
		Object object = parseResponseData(HttpTookit.doPost(url, map , "UTF-8", false));
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("SendMany:{}|{}|{}|{}|{}|{}|{}|{}", new Object[] { gm, sender, title, content, coin, money, prestige, std .format(validThru) });
		return "OK";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@ResponseBody
	@RequestMapping(value = "/ggm_sendMail", method = { RequestMethod.POST })
	public Object sendMail(String sender, String uid, String title, String content, int coin, int money, int prestige) {
		String url = buildUrl("0x0009000d");
		Map<String, String> map = Maps.newHashMap();
		map.put("sender", sender);
		map.put("uid", uid);
		map.put("title", title);
		map.put("content", content);
		map.put("coin", String.valueOf(coin));
		map.put("gem", String.valueOf(money));
		map.put("prestige", String.valueOf(prestige));
		@SuppressWarnings("unused")
		Object object = parseResponseData(HttpTookit.doPost(url, map , "UTF-8", false));
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("SendMail:{}|{}|{}|{}|{}|{}|{}", new Object[] { gm, sender, uid, title, coin, money, prestige });
		return "OK";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@ResponseBody
	@RequestMapping(value = "ggm_queryBox", method = { RequestMethod.POST })
	public Object queryBox(String uid) {
		String url = buildUrl("0x0009000e");
		Map<String, String> map = Maps.newHashMap();
		map.put("uid", uid);
		Object object = parseResponseData(HttpTookit.doPost(url, map , "UTF-8", false));
		return JSON.toJSONString(object);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@ResponseBody
	@RequestMapping(value = "ggm_queryMail", method = { RequestMethod.POST })
	public Object queryMail(long mailId) {
		String url = buildUrl("0x0009000f");
		Map<String, String> map = Maps.newHashMap();
		map.put("mailId", String.valueOf(mailId));
		Object object = parseResponseData(HttpTookit.doPost(url, map , "UTF-8", false));
		return JSON.toJSONString(object);
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@ResponseBody
	@RequestMapping(value = "ggm_delMail", method = { RequestMethod.POST })
	public Object delMail(long mailId) {
		String url = buildUrl("0x00090010");
		Map<String, String> map = Maps.newHashMap();
		map.put("mailId", String.valueOf(mailId));
		@SuppressWarnings("unused")
		Object object = parseResponseData(HttpTookit.doPost(url, map , "UTF-8", false));
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("DelMail:{}|{}", gm, mailId);
		return "OK";
	}

}
