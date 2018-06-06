var websocket = null;
var paths;
//
function websockets(path){
    paths = path;
    //判断当前浏览器是否支持WebSocket
    if('WebSocket' in window){
        var sessionId = getCookie("tokenu");
        websocket = new WebSocket(paths+"/websocket/"+sessionId+"/guanli1");
        //连接发生错误的回调方法
        websocket.onerror = function(){
            setMessageInnerHTML("消息链接错误");
        };
        //连接成功建立的回调方法
        websocket.onopen = function(event){
//            setMessageInnerHTML("消息链接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function(event){
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function(){
            setMessageInnerHTML("消息链接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        }

    }else
        alert('您的浏览器不支持websocket')
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//发送消息
function send(){
    var message = document.getElementById('to_message').value;
    websocket.send(message);
    $('#to_message').val(null);
}

function getCookie(c_name){
if (document.cookie.length>0){
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1){
    c_start=c_start + c_name.length+1
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    }
  }
return ""
}
