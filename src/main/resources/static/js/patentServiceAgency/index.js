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
        url: "/patentServiceAgency/patentServiceAgency/"+pageNow,
        async: false,
        type: 'GET',
        data:{"types":$('#types').val(),"titles":$('#titles').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){

                $(req.data.content).each(function(index,e){

                    var u = "";
                    if($("#ro_form_update").length > 0)
                        u = "<input type=\"button\" onclick=\"cli(this)\" data-target=\"/patentServiceAgency/update/"+e.uuid+"\" value=\"修改\"/>&nbsp;";
                    var d = "";
                    if($("#ro_form_delete").length > 0)
                        d="<input type=\"button\" onclick=\"ro_del('"+e.uuid+"')\" value=\"删除\"/>&nbsp;";

                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.titles+"</td>"
                	+"<td style=\"text-align: center;\">"
                	+d
                	+u
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
//搜索
function ro_seach(){
    init(pageN);
}
//del
function ro_del(obj){
	var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url: "/patentServiceAgency/patentServiceAgency/"+obj,
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