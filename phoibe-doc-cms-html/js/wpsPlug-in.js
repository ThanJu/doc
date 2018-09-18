/**
 * 
 */
// var DocFrame = null;
var DocFrame;
// 初始化文档插件
function init(tagID, width, height) {
	var iframe;
	var obj;
	iframe = document.getElementById(tagID);
	var codes = selectBrowser();
	iframe.innerHTML = codes.join("");
	
	obj = document.getElementById("DocFrame1");

	// 以下方法二选一
	// 添加事件方法1
	/*
	 * var fn = function(){ function obj::OnRequireSave(){ alert("用户请求保存文档"); }
	 * 
	 * function obj::OnDocumentOpened(){ alert("文档打开"); }
	 * 
	 * function obj::OnDocumentCopy(){ alert("用户复制"); }
	 * 
	 * function obj::OnDocumentBeforePrint(){ alert("用户打印");
	 *  }
	 * 
	 * function obj::OnDocumentBeforeSave(){ alert("用户保存"); } }; fn(); //添加事件方法2
	 * if(obj.attachEvent){ console.log("attachEvent...");
	 * obj.attachEvent('OnRequireSave', function () { alert("用户请求保存文档");
	 * console.log("OnRequireSave..........222222222....."); }); }else{
	 * console.error("该版本ie不支持attachEvent事件，请设置<meta
	 * http-equiv='X-UA-Compatible' content='IE=10' />"); }
	 */
	
	return obj;
}

function InitFrame() {
	DocFrame = init("wps", "99%", "99%");
	// 需要隐藏文件的平台可以在这里调用
	// DocFrame.MenuItems &= ~MenuItems.FILE;
}
function FullScreen() {
	DocFrame.FullScreen();
}

function OpenFile(path) {

	InitFrame();
	if (DocFrame != null) {
		var temp = DocFrame.openDocumentRemote(path, false);
		if (temp) {

		} else {
			alert("文件打开失败！");
		}
		return temp;
	} else {
		alert("流式文件对象初始化失败!");
	}
}

function saveURL(path, filename) {
	/*var name=prompt("请输入要保存的名称",filename);
	if (name!=null && name!=""){
		
	}*/
	
	var ret = DocFrame.saveURL(path,filename);
	if (ret) {
		alert("文件保存成功！");
	} else {
		alert("文件保存失败！");
	}
}

function selectBrowser() {

	var codes = [];

	if (navigator.userAgent.indexOf("MSIE") > 0) {

		codes.push('<object id=DocFrame1 height=100% width=100% ');
		codes.push('data=data:application/x-oleobject;base64,7Kd9juwHQ0OBQYiirbY6XwEABAA7DwMAAgAEAB0AAAADAAQAgICAAAQABAD///8ABQBcAFgAAABLAGkAbgBnAHMAbwBmAHQAIABBAGMAdABpAHYAZQBYACAARABvAGMAdQBtAGUAbgB0ACAARgByAGEAbQBlACAAQwBvAG4AdAByAG8AbAAgADEALgAwAAAA ');
		codes.push('classid=clsid:8E7DA7EC-07EC-4343-8141-88A2ADB63A5F viewastext=VIEWASTEXT></object> ');
	}

	if (navigator.userAgent.indexOf("Chrome") > 0) {
		codes.push("<object id='DocFrame1' type='application/x-itst-activex' align='baseline' border='0'"
				+ "style='LEFT: 0px; WIDTH: 100%; TOP: 0px; HEIGHT: 100%'"
				+ "clsid='{E77E049B-23FC-4DB8-B756-60529A35FAD5}'"
				+ "</object>");
	}

	if (navigator.userAgent.indexOf("Firefox") > 0) {
		
		if(navigator.userAgent.indexOf("Firefox")>0){
			codes.push("<object id='DocFrame1' type='application/x-itst-activex' align='baseline' border='0'");
			codes.push("style='LEFT: 0px; WIDTH: 100%; TOP: 0px; HEIGHT: 100%'" );
			codes.push("clsid='{E77E049B-23FC-4DB8-B756-60529A35FAD5}'>");
			codes.push("</object>");
		}
	}
	return codes;
}
