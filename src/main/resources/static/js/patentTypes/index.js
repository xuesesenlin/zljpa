$(function(){
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
    init();
}
//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/patentTypes/patentTypes/0",
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
                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.typesName+"</td>"
                	+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};