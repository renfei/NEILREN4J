<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-black">
<meta name="keywords" content="任霏,网站,相册,影像,电子相册,电子影像"/>
<meta name="description" content="任霏个人电子相册，分享任霏的个人摄影作品以及数码影像资料，影像不仅仅是光影的艺术，更是一段回忆和态度。"/>
<title>数码影像-电子相册 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
<style>
    #playerbox {
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        margin: auto;
        position: absolute;
        z-index: 99999;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        display: none;
        padding: 15px;
        min-width: 630px;
        min-height: 430px;
    }

    .playerleft {
        float: left;
        background-color: #000;
        height: 100%;
        padding: 5px;
        position: relative;
    }

    .playerleft .playerleft_list {
        height: 50px;
        position: absolute;
        bottom: 10px;
        left: 0;
        right: 0;
    }

    .playerright {
        width: 300px;
        height: 100%;
        background-color: #FFF;
        color: #8C8C8C;
        float: right;
        font-size: 12px;
    }

    .playerright a {
        color: #246AB0;
    }

    .playerright a:hover {
        text-decoration: solid;
    }

    .playertitle {
        box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
        padding: 10px;
    }

    span.FileType {
        background-color: #707070;
        color: #FFF;
        padding: 0 5px;
        float: right;
        display: inline-block;
        height: 18px;
        line-height: 18px;
    }

    .exifinfo {

    }

    .exifinfo p {
        margin: 0;
    }

    .exifinfo .exifinfo-ft {
        border-top: 1px solid #F0F0F0;
        padding-left: 10px;
    }

    .exifinfo .exifinfo-ft ul {
        padding-bottom: 3px;
        margin-right: -5px;
    }

    .exifinfo .exifinfo-ft ul li {
        float: left;
        font-weight: bold;
        width: 55px;
        height: 32px;
        line-height: 40px;
        white-space: nowrap;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: center;
    }

    .exifinfoall {
        overflow-y: auto;
        height: 100%;
    }

    .exifinfoall table {
        width: 100%;
        margin: 0 5px;
    }

    .exifinfoall table tr {
    }

    /*鼠标手势*/
    .CursorL {
        position: absolute;
        z-index: 999;
        width: 50%;
        height: 100%;
        color: #FFF;
        left: 0;
        cursor: pointer;
        background-image: url('/static/img/back2.png');
        background-position: left center;
        background-repeat: no-repeat;
    }

    .CursorL:hover {
        background-image: url('/static/img/back.png');
    }

    .CursorR {
        position: absolute;
        z-index: 999;
        width: 50%;
        height: 100%;
        color: #FFF;
        right: 0;
        cursor: pointer;
        background-image: url('/static/img/next2.png');
        background-position: right center;
        background-repeat: no-repeat;
    }

    .CursorR:hover {
        background-image: url('/static/img/next.png');
    }

    .img_box {
        width: 100%;
        height: 100%;
        text-align: center;
    }

    .img_box img {
        display: inline-block;
        height: auto;
        max-width: 100%;
        max-height: 100%;
        margin: auto;
    }

    .closebtn {
        position: absolute;
        top: 0;
        right: 0;
        width: 30px;
        height: 30px;
        background-color: #777777;
        color: #FFF;
        text-align: center;
        line-height: 27px;
        font-weight: 900;
        -webkit-border-radius: 15px;
        -moz-border-radius: 15px;
        border-radius: 15px;
        cursor: pointer;
        border: 2px solid rgba(255, 255, 255, 0.5);
    }

    .closebtn:hover {
        background-color: #246AB0;
        border: 2px solid rgba(255, 255, 255, 1);
    }

    .playerlist {
        float: left;
        overflow: hidden;
        position: relative;
        z-index: 2;
    }

    .playerlist ul {
        position: relative;
        z-index: 1;
        left: 0;
    }

    .playerlist ul li {
        list-style: none;
        float: left;
        margin: 0 4px;
        width: 50px;
        cursor: pointer;
        height: 50px;
        border: 2px solid #777777;
    }

    .imgboxx {
        cursor: pointer;
    }
