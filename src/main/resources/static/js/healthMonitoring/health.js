$(function(){
    init();
});
//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/health",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $('#table_data').append('<tr><td style="text-align: center;">'+(req.status == "UP" ? "正常" : "异常")+'</td>'
            +'<td style="text-align: center;">'+(req.diskSpace.status == "UP" ? "正常" : "异常")+'</td>'
            +'<td style="text-align: center;">'+req.diskSpace.total+'</td>'
            +'<td style="text-align: center;">'+req.diskSpace.free+'</td>'
            +'<td style="text-align: center;">'+req.diskSpace.threshold+'</td>'
            +'<td style="text-align: center;">'+(req.db.status == "UP" ? "正常" : "异常")+'</td>'
            +'<td style="text-align: center;">'+req.db.database+'</td>'
            +'<td style="text-align: center;">'+req.db.hello+'</td>'
            +'</tr>');
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};