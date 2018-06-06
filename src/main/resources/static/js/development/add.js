var ckeditor;
$(function(){
    ckeditor = CKEDITOR.replace('bodys');
});
//add
function add(){
    var content = ckeditor.document.getBody().getHtml();
    $('#bodys').html(content);
    $.ajax({
        url: "/development/development/",
        async: false,
        type: 'POST',
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