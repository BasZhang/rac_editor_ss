package com.ourpalm.editor.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourpalm.editor.service.TablesService;
import com.ourpalm.editor.util.MaverickShellTool;
import com.ourpalm.editor.util.ShellUtils;
import com.ourpalm.jmx.RacingGmServiceMBean;
import com.ourpalm.logic.enums.CurrencyEnum;
import com.ourpalm.logic.model.mail.MailAttach;

@Controller
public class GmController {

	static final Logger logger = LoggerFactory.getLogger(GmController.class);

	@Autowired
	private RacingGmServiceMBean gmService;

	@Autowired
	private TablesService tableService;

	private SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "/gm_showUserNavi", method = { RequestMethod.GET })
	public String showPlayerInfo(Model model) {
		model.addAttribute("currencyNames", CurrencyEnum.values());
		return "gm_userNavigation";
	}

	@ResponseBody
	@RequestMapping(value = "/gm_addVirtual", method = { RequestMethod.POST })
	public Object addVirtual(boolean byNick, String uid, @RequestParam(value = "ctypes[]") CurrencyEnum[] ctypes, @RequestParam(value = "amounts[]") int[] amounts) {
		boolean addResult = byNick ? gmService.addCurrencyN(uid, ctypes, amounts) : gmService.addCurrency(uid, ctypes, amounts);

		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("AddVirtual:{}|{}|{}|{}|{}", new Object[] { gm, uid, Arrays.toString(ctypes), Arrays.toString(amounts), addResult });
		return addResult;
	}

	@ResponseBody
	@RequestMapping(value = "/gm_queryPlayer", method = { RequestMethod.POST })
	public Object queryPlayer(boolean byNick, String uid) {
		Map<String, Object> map = Maps.newHashMap();
		String base = byNick ? gmService.queryUserBaseN(uid) : gmService.queryUserBase(uid);
		map.put("base", JSON.parseObject(base, Map.class));
		String extra = byNick ? gmService.queryUserN(uid) : gmService.queryUser(uid);
		map.put("extra", JSON.parseObject(extra, Map.class));
		return JSON.toJSONString(map);
	}

	@ResponseBody
	@RequestMapping(value = "/gm_queryCars", method = { RequestMethod.POST })
	public Object queryCars(boolean byNick, String uid) {
		return byNick ? gmService.queryCarsN(uid) : gmService.queryCars(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/gm_queryFriends", method = { RequestMethod.POST })
	public Object queryFriends(boolean byNick, String uid) {
		return byNick ? gmService.queryFriendsN(uid) : gmService.queryFriends(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/gm_queryTours", method = { RequestMethod.POST })
	public Object queryTours(boolean byNick, String uid) {
		return byNick ? gmService.queryToursN(uid) : gmService.queryTours(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/gm_queryMatches", method = { RequestMethod.POST })
	public Object queryMatches(boolean byNick, String uid, int tid) {
		return byNick ? gmService.queryMatchesN(uid, tid) : gmService.queryMatches(uid, tid);
	}

	@ResponseBody
	@RequestMapping(value = "/gm_changeLevel", method = { RequestMethod.POST })
	public Object changeLevel(boolean byNick, String uid, int level) {
		boolean result = byNick ? gmService.changeLevelN(uid, level) : gmService.changeLevel(uid, level);

		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("ChangeLevel:{}|{}|{}|{}", new Object[] { gm, uid, level, result });
		return result;
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "/gm_mails", method = { RequestMethod.GET })
	public String showMails() {
		return "gm_mails";
	}

	@ResponseBody
	@RequestMapping(value = "/gm_sendMany", method = { RequestMethod.POST })
	public Object sendMany(String sender, String title, String content, int coin, int money, int prestige, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date validThru) {
		MailAttach attach = new MailAttach(money, coin, prestige, 0, null);
		gmService.sendGmMail(sender, null, title, content, attach, validThru);
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("SendMany:{}|{}|{}|{}|{}|{}|{}|{}", new Object[] { gm, sender, title, content, coin, money, prestige, std.format(validThru) });
		return "OK";
	}

	@ResponseBody
	@RequestMapping(value = "/gm_sendMail", method = { RequestMethod.POST })
	public Object sendMail(String sender, String uid, String title, String content, int coin, int money, int prestige) {
		MailAttach attach = new MailAttach(money, coin, prestige, 0, null);
		gmService.sendMail(sender, uid, title, content, attach);
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("SendMail:{}|{}|{}|{}|{}|{}|{}", new Object[] { gm, sender, uid, title, coin, money, prestige });
		return "OK";
	}

	@ResponseBody
	@RequestMapping(value = "gm_delMail", method = { RequestMethod.POST })
	public Object delMail(long mailId) {
		gmService.deleteGmMail(mailId);
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("DelMail:{}|{}", gm, mailId);
		return "OK";
	}

	@ResponseBody
	@RequestMapping(value = "gm_queryMail", method = { RequestMethod.POST })
	public Object queryMail(long mailId) {
		return gmService.queryMail(mailId);
	}

	@ResponseBody
	@RequestMapping(value = "gm_queryBox", method = { RequestMethod.POST })
	public Object queryBox(boolean byNick, String uid) {
		return byNick ? gmService.queryMailboxN(uid) : gmService.queryMailbox(uid);
	}

	@ResponseBody
	@RequestMapping(value = "gm_ban", method = { RequestMethod.POST })
	public Object ban(String uid, int banType, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date until) {
		if (banType == 1 && until == null)
			return "请输入临时封号结束时间！";
		gmService.banUser(uid, banType, until);
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Ban:{}|{}|{}|{}", new Object[] { gm, uid, banType, std.format(until) });
		return "OK";
	}

	@ResponseBody
	@RequestMapping(value = "gm_unban", method = { RequestMethod.POST })
	public Object unban(String uid) {
		gmService.unbanUser(uid);
		String gm = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Unban:{}|{}", gm, uid);
		return "OK";
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV','COMM')")
	@RequestMapping(value = "gm_actions", method = { RequestMethod.GET })
	public String showActions() {
		return "gm_queryLogs";
	}

	@ResponseBody
	@RequestMapping(value = "gm_queryLogs", method = { RequestMethod.POST })
	public List<String[]> queryLogs(String uid, String since, String logType, Map<String, Object> map) {
		List<String[]> cols = Lists.newArrayList();
		String LOG_SERVER_KEY_PATH = tableService.getSysProp("LOG_SERVER_KEY_PATH");
		String LOG_SERVER_ADDR = tableService.getSysProp("LOG_SERVER_ADDR");
		String LOG_SERVER_USERNAME = tableService.getSysProp("LOG_SERVER_USERNAME");
		MaverickShellTool sst = new MaverickShellTool(new File(LOG_SERVER_KEY_PATH), LOG_SERVER_ADDR, LOG_SERVER_USERNAME);
		String echo = "error";
		try {
			String cmds = ShellUtils.getSearchCmd(uid, since, logType);
			echo = sst.exec(cmds);
			String[] split = echo.split("\n");
			for (String one : split) {
				cols.add(one.split("\\|"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cols;
	}

}
