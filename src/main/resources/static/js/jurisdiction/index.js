$(function(){
    init();
});

//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/jurisdiction/initData/1",
        async: false,
        type: 'post',
        data:{"seach":""},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.name+"</td>"
                	+"<td style=\"text-align: center;display:none;\">"+e.parents+"</td>"
                	+"<td style=\"text-align: center;\">"+e.identification+"</td>"
                	+"<td style=\"text-align: center;display:none;\">"+(e.jurType == 1 ? "左侧菜单" : "其它")+"</td>"
                	+"<td style=\"text-align: center;display:none;\">"+(e.sysType == "superAdmin" ? "超级" : (e.sysType == "admin" ? "管理员" : "用户"))+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
//                分页
//                $('#page_last').attr("onclick","init("+req.code+")");
//                $('.pageNow').find("font").text(pageNow);
//                $('.pageTwo').remove();
//                var page_last = '';
//                if((Number(pageNow)+1) <= Number(req.code)){
//                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+1)+")\">"
//                     +"<a><font style=\"vertical-align: inherit;\">"
//                     +(Number(pageNow)+1)+"</font></a></li>";
//                }
//                if((Number(pageNow)+2) <= Number(req.code)){
//                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+2)+")\">"
//                     +"<a><font style=\"vertical-align: inherit;\">"
//                     +(Number(pageNow)+2)+"</font></a></li>";
//                }
//                if((Number(pageNow)+3) <= Number(req.code)){
//                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+3)+")\">"
//                     +"<a><font style=\"vertical-align: inherit;\">"
//                     +(Number(pageNow)+3)+"</font></a></li>";
//                }
//                $('.pageNow').after(page_last);
//
//                $('.pageOne').remove();
//                var page_first = '';
//                if((Number(pageNow)-3) > 0){
//                    page_first = "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-3)+")\">"
//                    +"<a><font style=\"vertical-align: inherit;\">"
//                    +(Number(pageNow)-3)+"</font></a></li>";
//                }
//                if((Number(pageNow)-2) > 0){
//                    page_first += "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-2)+")\">"
//                                        +"<a><font style=\"vertical-align: inherit;\">"
//                                        +(Number(pageNow)-2)+"</font></a></li>";
//                }
//                if((Number(pageNow)-1) > 0){
//                    page_first += "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-1)+")\">"
//                                        +"<a><font style=\"vertical-align: inherit;\">"
//                                        +(Number(pageNow)-1)+"</font></a></li>";
//                }
//                $('.pageNow').before(page_first);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

//    del
function del(obj){
	var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
    $.ajax({
        url: "/btsfzb/del",
        async: false,
        type: 'get',
        data:{"id":$(obj).attr('data_id')},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
            $('.htmlBody1').show();  //之前
            $('.htmlBody2').hide();  //之前
        },
        success: function (req) {
            init("1");
        },
        error: erryFunction
    });
}
}
//    error
function erryFunction() {
    alert("错误!");
};