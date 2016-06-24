/**
 * 刷新页面文件树
 * @param channel 渠道名
 */
function refreshPage(channel) {
	var treeOpts;
	var $browseTree = $('#browseTree');
	try {
		$browseTree.treegrid('options');
	} catch (e) { // 生成tree
		(function() {
			var $treeMenu = $('#resUploadMenu');
			$browseTree.treegrid({
				height: 580,
		        method: 'post',
		        rownumbers: false,
		        idField: 'id',
		        treeField: 'name',
			    columns:[[
			        {title:'文件',field:'name',width:900},
			        {title:'大小',field:'size',width:130,align:'right'},
			        {title:'更改日期',field:'date',width:130,align:'right'}
			    ]],
			    onContextMenu: function(e, row) {
					e.preventDefault();
					$(this).treegrid('select', row.id);
					var node = $(this).treegrid('getSelected');
					var selectedFilePath = joinParents(node);
					var itemEl = $treeMenu.menu('findItem', '下载');
					if (!itemEl) {
						var downInMenu = {
							text: '下载',
							onclick: function(){
								var crtChannel = $browseTree.treegrid('options').crtChannel;
								window.location.href="./deploy_download?channelName=" + crtChannel + "&filePath="+selectedFilePath;
							}
						};
						$treeMenu.menu('appendItem', downInMenu);
					}
					itemEl = $treeMenu.menu('findItem', '删除');
					if (!itemEl) {
						var downInMenu = {
							text: '删除',
							onclick: function(){
								var crtChannel = $browseTree.treegrid('options').crtChannel;
								$.post('./deploy_delete', {channelName: crtChannel, filePath: selectedFilePath}, function(data){
									if (data.successed) {
										$.messager.show({
											title : '成功',
											msg : data.msg,
											timeout : 5000,
											showType : 'slide'
										});
										refreshPage(crtChannel);
									} else {
										$.messager.show({
											title : '错误',
											msg : data.msg,
											timeout : 5000,
											showType : 'slide'
										});
									}
								});
							}
						};
						$treeMenu.menu('appendItem', downInMenu);
					}
					$treeMenu.menu('show',{
		                left: e.pageX,
		                top: e.pageY
		            });
		            e.stopPropagation();
		            /**
		             * 将node表示的文件名连接上父文件夹
		             */
		            function joinParents(node) {
		            	var name = node.name;
						if(node._parentId) {
							var parent = $browseTree.treegrid('getParent', node.id);
							name = joinParents(parent) + '/' + node.name;
						}
						return name;
		            }
		            
				},
			    crtChannel: undefined, // 当前选定的渠道
			});
			$treeMenu.menu({
				onHide: function(){
					var itemEl = $treeMenu.menu('findItem', '下载');
					if (itemEl) {
						$treeMenu.menu('removeItem', itemEl.target);
					}
					itemEl = $treeMenu.menu('findItem', '删除');
					if (itemEl) {
						$treeMenu.menu('removeItem', itemEl.target);
					}
				}
			});
			var uploadInMenu = {
		            text: '上传',
	                onclick: function(item){
	                	var crtChannel = $browseTree.treegrid('options').crtChannel; // 注：方法的参数channel只是第一次生成treegrid时的渠道名，这里不能用那个。
	                	uploadFile(crtChannel);
	                }
				};
			$treeMenu.menu('appendItem', uploadInMenu);
			$('.datagrid-body')//想不到怎么取到这个$('#browseTree')对应的treegrid表现体，只能用这个class来了。
				.bind('contextmenu',function(e){
					e.preventDefault();
					$treeMenu.menu('show',{
						left: e.pageX,
						top: e.pageY,
					});
		            e.stopPropagation();
				});
		})();
	}
	var treeOpts = $browseTree.treegrid('options');
	treeOpts.url = './' + channel + '/deploy_filetree';
	treeOpts.crtChannel = channel;
	$browseTree.treegrid('reload');
}

/**
 * 发出渠道资源同步命令
 */
function pushChannel() {
	try {
		var channel = $('#browseTree').treegrid('options').crtChannel;
		$.post('./' + channel + '/deploy_version', {}, function(data){
			if (data.version === null) {
				$.messager.show({
					title : '错误',
					msg : "不能获取渠道资源版本！",
					timeout : 5000,
					showType : 'slide'
				});
				return;
			}
			$.messager.confirm('Confirm','渠道：' + channel + '，资源版本：' + data.version + '，确认同步?',function(r){
				if (r){
					$.post('./deploy_pushSync', {
						channelName : channel
					}, function(resp) {
						if (resp.successed) {
							$.messager.show( {
								title : '同步成功',
								msg : "同步命令已发出！",
								timeout : 5000,
								showType : 'slide'
							});
						} else {
							$.messager.show( {
								title : '同步失败',
								msg : resp.msg,
								timeout : 5000,
								showType : 'slide'
							});
						}
					});
				}
			});
			
		});
	} catch (e) {
		$.messager.show({
			title : '错误',
			msg : "渠道无效！",
			timeout : 5000,
			showType : 'slide'
		});
	}
}

function uploadFile(channel) {
	var $a = $('<input type="file" name="file">');
	var $b = $('<input type="text" name="channelName"></input>').val(channel);
	var $form = $('<form>').append($a).append($b);
	$a.bind('change', function(){
		$mask = $("<div class=\"datagrid-mask\"></div>")
			.css({display:"block",width:"100%",height:$(window).height()})
			.appendTo("body");
		$pending = $("<div class=\"datagrid-mask-msg\"></div>")
			.html("Processing, please wait ...")
			.css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2})
			.appendTo("body");
		var formdata = new FormData($form[0]);
		$.ajax({
		    type : 'POST',
		    url : './deploy_upload',
		    data : formdata,
		    processData : false,
		    contentType : false,
		    success : function(r) {
		        if (r.successed) {
		        	$.messager.show({
		    			title : '上传成功',
		    			msg : r.msg,
		    			timeout : 5000,
		    			showType : 'slide'
		    		});
		        	refreshPage(channel);
		        } else {
		        	$.messager.show({
		    			title : '错误',
		    			msg : r.msg,
		    			timeout : 5000,
		    			showType : 'slide'
		    		});
		        }
		        $mask.remove();
		        $pending.remove();
		    },
		    error : function(r) {
		    	alert('jQuery Error');
		        $mask.remove();
		        $pending.remove();
		    }
		});
		$form.remove();
	});
	$a.trigger('click');
}