</style>
<script>
    var jsons = ${jsons};
    var listw = 58;
    var current;

    function player(id) {
        current = id;
        $('#player').attr('src', "//" + getImageById(id).url + "?x-oss-process=style/album-view");
        var index = getIndexById(id);
        var leftvalue = index * listw;
        $("#playerlist").css("left", 0 - leftvalue);
        var tablehtml;
        for (var key in jsons[index]) {
            if (key == "id")
                continue;
            if (key == "meteid")
                continue;
            if (key == "albumid")
                continue;
            if (key == "url")
                continue;
            if (key.length > 18)
                continue;
            tablehtml += "<tr><td style='text-align: right;padding-right: 20px;'>" + key + "</td><td>" + jsons[index][key] + "</td></tr>";
        }
        $("#exifinfotable").html(tablehtml);
        $("#playerbox_title").html(jsons[index]["title"] == undefined ? "暂无标题" : jsons[index]["title"]);
        $("#playerbox_link").attr('href', jsons[index]["url"] == undefined ? "javascript:void(0)" : 'http://' + jsons[index]["url"]);
        $("#playerbox_model").html(jsons[index]["model"] == undefined ? "相机型号未知" : jsons[index]["model"]);
        $("#playerbox_lensmodel").html(jsons[index]["lensmodel"] == undefined ? "镜头型号未知" : jsons[index]["lensmodel"]);
        $("#playerbox_iso").html(jsons[index]["isospeedratings"] == undefined ? "ISO 未知" : "ISO " + jsons[index]["isospeedratings"]);
        $("#playerbox_focallength").html(jsons[index]["focallength"] == undefined ? "?mm" : jsons[index]["focallength"]);
        $("#playerbox_exposurebiasvalue").html(jsons[index]["exposurebiasvalue"] == undefined ? "+/- ?" : "+/- " + jsons[index]["exposurebiasvalue"].replace("EV", ""));
        $("#playerbox_aperturevalue").html(jsons[index]["aperturevalue"] == undefined ? "F ?" : "F " + jsons[index]["aperturevalue"].replace("f/", ""));
        $("#playerbox_shutterspeedvalue").html(jsons[index]["shutterspeedvalue"] == undefined ? "1/?" : jsons[index]["shutterspeedvalue"].replace("sec", ""));
        $('#playerbox').show();
        setSize();
    }

    function playerNext() {
        var index = getIndexById(current);
        if (index != jsons.length - 1) {
            var nextID = jsons[index + 1].id;
            player(nextID);
        }
    }

    function playerBack() {
        var index = getIndexById(current);
        if (index != 0) {
            var backID = jsons[index - 1].id;
            player(backID);
        }
    }

    function listL() {
        var temp = $("#playerlist").css("left");
        temp = temp.substr(0, temp.length - 2);
        $("#playerlist").css("left", temp - listw);
    }

    function listR() {
        var temp = $("#playerlist").css("left");
        temp = temp.substr(0, temp.length - 2);
        if ((temp + listw) < 1) {
            $("#playerlist").css("left", parseInt(temp) + listw);
        }
    }

    function getImageById(id) {
        for (var i = 0; i < jsons.length; i++)
            if (jsons[i].id == id)
                return jsons[i];
    }

    function getIndexById(id) {
        for (var i = 0; i < jsons.length; i++)
            if (jsons[i].id == id)
                return i;
    }
</script>
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>

