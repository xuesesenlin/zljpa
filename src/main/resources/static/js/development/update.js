var ckeditor;
$(function(){
    ckeditor = CKEDITOR.replace('bodys');
    findOne($('input[name="uuid"]').val());
});
//获取当前修改的bean
function findOne(obj){
    $.ajax({
        url: "/development/findOne/"+obj,
        async: false,
        type: 'GET',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $('select[name="types"]').find("option").each(function(index,e){
                    if(e.value == req.data.types){
                        $(e).attr("selected","selected");
                    }
                });
                $('input[name="titles"]').val(req.data.titles);
                $('#bodys').html(req.data.bodys);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//update
function update(){
    var content = ckeditor.document.getBody().getHtml();
    $('#bodys').html(content);
    $.ajax({
        url: "/development/development/",
        async: false,
        type: 'PUT',
        data:$('#ro_form_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#development_home').click();
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