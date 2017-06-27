<#-- 引入布局指令的命名空间 -->
<#import "../../layout/defaultLayout.ftl" as defaultLayout>
<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<title>文章发布 - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<style>
    .row {
        margin: 0;
    }
</style>
<script type="application/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    var tag = [];//选择的标签
    var cat = [];//选择的分类
    var objJson = {
        type: "",
        title: "",
        author: "",
        source: "",
        author_link: "",
        keyword: "",
        describes: "",
        content: "",
        cat: [],
        tag: []
    };
    $(function () {
        $('#sub-otp').bind('keypress', function (event) {
            if (event.keyCode == 13)
                sub();
        });
    });
    function selecttag(id) {
        for (var i = 0; i < tag.length; i++) {
            if (tag[i] == id) {
                //找到了，说明曾经选择过，那么移除选择
                tag.splice(i, 1);
                $('#tag-' + id).removeClass('btn-inverse');
                return;
            }
        }
        //没有找到，加入选择
        tag.push(id);
        $('#tag-' + id).addClass('btn-inverse');
    }
    function selectcat(id) {
        for (var i = 0; i < cat.length; i++) {
            if (cat[i] == id) {
                //找到了，说明曾经选择过，那么移除选择
                cat.splice(i, 1);
                $('#cat-' + id).removeClass('btn-inverse');
                return;
            }
        }
        //没有找到，加入选择
        cat.push(id);
        $('#cat-' + id).addClass('btn-inverse');
    }
    function sub() {
        if ($('#sub-otp').val() == "") {
            alert("请输入OTP一次性密码进行身份验证");
            return false;
        }
        objJson.type = $('#sub-type').val();
        objJson.title = $('#sub-title').val();
        objJson.author = $('#sub-author').val();
        objJson.source = $('#sub-source').val();
        objJson.author_link = $('#sub-author-link').val();
        objJson.keyword = $('#sub-keyword').val();
        objJson.describes = $('#sub-describes').val();
        objJson.content = UE.getEditor('editor').getContent();
        objJson.cat = cat;
        objJson.tag = tag;
        console.log(objJson);
        $.ajax({
            type: 'POST',
            url: "/System/Article/Release?strOtp=" + $('#sub-otp').val(),
            data: JSON.stringify(objJson),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.state == "success") {
                    alert("发布成功");
                    window.location.href = "/Article/" + data.msg;
                } else {
                    alert(data.msg);
                    return false;
                }
            },
            dataType: "json"
        });
    }
</script>
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>

<main id="awd-site-main">
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">

                <div class="slides-wrap">
                    <div class="slide-item" data-slide-id="home">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-lg-8 col-md-7 svm">
                                                <div class="section-info text-left">
                                                    <div class="countdown">
                                                        <div class="subscribe-form animated" data-animation="fadeIn"
                                                             data-animation-delay="50">
                                                            <div class="row">
                                                                <div class="col-md-2" style="padding: 0;margin: 0;">
                                                                    <select id="sub-type"
                                                                            style="height: 42px;width: 100%;"
                                                                            class="btn"
                                                                            data-animation="fadeIn"
                                                                            data-animation-delay="50">
                                                                        <option value="0">原创</option>
                                                                        <option value="1">转载</option>
                                                                        <option value="2">译文</option>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-10" style="padding: 0;margin: 0;">
                                                                    <input type="text" id="sub-title"
                                                                           placeholder="标题"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="countdown">
                                                        <div class="animated" data-animation="fadeIn"
                                                             data-animation-delay="60">
                                                            <script id="editor" type="text/plain"
                                                                    style="width:100%;height:300px;"></script>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-5 svm" style="vertical-align: top;">
                                                <div class="subscribe-form animated">
                                                    <div class="row">
                                                        <div class="col-md-2" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-author"
                                                                   placeholder="作者"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                        <div class="col-md-5" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-author-link"
                                                                   placeholder="作者链接"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                        <div class="col-md-5" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-source"
                                                                   placeholder="原文链接"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-keyword"
                                                                   placeholder="关键字"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                        <div class="col-md-6" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-describes"
                                                                   placeholder="摘要"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <h3>文章分类</h3>
                                                        <div>
                                                            <a id="cat-1" onclick="selectcat(1);" class="btn animated">文本</a>
                                                            <a id="cat-2" onclick="selectcat(2);" class="btn animated">文本</a>
                                                            <a id="cat-3" onclick="selectcat(3);" class="btn animated">文本</a>
                                                            <a id="cat-4" onclick="selectcat(4);" class="btn animated">文本</a>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <h3>文章标签</h3>
                                                        <div>
                                                            <a id="tag-1" onclick="selecttag(1);" class="btn animated">文本</a>
                                                            <a id="tag-2" onclick="selecttag(2);" class="btn animated">文本</a>
                                                            <a id="tag-3" onclick="selecttag(3);" class="btn animated">文本</a>
                                                            <a id="tag-4" onclick="selecttag(4);" class="btn animated">文本</a>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="margin-top: 50px;">
                                                        <h3>发布文章</h3>
                                                        <div class="col-md-10" style="padding: 0;margin: 0;">
                                                            <input type="text" id="sub-otp"
                                                                   placeholder="OTP一次性密码"
                                                                   style="padding: 5px 20px;"
                                                                   data-animation="fadeIn"
                                                                   data-animation-delay="50"/>
                                                        </div>
                                                        <div class="col-md-2" style="padding: 0;margin: 0;">
                                                            <a class="btn animated" onclick="sub();">发布</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<@defaultLayout.footer>
<script type="text/javascript">

    function isFocus(e) {
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e) {
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }

    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }
</script>
</@defaultLayout.footer>