<main id="awd-site-main">
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">
                <div>
                    <div class="slide-item" data-slide-id="contact">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide-content">
                                        <div class="row">
                                        <#if images??>
                                            <#list images as image>
                                                <div class="album_list animated fadeInDown imgboxx"
                                                     data-animation="fadeInDown"
                                                     data-animation-delay="500" onclick="player(${image.getId()})">
                                                    <div class="imgBox">
                                                        <img class="left"
                                                             src="//${image.getUrl()!""}?x-oss-process=style/album-list-show"
                                                             width="170" height="133">
                                                    </div>
                                                    <div class="album_title">
                                                    ${image.getTitle()!image.getDatetime()?substring(0,10)?replace(":","-")}
                                                    </div>
                                                </div>
                                            </#list>
                                        </#if>
                                        </div>
                                        <div class="row">
                                        <#if articlePagingList??>
                                            <div>
                                                <#list articlePagingList as articlePaging>
                                                    <a href="/Image?page=${articlePaging.getIndex()!""}"
                                                       class="btn <#if articlePaging.getIndex()==Index>btn-inverse</#if> animated"
                                                       data-animation-delay="60" style="margin-bottom: 20px;background: transparent;color: #FFF;">
                                                    ${articlePaging.getName()!""}
                                                    </a>
                                                </#list>
                                            </div>
                                        </#if>
                                        </div>
                                    </div>
                                    <!-- END SLIDE CONTENT-->
                                </div>
                            </div>
                            <!-- END ROW -->
                        </div>

                    </div>
                </div>
            </div>
    </section>
</main>
<div id="playerbox">
    <div class="playerleft">
        <div id="playerleft_box">
            <div id="aPrev" class="CursorL" onclick="playerBack()"></div>
            <div id="aNext" class="CursorR" onclick="playerNext()"></div>
            <div class="img_box">
                <img id="player" src="">
            </div>
        </div>
        <div class="playerleft_list">
            <img src="/static/img/ArrowL.jpg" style="float: left;cursor:pointer;height: 50px;margin-left: 10px;"
                 onclick="listR()">
            <div class="playerlist">
                <ul id="playerlist" style="left: 0;">
                <#if images??>
                    <#list images as image>
                        <li onclick="player(${image.getId()})">
                            <img src="//${image.getUrl()}?x-oss-process=style/50-50">
                        </li>
                    </#list>
                </#if>
                </ul>
                <div style="clear: both"></div>
            </div>
            <img src="/static/img/ArrowR.jpg" style="float: right;cursor:pointer;height: 50px;margin-right: 10px;"
                 onclick="listL()">
        </div>
    </div>
    <div class="playerright">
        <div class="closebtn" onclick="$('#playerbox').hide()">X</div>
        <div class="playertitle">
            <h4 id="playerbox_title">标题</h4>
            <a id="playerbox_link" href="javascript:void(0)" target="_blank">查看原图</a>
            <div class="exifinfo">
                <p id="playerbox_model"></span></p>
                <p id="playerbox_lensmodel"></p>
                <div class="exifinfo-ft">
                    <ul>
                        <li id="playerbox_iso"></li>
                        <li id="playerbox_focallength"></li>
                        <li id="playerbox_exposurebiasvalue"></li>
                        <li id="playerbox_aperturevalue"></li>
                        <li id="playerbox_shutterspeedvalue"></li>
                    </ul>
                    <div style="clear: both"></div>
                </div>
            </div>
        </div>
        <div class="exifinfoall">
            <table id="exifinfotable">
            </table>
        </div>
    </div>
</div>
<@defaultLayout.footer>
<script>
    $(window).resize(function () {
        setSize();
    });

    function setSize() {
        $(".playerleft").width($("#playerbox").width() - 310);
        $("#playerleft_box").height($(".playerleft").height() - 70);
        $(".playerlist").width($(".playerleft_list").width() - 62);
        $("#aPrev").height($("#playerleft_box").height());
        $("#aNext").height($("#playerleft_box").height());
        $(".exifinfoall").height($(".playerright").height() - $(".playertitle").height() - 20);
    }

    $(function () {
        setSize();
    });
</script>
</@defaultLayout.footer>