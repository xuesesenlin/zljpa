var pageN;
$(function(){
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
        url: path+"/patentDataLocking/patentDataLocking/"+pageNow,
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
                $(req.data).each(function(index,e){
                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+getFormatDate(new Date(e[0]), "yyyyMM")+"</td>"
                	+"<td style=\"text-align: center;\">"+e[1]+"</td>"
                	+"<td style=\"text-align: center;\">"
                	+"<input class=\"btn btn-outline btn-danger btn-xs\" type=\"button\" onclick=\"ro_jssd('"+e[0]+"',2)\" value=\"解锁\"/>"
                	+"<input class=\"btn btn-outline btn-primary btn-xs\" type=\"button\" onclick=\"ro_jssd('"+e[0]+"',1)\" value=\"锁定\"/>"
                	+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
//                分页
                pages();
            }else{
                alert(req.message);
                //    总页数
//                    $('#page_total').text("总页数页数:1");
                //    总条数
//                    $('#page_size_total').text("总条数:0");
            }
        },
        error: erryFunction
    });
}
//锁定1,jiesuo 2
function ro_jssd(obj,obj2){
    $.ajax({
        url: path+"/patentDataLocking/patentDataLocking/sd/"+obj2+"/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                alert(req.message);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
//分页
function pages(){
//    如果当前页是第一页则隐藏跳转至第一页
    if(pageN == 0)
        $('#page_first').hide();
    else
        $('#page_first').show();
//    如果当前页是最后一页则隐藏跳转至最后一页
//    if(obj.last)
//        $('#page_last').hide();
//    else
//        $('#page_last').show();
////    总页数
//    $('#page_total').text("总页数页数:"+obj.totalPages);
////    总条数
//    $('#page_size_total').text("总条数:"+obj.totalElements);
}
//    error
function erryFunction() {
    alert("错误!");
};