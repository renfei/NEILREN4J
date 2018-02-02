<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>控制台 - NEILREN.COM</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/easyui/js/jquery.easyui.min.js"></script>
</head>
<body id="body" class="easyui-layout">
<!--头部-->
<div data-options="region:'north',border:false" style="height:60px;background:#202123;padding:10px;color: #FFF;">
    <img src="/static/img/logo.jpg" height="40">
</div>
<!--左侧-->
<div data-options="region:'west',split:true,collapsed:true,
				hideExpandTool: true,
				expandMode: null,
				hideCollapsedContent: false,
				collapsedSize: 68,
				collapsedContent: function(){
					return $('#titlebar');
				}
				" title="菜单" style="width:20%;">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <ul id="tree" class="easyui-tree">
            <li>
                <span>NEILREN.COM站点控制台</span>
                <ul>
                    <li>
                        <span>相册管理</span>
                        <ul>
                            <li>相册管理</li>
                            <li>影像管理</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!--中心-->
<div id="center" class="easyui-tabs" data-options="region:'center',tools:'#tab-tools'">
    <div title="首页" data-options="tools:'#p-tools'" style="padding:10px">
    </div>
</div>
<!--右侧-->
<div data-options="region:'east',split:true,title:'快捷操作'" style="width:250px;padding:10px;">

</div>
<!--底部-->
<div data-options="region:'south',border:false"
     style="height:20px;background:#f5f5f5;padding-left: 20px;line-height: 20px;">
    2018 &copy; NEILREN.COM
</div>

<div id="titlebar" style="padding:2px">
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%"
       data-options="iconCls:'layout-button-right'" onclick="$('#body').layout('expand','west')"></a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%"
       data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">XXX</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%"
       data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">XXX</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%"
       data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">XXXX</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%"
       data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">XXXX</a>
</div>
<div id="tab-tools">
<#--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"-->
       <#--onclick="addPanel()"></a>-->
    <#--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"-->
       <#--onclick="removePanel()"></a>-->
</div>
<div id="p-tools">
    <a href="javascript:void(0)" class="icon-mini-add" onclick="alert('add')"></a>
    <a href="javascript:void(0)" class="icon-mini-edit" onclick="alert('edit')"></a>
    <a href="javascript:void(0)" class="icon-mini-refresh" onclick="alert('refresh')"></a>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div id="mm-refresh" name="1">刷新</div>
    <div id="mm-tabclose" data-options="name:2">关闭</div>
    <div id="mm-tabcloseall" data-options="name:3">全部关闭</div>
    <div id="mm-tabcloseother" data-options="name:4">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" data-options="name:5">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft" data-options="name:6">当前页左侧全部关闭</div>

</div>
<script type="text/javascript">

    function Clear() {
        $("#form").show();
    }

    function addTab(title, url) {
        if ($('#center').tabs('exists', title)) {
            $('#center').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
            $('#center').tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
    }

    //添加Tabs
    function addTabs(event, treeId, treeNode, clickFlag) {
        if (!$("#center").tabs('exists', treeNode.name)) {
            $('#center').tabs('add', {
                id: treeId,
                title: treeNode.name,
                selected: true,
                href: treeNode.dataurl,
                closable: true
            });
        } else $('#center').tabs('select', treeNode.name);
    }

    //删除Tabs
    function closeTab(menu, type) {
        var allTabs = $("#center").tabs('tabs');
        var allTabtitle = [];
        $.each(allTabs, function (i, n) {
            var opt = $(n).panel('options');
            if (opt.closable)
                allTabtitle.push(opt.title);
        });
        var curTabTitle = $(menu).data("tabTitle");
        var curTabIndex = $("#center").tabs("getTabIndex", $("#center").tabs("getTab", curTabTitle));
        switch (type) {
            case 1: //刷新
                var panel = $("#center").tabs("getTab", curTabTitle).panel("refresh");
                break;
            case 2://关闭当前
                $("#center").tabs("close", curTabIndex);
                return false;
                break;
            case 3://全部关闭
                for (var i = 0; i < allTabtitle.length; i++) {
                    $('#center').tabs('close', allTabtitle[i]);
                }
                break;
            case 4://除此之外全部关闭
                for (var i = 0; i < allTabtitle.length; i++) {
                    if (curTabTitle != allTabtitle[i])
                        $('#center').tabs('close', allTabtitle[i]);
                }
                $('#center').tabs('select', curTabTitle);
                break;
            case 5://当前侧面右边
                for (var i = curTabIndex; i < allTabtitle.length; i++) {
                    $('#center').tabs('close', allTabtitle[i]);
                }
                $('#center').tabs('select', curTabTitle);
                break;
            case 6: //当前侧面左边
                for (var i = 0; i < curTabIndex - 1; i++) {
                    $('#center').tabs('close', allTabtitle[i]);
                }
                $('#center').tabs('select', curTabTitle);
                break;
        }

    }

    $(document).ready(function () {
        //监听右键事件，创建右键菜单
        $('#center').tabs({
            onContextMenu: function (e, title, index) {
                e.preventDefault();
                if (index > 0) {
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    }).data("tabTitle", title);
                }
            }
        });
        //右键菜单click
        $("#mm").menu({
            onClick: function (item) {
                closeTab(this, item.name);
            }
        });
        //tree点击事件
        $("#tree").tree({
            onClick: function (node) {
                switch (node.text) {
                    case "相册管理":
                        addTab('相册管理', '/console/image/album');
                        break;
                    case "影像管理":
                        addTab('影像管理', '/console/image/image');
                        break;
                }
            }
        });
    });

    var index = 0;

    function addPanel() {
        index++;
        $('#center').tabs('add', {
            title: 'Tab' + index,
            content: '<div style="padding:10px">Content' + index + '</div>',
            closable: true
        });
    }

    function removePanel() {
        var tab = $('#center').tabs('getSelected');
        if (tab) {
            var index = $('#center').tabs('getTabIndex', tab);
            $('#center').tabs('close', index);
        }
    }
</script>
</body>
</html>