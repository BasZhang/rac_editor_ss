
var window_TAB = "  ";

function checkIsArray(obj) {
	return obj && typeof obj === 'object' && typeof obj.length === 'number'
			&& !(obj.propertyIsEnumerable('length'));
}

function formatJson(json) {
	var hformatJSON;
	try {
		if (json == "")
			json = "\"\"";
		var obj = eval("[" + json + "]");
		var html = processObject(obj[0], 0, false, false, false);
		return "<pre style='font-family:\"Lucida Console\",\"Monaco\",\"Courier\",\"mono\",\"monospace\"; font-size: 120%; line-height: 100%'>" + html + "</pre>";
	} catch (e) {
		return json;
	}
}
function processObject(obj, indent, addComma, isArray, isPropertyContent) {
	var html = "";
	var comma = (addComma) ? "<span>,</span> " : "";
	var type = typeof obj;

	if (checkIsArray(obj)) {
		if (obj.length == 0) {
			html += getRow(indent, "<span>[ ]</span>"
					+ comma, isPropertyContent);
		} else {
			html += getRow(indent, "<span>[</span>",
					isPropertyContent);
			for ( var i = 0; i < obj.length; i++) {
				html += processObject(obj[i], indent + 1, i < (obj.length - 1),
						true, false);
			}
			html += getRow(indent, "<span>]</span>" + comma);
		}
	} else if (type == 'object' && obj == null) {
		html += formatLiteral("null", "", comma, indent, isArray, "Null");
	} else if (type == 'object') {
		var numProps = 0;
		for ( var prop in obj)
			numProps++;
		if (numProps == 0) {
			html += getRow(indent, "<span>{ }</span>"
					+ comma, isPropertyContent);
		} else {
			html += getRow(indent, "<span>{</span>",
					isPropertyContent);
			var j = 0;
			for ( var prop in obj) {
				html += getRow(indent + 1, "<span>"
						+ prop
						+ "</span>: "
						+ processObject(obj[prop], indent + 1, ++j < numProps,
								false, true));
			}
			html += getRow(indent, "<span>}</span>" + comma);
		}
	} else if (type == 'number') {
		html += formatLiteral(obj, "", comma, indent, isArray, "Number");
	} else if (type == 'boolean') {
		html += formatLiteral(obj, "", comma, indent, isArray, "Boolean");
	} else if (type == 'function') {
		obj = formatLiteral(indent, obj);
		html += formatLiteral(obj, "", comma, indent, isArray, "Function");
	} else if (type == 'undefined') {
		html += formatLiteral("undefined", "", comma, indent, isArray, "Null");
	} else {
		html += formatLiteral(obj, "\"", comma, indent, isArray, "String");
	}
	return html;
}
function formatLiteral(literal, quote, comma, indent, isArray, style) {
	if (typeof literal == 'string')
		literal = literal.split("<").join("&lt;").split(">").join("&gt;");
	var str = "<span class='" + style + "'>" + quote + literal + quote + comma
			+ "</span>";
	if (isArray)
		str = getRow(indent, str);
	return str;
}
function formatFunction(indent, obj) {
	var tabs = "";
	for ( var i = 0; i < indent; i++)
		tabs += window_TAB;
	var funcStrArray = obj.toString().split("\n");
	var str = "";
	for ( var i = 0; i < funcStrArray.length; i++) {
		str += ((i == 0) ? "" : tabs) + funcStrArray[i] + "\n";
	}
	return str;
}
function getRow(indent, data, isPropertyContent) {
	var tabs = "";
	for ( var i = 0; i < indent && !isPropertyContent; i++)
		tabs += window_TAB;
	if (data != null && data.length > 0 && data.charAt(data.length - 1) != "\n")
		data = data + "\n";
	return tabs + data;
}