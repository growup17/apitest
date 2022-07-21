<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试页面</title>
    <link rel="stylesheet" type="text/css" href="">
    <script src="/react.development.js"></script>
    <script src="/react-dom.development.js"></script>
    <script src="/jquery-3.6.0.js"></script>
</head>
<body>
<div id="divForm">

    <form id="socketTestForm">
        <div>
            <span>服务器IP：</span>
            <input type="text" name="ip" value=""/>
            <span>端口：</span>
            <input type="text" name="port" value=""/>
        </div>
        <div>
            <span>请求：</span>
            <textarea cols="80" rows="26" name="request"></textarea>
            <input type="button" value="提交" onclick="sendRequest()">
        </div>
        <div>

        </div>
        <div>
            <span>响应：</span>
            <textarea cols="80" rows="20" name="response" id="response"></textarea>
        </div>
    </form>

</div>
<script>
    function sendRequest() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/doSocket",
            data: $("#socketTestForm").serialize(),
            success: function (result) {
                $("#response").text(JSON.stringify(result.response));
            },
            error: function () {

            }
        });
    }
</script>
</body>
</html>