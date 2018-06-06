$(function(){
//日期控件
    laydate.render({
      elem: '#tjny' //指定元素
      ,type: 'month' //指定到月份
      ,format: 'yyyy-MM'//格式
    });
});
//    error
function erryFunction() {
    alert("错误!");
}
//init
function init(){
    $.ajax({
        url: path+"/monthlyReport/monthlyReport",
        async: false,
        type: 'GET',
        dataType: 'json',
        data:{"tjny":$('#tjny').val()},
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
//                拼接数据
                pjsj(req.data);
                init2();
//                alert('数据生成完成');
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//拼接数据
function pjsj(obj){
    $(obj.a).each(function(index,e){
    //                    提取申请数据
        if(e[3].search('申请') != -1){
            pjsj2($('#table_data_a_a'),e[2],e[1],e[0])
        }
         if(e[3].search('授权') != -1){
          pjsj2('#table_data_a_b',e[2],e[1],e[0])
        } if(e[3].search('有效') != -1){
          pjsj2('#table_data_a_c',e[2],e[1],e[0])
        }
    });
    $(obj.b).each(function(index,e){
        //                    提取申请数据
        if(e[3].search('申请') != -1){
            pjsj3($('#table_data_b_a'),e[2],e[1],e[0])
        }
         if(e[3].search('授权') != -1){
          pjsj3('#table_data_b_b',e[2],e[1],e[0])
        } if(e[3].search('有效') != -1){
          pjsj3('#table_data_b_c',e[2],e[1],e[0])
        }
    });
    $(obj.c).each(function(index,e){
        //                    提取申请数据
        if(e[3].search('申请') != -1){
            pjsj3($('#table_data_c_a'),e[2],e[1],e[0])
        }
         if(e[3].search('授权') != -1){
          pjsj3('#table_data_c_b',e[2],e[1],e[0])
        } if(e[3].search('有效') != -1){
          pjsj3('#table_data_c_c',e[2],e[1],e[0])
        }
    });
}
//拼接数据
function pjsj2(o1,o2,o3,o4){
    //                    提取本月数据
    if(o2.search('机关团体') != -1){
        var tdaa = $(o1).find('td:eq(12)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('工矿企业') != -1){
        var tdaa = $(o1).find('td:eq(11)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('科研单位') != -1){
        var tdaa = $(o1).find('td:eq(10)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('大专院校') != -1){
        var tdaa = $(o1).find('td:eq(9)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
//                        专利类型
    if(o3.search('外观') != -1){
        var tdaa = $(o1).find('td:eq(6)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('实用') != -1){
        var tdaa = $(o1).find('td:eq(5)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('发明') != -1){
        var tdaa = $(o1).find('td:eq(4)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }

//                        合计
    var tdaa6 = $(o1).find('td:eq(6)');
    var tdaa5 = $(o1).find('td:eq(5)');
    var tdaa4 = $(o1).find('td:eq(4)');
    var tdaa3 = $(o1).find('td:eq(3)');
    $(tdaa3).text(Number($(tdaa6).text())+Number($(tdaa5).text())+Number($(tdaa4).text()));
}
function pjsj3(o1,o2,o3,o4){
    //                    提取本月数据
    if(o2.search('机关团体') != -1){
        var tdaa = $(o1).find('td:eq(11)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('工矿企业') != -1){
        var tdaa = $(o1).find('td:eq(10)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('科研单位') != -1){
        var tdaa = $(o1).find('td:eq(9)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('大专院校') != -1){
        var tdaa = $(o1).find('td:eq(8)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
//                        专利类型
    if(o3.search('外观') != -1){
        var tdaa = $(o1).find('td:eq(5)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('实用') != -1){
        var tdaa = $(o1).find('td:eq(4)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('发明') != -1){
        var tdaa = $(o1).find('td:eq(3)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }

//                        合计
    var tdaa6 = $(o1).find('td:eq(5)');
    var tdaa5 = $(o1).find('td:eq(4)');
    var tdaa4 = $(o1).find('td:eq(3)');
    var tdaa3 = $(o1).find('td:eq(2)');
    $(tdaa3).text(Number($(tdaa6).text())+Number($(tdaa5).text())+Number($(tdaa4).text()));
}
function init2(){
    $.ajax({
        url: path+"/monthlyReport/monthlyReport2",
        async: false,
        type: 'GET',
        dataType: 'json',
        data:{"tjny":$('#tjny').val()},
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
//                拼接数据
                pjsj22(req.data);
                alert('数据生成完成');
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//拼接数据
function pjsj22(obj){
    $(obj.a).each(function(index,e){
    //                    提取申请数据
        if(e[2].search('申请') != -1){
            pjsj21($('#table_data2_a'),e[1],e[0])
        }if(e[2].search('授权') != -1){
          pjsj21('#table_data2_c',e[1],e[0])
        } if(e[2].search('有效') != -1){
          pjsj21('#table_data2_e',e[1],e[0])
        }
    });
    $(obj.b).each(function(index,e){
        //                    提取申请数据
        if(e[2].search('申请') != -1){
            pjsj21($('#table_data2_b'),e[1],e[0])
        }if(e[2].search('授权') != -1){
          pjsj21('#table_data2_d',e[1],e[0])
        } if(e[2].search('有效') != -1){
          pjsj21('#table_data2_f',e[1],e[0])
        }
    });
}
//拼接数据
function pjsj21(o1,o2,o3){
    //                    提取本月数据
    if(o2.search('长安') != -1){
        var tdaa = $(o1).find('td:eq(3)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('桥东') != -1){
        var tdaa = $(o1).find('td:eq(4)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('桥西') != -1){
        var tdaa = $(o1).find('td:eq(5)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('新华') != -1){
        var tdaa = $(o1).find('td:eq(6)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('矿区') != -1){
        var tdaa = $(o1).find('td:eq(7)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('裕华') != -1){
        var tdaa = $(o1).find('td:eq(8)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('高新') != -1){
        var tdaa = $(o1).find('td:eq(9)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('井陉县') != -1){
        var tdaa = $(o1).find('td:eq(11)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('正定县') != -1){
        var tdaa = $(o1).find('td:eq(12)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('栾城') != -1){
        var tdaa = $(o1).find('td:eq(13)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('行唐') != -1){
        var tdaa = $(o1).find('td:eq(14)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('灵寿') != -1){
        var tdaa = $(o1).find('td:eq(15)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('高邑') != -1){
        var tdaa = $(o1).find('td:eq(16)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('深泽') != -1){
         var tdaa = $(o1).find('td:eq(17)');
         $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }
    if(o2.search('赞皇') != -1){
        var tdaa = $(o1).find('td:eq(18)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('无极') != -1){
         var tdaa = $(o1).find('td:eq(19)');
         $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('平山') != -1){
          var tdaa = $(o1).find('td:eq(20)');
          $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('元氏') != -1){
           var tdaa = $(o1).find('td:eq(21)');
           $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('赵县') != -1){
        var tdaa = $(o1).find('td:eq(22)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('辛集') != -1){
         var tdaa = $(o1).find('td:eq(23)');
         $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('藁城') != -1){
          var tdaa = $(o1).find('td:eq(24)');
          $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('晋州') != -1){
       var tdaa = $(o1).find('td:eq(25)');
       $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('新乐') != -1){
        var tdaa = $(o1).find('td:eq(26)');
        $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }if(o2.search('鹿泉') != -1){
        var tdaa = $(o1).find('td:eq(27)');
         $(tdaa).text(Number($(tdaa).text())+Number(o3));
    }

//                        合计
    var a = 0,b=0;
    var f = $(o1).find('td');
    for(var ii = f.length-1;ii > 0;ii--){
        if(ii > 10){
//            县区合计
            a += Number($(f[ii]).text());
        }
        if(ii > 2 && ii < 10){
            b += Number($(f[ii]).text());
        }
    }
    $(o1).find('td:eq(10)').text(a);
    $(o1).find('td:eq(2)').text(b);
    $(o1).find('td:eq(1)').text(Number(a)+Number(b));
}
function pjsj31(o1,o2,o3,o4){
    //                    提取本月数据
    if(o2.search('机关团体') != -1){
        var tdaa = $(o1).find('td:eq(11)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('工矿企业') != -1){
        var tdaa = $(o1).find('td:eq(10)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('科研单位') != -1){
        var tdaa = $(o1).find('td:eq(9)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o2.search('大专院校') != -1){
        var tdaa = $(o1).find('td:eq(8)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
//                        专利类型
    if(o3.search('外观') != -1){
        var tdaa = $(o1).find('td:eq(5)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('实用') != -1){
        var tdaa = $(o1).find('td:eq(4)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }
    if(o3.search('发明') != -1){
        var tdaa = $(o1).find('td:eq(3)');
        $(tdaa).text(Number($(tdaa).text())+Number(o4));
    }

//                        合计
    var tdaa6 = $(o1).find('td:eq(5)');
    var tdaa5 = $(o1).find('td:eq(4)');
    var tdaa4 = $(o1).find('td:eq(3)');
    var tdaa3 = $(o1).find('td:eq(2)');
    $(tdaa3).text(Number($(tdaa6).text())+Number($(tdaa5).text())+Number($(tdaa4).text()));
}
//导出类型
function exp_lx(){
    var form = $("<form></form>").attr("action", path+"/monthlyReport/monthlyReport/export/1").attr("method", "get");
            form.append($("<input></input>").attr("type", "hidden").attr("name", "data").attr("value", exp_lx2()));
            form.appendTo('#dow').submit().remove();
//    $.ajax({
//        url: path+"/monthlyReport/monthlyReport/exp",
//        async: false,
//        type: 'POST',
//        dataType: 'json',
//        data:{"data":exp_lx2()},
//        timeout: 1000,
//        cache: false,
//        beforeSend:function(XMLHttpRequest){
//        },
//        success: function (req) {
//            if(req.success){
////                拼接数据
//                alert('数据生成完成');
//            }else{
//                alert(req.message);
//            }
//        },
//        error: erryFunction
//    });
}
function exp_lx2(){
    //申请
    var a = "";
    $('#table_data').find('tr:eq(0)').find('td').each(function(index,e){
        if(index > 2)
            a += $(e).text()+",";
    });
    var b = "";
    $('#table_data').find('tr:eq(1)').find('td').each(function(index,e){
        if(index > 1)
            b += $(e).text()+",";
    });
    var c = "";
    $('#table_data').find('tr:eq(2)').find('td').each(function(index,e){
        if(index > 1)
            c += $(e).text()+",";
    });
    //批准
    var d = "";
    $('#table_data').find('tr:eq(3)').find('td').each(function(index,e){
        if(index > 2)
            d += $(e).text()+",";
    });
    var e = "";
    $('#table_data').find('tr:eq(4)').find('td').each(function(index,e){
        if(index > 1)
            e += $(e).text()+",";
    });
    var f = "";
    $('#table_data').find('tr:eq(5)').find('td').each(function(index,e){
        if(index > 1)
            f += $(e).text()+",";
    });
    //有效
    var g = "";
    $('#table_data').find('tr:eq(6)').find('td').each(function(index,e){
        if(index > 2)
            g += $(e).text()+",";
    });
    var h = "";
    $('#table_data').find('tr:eq(7)').find('td').each(function(index,e){
        if(index > 1)
            h += $(e).text()+",";
    });
    var i = "";
    $('#table_data').find('tr:eq(8)').find('td').each(function(index,e){
        if(index > 1)
            i += $(e).text()+",";
    });
    return (a == "" ? "[" : a)+"]"+(b == "" ? "[" : b)+"]"+(c == "" ? "[" : c)+"]"+(d == "" ? "[" : d)+"]"
    +(e == "" ? "[" : e)+"]"+(f == "" ? "[" : f)+"]"+(g == "" ? "[" : g)+"]"+(h == "" ? "[" : h)+"]"
    +(i == "" ? "[" : i);
}
//导出区域
function exp_qy(){
    var form = $("<form></form>").attr("action", path+"/monthlyReport/monthlyReport/export/2").attr("method", "get");
            form.append($("<input></input>").attr("type", "hidden").attr("name", "data").attr("value", exp_qy2()));
            form.appendTo('#dow').submit().remove();
}
function exp_qy2(){
    //申请
    var a = "";
    $('#table_data2').find('tr').each(function(index,e){
        var b = "";
        $(e).find('td').each(function(index1,e1){
            if(index1 > 0)
                b += $(e1).text()+",";
        });
        if(b == "")
            b = "[";
        a += b + "]";
    });
    return a;
}