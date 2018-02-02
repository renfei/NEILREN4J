<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>账户管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/easyui/js/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height: 35px;">
    <div id="DIV_toolbar" Style="margin:0px; padding:5px">
        相册：
        <select id="cc" class="easyui-combobox" name="typeId" style="width:100px;" editable="false"
                data-options="required:true,valueField:'id',textField:'title',url:'/Image/getAllAlbum',onSelect:onSelect">
        </select>
        上传：
        <a class="easyui-linkbutton" onclick="$('#w').window('open');">上传</a>
    </div>
</div>
<div data-options="region:'center'">
    <table id="dg" title="账户管理"
           data-options="rownumbers:true,singleSelect:true,fit:true,pagination:true,url:'/console/image/getimages',method:'get',toolbar:toolbar">
        <thead>
        <tr>
            <th data-options="field:'id'">ID</th>
            <th data-options="field:'url'">链接</th>
            <th data-options="field:'url2'" formatter="formatShow">预览</th>
            <th data-options="field:'albumid'" formatter="formatAlbum">相册</th>
            <th data-options="field:'title'">标题</th>
        </tr>
        </thead>
    </table>
    <div id="w" class="easyui-window" data-options="title:'账户管理',inline:true,closed:true"
         style="width:50%;height:300px;padding:10px">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'" style="padding:10px;">
                <form id="upload" method="post" action="/console/image/uploadimage" enctype="multipart/form-data">
                    <div id="drop">
                        Drop Here

                        <a>Browse</a>
                        <input type="file" name="upl" multiple/>
                        <input type="hidden" id="albumid" name="albumid" value="">
                    </div>

                    <ul>
                        <!-- The file uploads will be shown here -->
                    </ul>

                </form>


                <!-- JavaScript Includes -->
                <script src="/static/js/jquery.knob.js"></script>

                <!-- jQuery File Upload Dependencies -->
                <script src="/static/js/jquery.ui.widget.js"></script>
                <script src="/static/js/jquery.iframe-transport.js"></script>
                <script src="/static/js/jquery.fileupload.js"></script>
                <!-- Our main JS file -->
                <script src="/static/js/upload.js"></script>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:submit()"
                   style="width:80px">确认</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                   href="javascript:$('#w').window('close')" style="width:80px">关闭</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var toolbar = [];
    $(function () {
        var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
        pager.pagination({
            buttons: []
        });
        $("#dg").datagrid({
            //双击事件
            onDblClickRow: function (index, row) {
                $("#id").val(row.id);
                $("#username").textbox("setValue", row.username);
                $("#username").textbox('textbox').attr('readonly', true);
                $("#passwd").textbox("setValue", "");
                $("#safepasswd").textbox("setValue", "");
                $("#state").combobox('select', row.state);
                $("#storeid").combobox('select', row.storeid)
                $('#w').window('open');
            }
        });
    });

    function formatShow(val, row) {
        return "<img src='//" + row.url + "?x-oss-process=style/50-50'>";
    }

    function onSelect(record) {
        $('#dg').datagrid({
            queryParams: {
                id: record.id
            }
        });
        $("#albumid").val(record.id);
    }

    function formatAlbum(val, row) {
        var obj;
        $.ajax({
            type: "POST",
            url: "/console/image/getalbumbyid",
            data: {
                id: row.albumid
            },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.state != 200) {
                    alert(data.message);
                    return;
                } else {
                    //
                    obj = data.object;
                }
            }
        });
        return obj.title;
    }

    function submit() {
        //$("#labusername").html(row.username);
//        $("#labusername").show();
//        $("#username").textbox("setValue", row.username);
//        $("#username").textbox('textbox').attr('readonly',true);
//        $("#passwd").textbox("setValue", "");
//        $("#safepasswd").textbox("setValue", "");
//        $("#state").combobox('select',row.state);
//        $("#storeid").combobox('select',row.storeid)
        if ($("#id").val() == "") {
            if ($("#passwd").textbox("getValue") == "") {
                alert("密码未填写");
                return false;
            }
        }
        if ($("#username").textbox("getValue") == "") {
            alert("用户名未填写");
            return false;
        } else if ($("#storeid").combobox("getValue") == "") {
            alert("店铺未选择");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/sys/access",
            data: {
                id: $("#id").val(),
                username: $("#username").textbox("getValue"),
                passwd: $("#passwd").textbox("getValue"),
                safepasswd: $("#safepasswd").textbox("getValue"),
                state: $("#state").val(),
                storeid: $("#storeid").combobox("getValue")
            },
            dataType: "json",
            success: function (data) {
                if (data.state != 200) {
                    alert(data.message);
                } else {
                    //
                    $("#dg").datagrid('reload');
                    $('#w').window('close');
                }
            }
        });
    }
</script>
</body>
</html>