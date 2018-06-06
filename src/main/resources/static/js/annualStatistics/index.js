$(function(){
//日期控件
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
//获取专利类型
    getPatentTypes();
//    申请人类型
    getPatentPeoTypes();
//    获取省份
    getSSX('#szss','0');
getCompanyTypes();
});
//获取企业类型
function getCompanyTypes(){
    $.ajax({
        url: path+"/companyTypes/companyTypes/",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){},
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#qylx').append("<option value=\""+e.companyTypes+"\">"+e.companyTypes+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//init
function query_data(){
    $('.data_table_th_a').remove();
    $('.data_table_th_b').remove();
    $.ajax({
        url: path+"/annualStatistics/annualStatistics",
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
                $(req.data).each(function (index,e){
                    if(e.applyAuthorization.search('申请') != -1){
                        var a = 0;
                        $('#data_table_th_a').find('th').each(function(index1,e1){
                            if($(e1).text() == getFormatDate(new Date(e.impDate), "yyyy-MM")){
                                a = 1;
                                $('#data_table_th_b').find('td').eq(index1).text(Number($('#data_table_th_b').find('td').eq(index1).text()) + Number(1));
                            }
                        });
                        if(a == 0){
                            $('#data_table_th_a').append('<th class="data_table_th_a" style="text-align: center;">'+getFormatDate(new Date(e.impDate), "yyyy-MM")+'</th>');
                            $('#data_table_th_b').append('<td class="data_table_th_b" style="text-align: center;">1</td>');
                        }
                    }

                    if(e.applyAuthorization.search('授权') != -1){
                        var a = 0;
                        $('#data_table_th_c').find('th').each(function(index1,e1){
                            if($(e1).text() == getFormatDate(new Date(e.impDate), "yyyy-MM")){
                                a = 1;
                                $('#data_table_th_d').find('td').eq(index1).text(Number($('#data_table_th_d').find('td').eq(index1).text()) + Number(1));
                            }
                        });
                        if(a == 0){
                            $('#data_table_th_c').append('<th class="data_table_th_a" style="text-align: center;">'+getFormatDate(new Date(e.impDate), "yyyy-MM")+'</th>');
                            $('#data_table_th_d').append('<td class="data_table_th_b" style="text-align: center;">1</td>');
                        }
                    }

                    if(e.applyAuthorization.search('有效') != -1){
                        var a = 0;
                        $('#data_table_th_e').find('th').each(function(index1,e1){
                            if($(e1).text() == getFormatDate(new Date(e.impDate), "yyyy-MM")){
                                a = 1;
                                $('#data_table_th_f').find('td').eq(index1).text(Number($('#data_table_th_f').find('td').eq(index1).text()) + Number(1));
                            }
                        });
                        if(a == 0){
                            $('#data_table_th_e').append('<th class="data_table_th_a" style="text-align: center;">'+getFormatDate(new Date(e.impDate), "yyyy-MM")+'</th>');
                            $('#data_table_th_f').append('<td class="data_table_th_b" style="text-align: center;">1</td>');
                        }
                    }
                });
                alert('数据生成完成');
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//导出查询结果
function export_result_data(){
//    拼接数据
    $('#ann_data').val(pjsj());
    $('#form_exp').submit();
}
//拼接数据
function pjsj(){
//第一个表
    var a = "";
    $('#data_table_th_a').find('th').each(function(index,e){
        a += $(e).text()+":"+$('#data_table_th_b').find('td').eq(index).text()+",";
    });
    a == "" ? "[" : a;
//第二个表
    var b = "";
    $('#data_table_th_c').find('th').each(function(index,e){
        b += $(e).text()+":"+$('#data_table_th_d').find('td').eq(index).text()+",";
    });
    b == "" ? "[" : b;
//第三个表
    var c = "";
    $('#data_table_th_e').find('th').each(function(index,e){
        c += $(e).text()+":"+$('#data_table_th_f').find('td').eq(index).text()+",";
    });
    c == "" ? "[" : c;

    return a+"]"+b+"]"+c;
}
//获取专利类型
function getPatentTypes(){
    $.ajax({
        url: path+"/patentDataPut/patentTypes/",
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
        url: path+"/patentDataPut/applyPeoTypes/",
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
        url: path+"/patentDataPut/pmc/ssx/"+obj,
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
//    error
function erryFunction() {
    alert("错误!");
};