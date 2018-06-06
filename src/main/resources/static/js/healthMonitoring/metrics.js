$(function(){
    init();
});
//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/metrics",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $('#table_data').append('<tr><td style="text-align: center;">'+req.mem+'</td>'
            +'<td style="text-align: center;">'+req.mem.free+'</td>'
            +'<td style="text-align: center;">'+req.processors+'</td>'
            +'<td style="text-align: center;">'+req.instance.uptime+'</td>'
            +'<td style="text-align: center;">'+req.uptime+'</td>'
            +'</tr>');
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};