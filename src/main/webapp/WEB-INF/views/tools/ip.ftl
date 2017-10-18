<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<meta name="keywords" content="IP查询数据库"/>
<meta name="description" content="IP查询数据库"/>
<title>IP查询 - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
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
                                                <div class="section-info text-left" style="margin: 0;">
                                                    <div class="countdown">
                                                        <h2 class="section-title text-default animated"
                                                            data-animation="fadeIn" data-animation-delay="60">IP查询</h2>
                                                        <div class="animated" data-animation="fadeIn"
                                                             data-animation-delay="60">
                                                            <form class="subscribe-form animated" style="margin-top: 0;"
                                                                  data-animation="fadeIn"
                                                                  data-animation-delay="50"
                                                                  enctype="multipart/form-data">
                                                                <div class="col-md-10" style="padding: 0;margin: 0;">
                                                                    <input type="text" id="ip" name="ip"
                                                                           placeholder="请输入IP地址"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50">
                                                                </div>
                                                                <div class="col-md-2" style="padding: 0;margin: 0;">
                                                                    <button class="btn" style="width: 100%"
                                                                            data-animation="fadeIn"
                                                                            data-animation-delay="50">
                                                                        <span>提交</span> <i class="fa fa-search"></i>
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <div data-animation="fadeIn" style="clear: both"
                                                             data-animation-delay="60">
                                                            <div>IP:[<span id="span_ip"
                                                                           style="color: red;">${IP!""}</span>]
                                                            </div>
                                                            <div>地址：<span
                                                                    id="span_country">${ipdbObject.getCountry()!""}</span>
                                                            </div>
                                                            <div>线路：<span
                                                                    id="span_local">${ipdbObject.getLocal()!""}</span>
                                                            </div>
                                                            <div>数据库更新日期：<span
                                                                    id="span_version">${ipdbObject.getVersion()!""}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="countdown">
                                                        <h3 class="text-default animated" style="margin-top: 40px;"
                                                            data-animation="fadeIn" data-animation-delay="60">
                                                            IP查询开放接口</h3>
                                                        <div data-animation="fadeIn" style="clear: both"
                                                             data-animation-delay="60">
                                                            <div>接口地址:<span id="span_ip"
                                                                            style="color: red;">https://api.neilren.com/IP/query</span>
                                                            </div>
                                                            <div>参数：<span
                                                                    id="span_country">ip=114.114.114.114</span>
                                                            </div>
                                                            <div>方法：<span
                                                                    id="span_local">POST</span>
                                                            </div>
                                                            <div>返回：
                                                                <pre>
{
    "state": 200,
    "message": "Success",
    "object": {
        "country": "江苏省南京市",
        "ip": "114.114.114.234",
        "local": "南京信风网络科技有限公司",
        "version": "20171015"
    }
}
                                                                </pre>
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
        </div>
    </section>
</main>


<@defaultLayout.footer>
</@defaultLayout.footer>