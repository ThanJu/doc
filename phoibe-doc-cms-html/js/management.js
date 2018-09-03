
$(function () {
	
	var baseUrl = "http://47.93.62.169:8090";//var baseUrl = "http://127.0.0.1:8090";;
    authLogin();

   $(function(){
       //左侧页面导航切换
       $('.main .navLeft li').on('click',function(){
           $(this).addClass('active').siblings('.navList').removeClass('active')
           var taggle = $(this).attr('data-toggle');
           $("#frm-main").attr("src",taggle);
           //var dataToggle='page'+;
           //$('.main .container .page').removeClass('show')
          // $('.main .container').find('.'+dataToggle).addClass('show')
       });
       //多选框
       $('li.checkBox').on('click', function () {

           $(".checkList").each(function () {
               $(this).find("li").each(function (i) {
                   $(this).removeClass("check");
               });
           });
           $(this).toggleClass('check')
           $("li.checkBox").removeAttr('checked');
           $(this).attr('checked', 'true');
       })
   })
})


function getUrlString(name) {

    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]); return null;
};


function authLogin() {
    if (getCookie("username") == null || getCookie("username") == "") {
        window.location.href = 'login.html';
    }
}

function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//读取cookies 
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies 
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function authExit() {
    //delCookie("username");
    //alert(getCookie("username"));
    //window.location.href = 'login.html';
}
function cutString(str, len) {

    //length属性读出来的汉字长度为1 

    if (str.length * 2 <= len) {

        return str;

    }

    var strlen = 0;

    var s = "";

    for (var i = 0; i < str.length; i++) {

        s = s + str.charAt(i);

        if (str.charCodeAt(i) > 128) {

            strlen = strlen + 2;

            if (strlen >= len) {

                return s.substring(0, s.length - 1) + "...";

            }

        } else {

            strlen = strlen + 1;

            if (strlen >= len) {

                return s.substring(0, s.length - 2) + "...";

            }

        }
    }
    return s;
}
