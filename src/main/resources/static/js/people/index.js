var pageN;
$(function(){
    init("0");
});
function page(i){
    if(i == 0)
        pageN = Number(pageN)-1;
    else
        pageN = Number(pageN)+1;

    pageN = Number(pageN) < 0 ? pageN = 0 : pageN;
    init(pageN);
}
//init
function init(pageNow){
    pageN = pageNow;
    $('#page_now').text("当前页数:"+(Number(pageN)+1));
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/people/people/"+pageNow,
        async: false,
        type: 'GET',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){

                $(req.data.content).each(function(index,e){

                    var d = "";
                    if($("#ro_form_delete").length > 0)
                        d="<input class=\"btn btn-outline btn-danger btn-xs\" type=\"button\" onclick=\"ro_del('"+e.uuid+"')\" value=\"删除\"/>&nbsp;";

                    var da = new Date(e.systime);
                    var year = da.getFullYear()+'年';
                    var month = da.getMonth()+1+'月';
                    var date = da.getDate()+'日';
                    var hours = da.getHours()+'时';
                    var minutes = da.getMinutes()+'分';
                    var seconds = da.getSeconds()+'秒';

                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.account+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.types == 'user' ? '普通用户' : e.types)+"</td>"
                	+"<td style=\"text-align: center;\">"+([year,month,date,hours,minutes,seconds].join('-'))+"</td>"
                	+"<td style=\"text-align: center;\">"
                	+d
                	+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
//                分页
                pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//del
function ro_del(obj){
    $('#table_data').find('input[type=\"button\"]').each(function(index,e){
        $(e).css("background-color","");
    });
	var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url: path+"/people/people/"+obj,
            async: false,
            type: 'delete',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
            },
            success: function (req) {
                if(req.success){
                    alert(req.message);
                    init(pageN);
                }else
                    alert(req.message);
            },
            error: erryFunction
        });
    }
}
//新增
function save(){
    $.ajax({
        url: path+"/people/people",
        async: false,
        type: 'POST',
        data:$('#form_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#form_save').hide();
                init(pageN);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function pages(obj){
//    如果当前页是第一页则隐藏跳转至第一页
    if(obj.first)
        $('#page_first').hide();
    else
        $('#page_first').show();
//    如果当前页是最后一页则隐藏跳转至最后一页
    if(obj.last)
        $('#page_last').hide();
    else
        $('#page_last').show();
}
//    error
function erryFunction() {
    alert("错误!");
};