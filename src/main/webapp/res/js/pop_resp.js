$(function() {
	var $respDiv = $('<div style="padding: 5px">');
	var $window = $respDiv.appendTo($(this)).window({
		width : 575,
		height : 599,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		iconCls : 'icon-search',
		modal : false,
		title : "...",
		closed : true,
		onClose : function() {
			$respDiv.empty();
		}
	});
	$('form.pop-response').each(function() {
		$form = $(this);
		var reqUrl = $form.attr('action');
		$(':submit', $form).hide();
		$newBtn = $('<button type="button">提交</button>').bind('click', function(){
			var reqParams = {};
			$parentform = $(this).parent('form');
			$inputs = $('input[name]', $parentform);
			$.each($inputs, function(){
				$input = $(this);
				$key = $input.attr('name');
				var inputType = $input.attr('type');
				if (inputType === 'image' || inputType === 'file') {
					// 不支持
					$.messager.show({
						title : '内容被忽略',
						msg : "input类型不支持image和file！",
						timeout : 5000,
						showType : 'slide'
					});
					return;
				} else if (inputType === 'radio') { // radio特殊处理
					if ($input.attr('checked')) {
						reqParams[$key] = $input.val();
					}
				} else if (reqParams.hasOwnProperty($key)) { // 重复域的转数组
					if (!Array.prototype.isPrototypeOf(reqParams[$key])) {
						var transToArray = [reqParams[$key]];
						reqParams[$key] = transToArray;
					}
					reqParams[$key].push($input.val());
				} else {
					reqParams[$key] = $input.val();
				}
			});
			$.post(reqUrl, reqParams, function(data) {
				$respDiv.empty().append($('<p>' + formatJson(data) + '</p>'));
				$window.window('open');
				// alert(data);
			});
		});
		$form.append($newBtn);
	}
	);
});
