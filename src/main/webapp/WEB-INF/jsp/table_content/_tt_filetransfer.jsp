<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="dg" title="数据表格" style="width:1300px;height:628px" toolbar="#toolbar" pagination="true" idField="code" rownumbers="true" fitColumns="true" singleSelect="true" pageSize="20">
	<thead>
		<tr>
			<th field="${columns[0].name}" width="50">${columns[0].comment}</th>
			<th data-options="field:'${columns[1].name}',width:50,
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'0',
                                textField:'1',
                                url:'../getTables',
                                required : true
                            }
                        }">${columns[1].comment}</th>
			<th field="${columns[2].name}" width="50" editor="{type:'text',options:{required:false}}">${columns[2].comment}</th>
		</tr>
	</thead>
</table>