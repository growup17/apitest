<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>socket测试</title>
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
            <span>并行数：</span>
            <input type="text" name="parallelNum" value="1"/>
        </div>
        <div>
            <span>请求：</span>
            <textarea cols="80" rows="26" name="request"></textarea>
            <input type="button" value="发送" onclick="sendRequest()">
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
        $("#response").val("");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/doSocket",
            data: $("#socketTestForm").serialize(),
            success: function (result) {
                for (let i = 0; i < result.length; i++) {
                    let item = "------结果" + i + "耗时：" + result[i].spendTime + "------\n";
                    item = item + result[i].response + "\n";
                    $("#response").val($("#response").val() + item)
                }
            },
            error: function (result) {
                $("#response").val(result);
            }
        });
    }
</script>
</body>
</html>