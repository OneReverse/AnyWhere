/**
 * Created by 怡轩 on 2015/7/7.
 */
jQuery.noConflict();
jQuery(function($){
    $("article").css("color", "yellow");
    $("li").each(function(){$(this).append("_suffix")});
    $("article").each(function(){ if($(this).is("p")) $(this).css("color", "red") });
    var link = $("a").attr("href");
    $("a").attr("target", "_blank");
    var className = $(".rose");
    //alert(className.attr("id").toString());
    //$(".indexButton").bind("click", function() {
    //    alert($("#index").val());
    //    window.location.href="http://www.zhanqi.tv/laoshusjq?id="+$("#index").val().toString()+"&tag_id=10&forum_id=&bf_ref_url=baidutieba"
    //})
    $(".indexButton").click(watchMouse);
    $("#collect").bind({click : collectTime, onclick : transfer});
    $(":checkbox").click(showValueOfCheckBox);

    function watchMouse() {
        alert($("#index").val());
        window.location.href="http://www.zhanqi.tv/laoshusjq?id="+$("#index").val().toString()+"&tag_id=10&forum_id=&bf_ref_url=baidutieba"
    }

    function collectTime() {
        var time = {
            year : $("#year").val(),
            month : $("#month").val(),
            day : $("#day").val()
        }
        alert(time.year + "-" + time["month"] + "-" + time.day);
        $("#collect").trigger("onclick");
    }

    function showValueOfCheckBox () {
        alert("checkbox :" + $(":checkbox").attr("value"));
    };

    function transfer() {
        $("#a1").load("https://www.baidu.com/index.php?tn=monline_5_dg");
    }
});

