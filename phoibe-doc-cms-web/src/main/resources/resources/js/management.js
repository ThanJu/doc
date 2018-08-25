$(function(){
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