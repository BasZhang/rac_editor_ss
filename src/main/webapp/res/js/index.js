
String.prototype.startWith=function(s){
	if(s===undefined||s===null||s===""||this.length===0||s.length>this.length)
		return false;
	if(this.substr(0,s.length)===s)
		return true;
	else
		return false;
};

$(function() {
	$('#changePswLbl').bind('click', function(){
		$('#changePswLbl').hide();
		$('#cancelPswBtn').show();
		$('#changePswBtn').show();
		$('#changePswIn1').show();
		$('#changePswIn2').show();
		$('#changePswIn1').val('');
		$('#changePswIn2').val('');
	});
	$('#cancelPswBtn').bind('click', function(){
		$('#changePswLbl').show();
		$('#cancelPswBtn').hide();
		$('#changePswBtn').hide();
		$('#changePswIn1').hide();
		$('#changePswIn2').hide();
	});
	// 修改密码功能
	$('#changePswBtn').bind('click', function(){
		$('#cancelPswBtn').trigger('click');
		$.post('./s_changePsw', {'oldPsw':$('#changePswIn1').val(), 'newPsw':$('#changePswIn2').val()}, function(data){
			if (data === true) {
				$.messager.show({
					title : '成功',
					msg : "密码已修改！",
					timeout : 5000,
					showType : 'slide'
				});
			} else {
				$.messager.show({
					title : '失败',
					msg : "密码验证失败！",
					timeout : 5000,
					showType : 'slide'
				});
			}
		});
	});
	$('#changePswBtn').hide();
	$('#cancelPswBtn').hide();
	$('#changePswIn1').hide();
	$('#changePswIn2').hide();
	/**
	 * 同步加载权限对应的操作tree
	 */
	(function (){
		$.ajax({
			url : './s_getAuth',
			type : 'post',
			async : false,
			data : {},
			dataType : 'json',
			success : function(data) {
				var $optTrees = $('#optTrees');
				data.indexOf('ADMIN') !== -1 && $optTrees.append($('<ul id="packageTree"></ul>'));
				data.indexOf('DEV') !== -1 && $optTrees.append($('<ul id="deployTree"></ul>'));
				data.indexOf('COMM') !== -1 && $optTrees.append($('<ul id="gmTree"></ul>'));
				data.indexOf('SUPER') !== -1 && $optTrees.append($('<ul id="adminTree"></ul>'));
			}
		});
	})();

	$('#packageTree').tree({
	    url:'getTree',
	    onClick: function(node){
	    	if (!node.state)
	    		open1(node.id);
		},
		onContextMenu: function(e,node){
            e.preventDefault();
            $(this).tree('select',node.target);
            if (node.id.startWith('_tt_') || node.id === 'com.ourpalm.editor.entity.conf') {
            	return;
            }
            $('#treeMenu').menu('show',{
                left: e.pageX,
                top: e.pageY,
                onClick: function(item){
            		if (node.state !== undefined) {
            			window.location.href="./"+node.id+"/downloadFolder";
            		} else {
            			window.location.href="./"+node.id+"/download";
            		}
            	}
            });
        }
	});
	$('#deployTree').tree({
		onClick: function(node){
			if (!node.state)
				open1(node.text);
		},
		data: [{
		        "text":"开发阶段工具",
		        "state":"closed",
		        "children":[{
		            "text":"上传TXT",
		        },{
		        	"text":"账号",
		        }]
		    }]
	});
	$('#gmTree').tree({
		onClick: function(node){
			if (!node.state)
				open1(node.text);
		},
		data: [{
		        "text":"GM工具",
		        "state":"closed",
		        "children":[{
		        	// "text":"用户浏览",
		            // "checked":true
		        // },{
		        	"text":"全服邮件",
		        },{
		        	"text":"玩家操作查询",
		        },{
		        	"text":"排行榜查询",
		        },{
		        	"text":"GM功能",
		        }]
		    }]
	});
	$('#adminTree').tree({
		onClick: function(node){
			if (!node.state)
				open1(node.text);
		},
		data: [{
		        "text":"管理账号",
		        "state":"closed",
		        "children":[{
		        	"text":"添加用户",
		        }]
		    }]
	});
	$('#tt').tabs({
		onSelect : function(table) {
			var self = $(this);
			var currTab = self.tabs('getTab', table);
			var frameUrl = getFrameUrl(table);
			self.tabs('update', {
					tab : currTab,
					options : {
					content : createFrame(frameUrl)
				}});
			function getFrameUrl(tabName) {
				switch (tabName) {
				case '上传TXT':
					return './deploy_browse';
				case '账号':
					return './dv_showFunctions';
					// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Developer
				case '用户浏览':
					return 'gm_showUserNavi';
				case '全服邮件':
					return 'gm_mails';
				case '玩家操作查询':
					return 'gm_actions';
				case '排行榜查询':
					return './gm_rankLook';	
				case 'GM功能':
					return './ggm_start';
					// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Admin
				case '添加用户':
					return './admin';
					// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Tables
				default: // 打开表格数据编辑器
					return tabName + '/open';
				}
			}
			function createFrame(url) {
				return '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
			}
		} // onSelect
	});
});
/**
 * 打开一个表
 * 
 * @param table
 *            表的数据库名
 */
function open1(table) {
	var $tt = $('#tt');
	if ($tt.tabs('exists', table)) {
		$tt.tabs('select', table);
	} else {
		$tt.tabs('add', {
			title : table,
			closable : true,
		});
	}
}