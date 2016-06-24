<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GM工具</title>
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="./res/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="./res/css/demo.css">
		<script type="text/javascript" src="./res/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="./res/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./res/calendar/WdatePicker.js"></script>
		<script type="text/javascript">
			$(function(){
				var $echo = $('#echo'),
					_text = $echo ? $echo.text() : undefined;
				$echo && $echo.remove();
				_text && alert(_text);
			});
		</script>
	</head>
	<body>
		<h2>
			UID：${data.uid} 的用户信息
		</h2>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					UID
				</dt>
				<dd>
					${data.uid}
				</dd>
				<dt>
					昵称
				</dt>
				<dd>
					${data.nickName}
				</dd>
				<dt>
					地区
				</dt>
				<dd>
					${data.area}
				</dd>
				<dt>
					账号状态（0正常、1临时封停、2永久封停）
				</dt>
				<dd>
					${data.state}
				</dd>
				<dt>
					封号至
				</dt>
				<dd>
					<date:date value="${data.banUntil}" />
				</dd>
				<dt>
					是否是新手
				</dt>
				<dd>
					${data.newbie}
				</dd>
			</dl>
			<form class="pop-response" action="ggm_ban" method="post">
			<b>封号：</b>
				<input type="hidden" name="uid" value="${data.uid}"/>
				时间&emsp;
				<input name="until" style="width: 150px" class="text-input-small"
					type="text"
					onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" value="" />
				封号类型&emsp;
				<select class="easyui-combobox"
					name="banType" style="width: 150px;">
						<option value="1">临时</option>
						<option value="2">永久</option>
				</select>
				<button type="submit">
					封号
				</button>
			</form>
			<form class="pop-response" action="ggm_unban" method="post">
			<b>解封：</b>
				<input type="hidden" name="uid" value="${data.uid}" />
				<button type="submit">
					解封
				</button>
			</form>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					游戏币
				</dt>
				<dd>
					<form action="ggm_setCoin" method="post">
						${data.coin}&emsp; <b>改为：</b>
						<input type="hidden" name="uid" value="${data.uid}" />
						<input type="text" name="amount" required="required"
							value="${data.coin}" />
						<button type="submit">
							提交
						</button>
					</form>
				</dd>
				<dt>
					充值币
				</dt>
				<dd>
					<form action="ggm_setGem" method="post">
						${data.gem}&emsp; <b>改为：</b>
						<input type="hidden" name="uid" value="${data.uid}" />
						<input type="text" name="amount" required="required"
							value="${data.gem}" />
						<button type="submit">
							提交
						</button>
					</form>
				</dd>
				<dt>
					车手等级
				</dt>
				<dd>
					<form action="ggm_setLevel" method="post">
						${data.level}&emsp; <b>改为：</b>
						<input type="hidden" name="uid" value="${data.uid}" />
						<input type="text" name="amount" required="required"
							value="${data.level}" />
						<button type="submit">
							提交
						</button>
					</form>
				</dd>
				<dt>
					声望
				</dt>
				<dd>
					<form action="ggm_setPrestige" method="post">
						${data.prestige}&emsp; <b>改为：</b>
						<input type="hidden" name="uid" value="${data.uid}" />
						<input type="text" name="amount" required="required"
							value="${data.prestige}" />
						<button type="submit">
							提交
						</button>
					</form>
				</dd>
				<dt>
					积分
				</dt>
				<dd>
					${data.score}
				</dd>
				<dt>
					星星
				</dt>
				<dd>
					${data.stars}
				</dd>
				<dt>
					金杯
				</dt>
				<dd>
					${data.cup[0]}
				</dd>
				<dt>
					银杯
				</dt>
				<dd>
					${data.cup[1]}
				</dd>
				<dt>
					铜杯
				</dt>
				<dd>
					${data.cup[2]}
				</dd>
				<dt>
					资格证书
				</dt>
				<dd>
					<form action="ggm_setCertificate" method="post">
						${data.certificate}&emsp; <b>改为：</b>
						<input type="hidden" name="uid" value="${data.uid}" />
						<input type="text" name="amount" required="required"
							value="${data.certificate}" />
						<button type="submit">
							提交
						</button>
					</form>
				</dd>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					总比赛数
				</dt>
				<dd>
					${data.totalCount}
				</dd>
				<dt>
					赢得比赛数
				</dt>
				<dd>
					${data.winCount}
				</dd>
				<dt>
					完成比赛数
				</dt>
				<dd>
					${data.doneCount}
				</dd>
				<dt>
					最后一次比赛ID
				</dt>
				<dd>
					${data.lastMatchId}
				</dd>
				<dt>
					最后一次赛事ID
				</dt>
				<dd>
					${data.lastTournamentId}
				</dd>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					好友提醒数
				</dt>
				<dd>
					${data.friendNitfys}
				</dd>
				<dt>
					新邮件提醒数
				</dt>
				<dd>
					${data.newMailCount}
				</dd>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					最后一次请求时间
				</dt>
				<dd>
					<date:date value="${data.lastRequestTime}" />
				</dd>
				<dt>
					上次登录时间
				</dt>
				<dd>
					<date:date value="${data.lastLoginDate}" />
				</dd>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
				<dt>
					拥有车ID集合
				</dt>
				<dd>
					${data.carIds}
				</dd>
				<dt>
					上次使用的车
				</dt>
				<dd>
					<dl>
						<dt>
							车ID
						</dt>
						<dd>
							${data.lastUseCar.cid}
						</dd>
						<dt>
							当前的道具
						</dt>
						<dd>
							${data.lastUseCar.currentProp}
						</dd>
						<dt>
							固定的道具
						</dt>
						<dd>
							${data.lastUseCar.solidProp}
						</dd>
						<dt>
							升级的道具
						</dt>
						<dd>
							${data.lastUseCar.updateProp}
						</dd>
					</dl>
				</dd>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
			</dl>
		</div>
		<div style="padding: 5px; border: 1px solid #ddd;">
			<dl>
			</dl>
		</div>
		<c:if test="${echo != null}">
			<label id="echo">${echo}</label>
		</c:if>
	</body>
</html>