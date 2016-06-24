var _logtypes = Object.seal([
{
	"label" : "注册",
	"value" : "role-register",
	"hint" : ['发生时间','特征串','通行证服务器标识','游戏逻辑服务器标识','游戏逻辑服务器版本号','客户端版本号','客户端IP','用户通行证','用户引流渠道','UID','角色ID','角色等级','角色VIP等级','虚拟币种1','币种1余额','…','虚拟币种N','币种N余额','角色名']
}, {
	"label" : "登录",
	"value" : "role-login",
	"hint" : ['发生时间','特征串','通行证服务器标识','游戏逻辑服务器标识','游戏逻辑服务器版本号','客户端版本号','客户端IP','用户通行证','用户引流渠道','UID','角色ID','角色等级','角色VIP等级','虚拟币种1','币种1余额','…','虚拟币种N','币种N余额']
}, {
	"label" : "消费",
	"value" : "role-debit",
	"hint" : ['发生时间','特征串','通行证服务器标识','游戏逻辑服务器标识','游戏逻辑服务器版本号','客户端版本号','客户端IP','用户通行证','用户引流渠道','UID','角色ID','角色等级','角色VIP等级','消费类型','项目类型','物品ID','物品个数','对方角色ID','虚拟币种','金额','该币种的余额']
} ]);

/**
 * 根据log的value字段取对应的结构。
 * 
 * @param val
 *            log的value字段
 * @return _logtypes对应的结构或undefined
 */
function searchLogInfo(val) {
	var _ret;
	for (var i = _logtypes.length; i--; ) if (_logtypes[i].value === val) {
		_ret = _logtypes[i];
		break;
	}
	return _ret;
}

$(function() {
	var $logtypeCombo = $('#logtypeCombo'),
		$logHint = $('#logHint'),
		$uid = $('#uid'),
		$since = $('#since'),
		$result = $('#results');
	
	$logtypeCombo.combobox({
		textField: 'label',
		valueField: 'value',
		data: _logtypes,
		onSelect : function(record){
			$logHint.empty();
			$result.empty();
			$.each(record.hint, function(index, data){
				$logHint.append($('<th>' + data + '</th>'));
			});
		}
	});
	$('#submit').bind('click', function(){
		$mask = $("<div class=\"datagrid-mask\"></div>")
			.css({display:"block",width:"100%",height:$(window).height()})
			.appendTo("body");
		$pending = $("<div class=\"datagrid-mask-msg\"></div>")
			.html("Processing, please wait ...")
			.css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2})
			.appendTo("body");
		var _uid = $uid.val(),
			_since = $since.val(),
			_logType = $logtypeCombo.combobox('getValue');
		$.post('./gm_queryLogs', {'uid':_uid, 'since':_since, 'logType':_logType}, function(data){
			$result.empty();
			$.each(data, function(rowIndex, row){
				var $tr = $('<tr style="height: 22; text-align: center; background-color: #FFFFFF">');
				$.each(row, function(colIndex, col){
					$tr.append($('<td>' + col + '</td>'));
				});
				$result.append($tr);
				$mask.remove();
		        $pending.remove();
			});
		});
	});
});