var pageN;
$(function(){
//日期控件
laydate.render({
  elem: '#drnyStar' //指定元素
  ,type: 'month' //指定到月份
  ,format: 'yyyy-MM'//格式
});
laydate.render({
  elem: '#drnyEnd' //指定元素
  ,type: 'month' //指定到月份
  ,format: 'yyyy-MM'//格式
});
laydate.render({
  elem: '#sqrqStar' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});
laydate.render({
  elem: '#sqrqEnd' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});
laydate.render({
  elem: '#ajrkrStar' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});
laydate.render({
  elem: '#ajrkrEnd' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});

//获取专利类型
    getPatentTypes();
//    申请人类型
    getPatentPeoTypes();
//    获取省份
    getSSX('#szss','0');
//    init
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
//query
function query_data(){
    init("0");
}
//init
function init(pageNow){
    pageN = pageNow;
    $('#page_now').text("当前页数:"+(Number(pageN)+1));
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/patentDataQuery/patentDataQuery/"+pageNow,
        async: false,
        type: 'GET',
        dataType: 'json',
        data:$('#form_query').serialize(),
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data.content).each(function(index,e){
                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.applyAuthorization == null ? "" : e.applyAuthorization)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.applyCode+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.applyDate == "" ? "" : getSmpFormatDateByLong(e.applyDate,false))+"</td>"
                	+"<td style=\"text-align: center;\">"+e.mainTypes+"</td>"
                	+"<td style=\"text-align: center;\">"+e.zipCode+"</td>"
                	+"<td style=\"text-align: center;\" onmouseover=\"mous_tal('"+e.peoAddress+"')\" onmouseout=\"mous_tal('鼠标指向地址查看详细信息')\">"
                	+(e.peoAddress.length > 20 ? e.peoAddress.substring(0,20)+"..." : e.peoAddress)
                	+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.fileEnterDate == "" ? "" : getSmpFormatDateByLong(e.fileEnterDate,false))+"</td>"
                	+"<td style=\"text-align: center;\">"+e.patentTypes+"</td>"
                	+"<td style=\"text-align: center;\">"+e.appPeoTypes+"</td>"
                	+"<td style=\"text-align: center;\">"+e.province+"</td>"
                	+"<td style=\"text-align: center;\">"+e.city+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.area == "" ? "未识别" : e.area)+"</td>"
                	+"<td style=\"text-align: center;\">"+(e.impDate == "" ? "" : getSmpFormatDateByLong(e.impDate,false))+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
//                分页
                pages(req.data);
            }else{
                alert(req.message);
                //    总页数
                    $('#page_total').text("总页数页数:1");
                //    总条数
                    $('#page_size_total').text("总条数:0");
            }
        },
        error: erryFunction
    });
}
//根据城市获取区/县
function click_getx(obj,o){
    $('#xx').find('input').remove();
    $('#x').show();
    $.ajax({
        url: "/patentDataQuery/pmc/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $(req).each(function(index,e){
                $('#xx').append("<input class=\"btn btn-outline btn-primary btn-xs\" type=\"button\" value=\""+e.names+"\" onclick=\"click_o('"+o+"','"+e.names+"')\"/>");
            });
        },
        error: erryFunction
    });
}
//鼠标悬停
function mous_tal(a) {
    $('#td_text').text(a);
}
//获取专利类型
function getPatentTypes(){
    $.ajax({
        url: "/patentDataQuery/patentTypes/",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#zllx').append("<option value=\""+e.typesName+"\">"+e.typesName+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//获取专利人类型
function getPatentPeoTypes(){
    $.ajax({
        url: "/patentDataQuery/applyPeoTypes/",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#sqrlx').append("<option value=\""+e.typesName+"\">"+e.typesName+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//获取省市县-联动
function getSSXLD(a,obj){
    $(a).find('.ld').remove();
    if(a == '#szsc')
        $('#szsx').find('.ld').remove();
    if(obj != ''){
        getSSX(a,$(obj).val());
    }
}
//获取省市县
function getSSX(a,obj){
    $.ajax({
        url: "/patentDataQuery/pmc/ssx/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $(a).append("<option class='ld' value=\""+e.names+"\">"+e.names+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//导出查询结果
function export_result_data(){
    $('#form_query').attr("action",path+"/patentDataQuery/export");
    $('#form_query').attr("method","post");
    $('#form_query').submit();
    $('#form_query').attr("action","#");
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
//    总页数
    $('#page_total').text("总页数页数:"+obj.totalPages);
//    总条数
    $('#page_size_total').text("总条数:"+obj.totalElements);
}
//    error
function erryFunction() {
    alert("错误!");
};