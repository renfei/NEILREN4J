<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-black">
<meta name="keywords" content="任霏,网站,相册,影像,电子相册,电子影像"/>
<meta name="description" content="任霏个人电子相册，分享任霏的个人摄影作品以及数码影像资料，影像不仅仅是光影的艺术，更是一段回忆和态度。"/>
<title>数码影像-电子相册 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
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
                                        <div class="section-info text-left" style="margin: 0;">
                                            <h2 class="section-title text-default animated" style="margin-bottom: 0;"
                                                data-animation="fadeIn" data-animation-delay="60">数字影像</h2>
                                            <p class="animated" data-animation="fadeIn"
                                               data-animation-delay="100">
                                                影像记录的不仅仅是对光影的记录，一张照片是一段回忆，一张照片是一种态度，一张照片是一种生活。
                                            </p>
                                        </div>
                                        <div class="row">
                                        <#if Albums??>
                                            <#list Albums as Album>
                                                <a href="/Image/${Album.getId()?c}" target="_blank">
                                                    <div class="album animated fadeInDown" data-animation="fadeInDown"
                                                         data-animation-delay="500">
                                                        <div class="imgBox">
                                                            <span class="right">${Album.getCount()?c}</span>
                                                            <img class="left"
                                                                 src="//${Album.getCover()!"cdn.neilren.com/image/album/album_show.jpg"}?x-oss-process=style/album-show"
                                                                 width="157" height="157">
                                                        </div>
                                                        <div class="album_title">
                                                        ${Album.getTitle()!""}
                                                        </div>
                                                    </div>
                                                </a>
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

<@defaultLayout.footer>
</@defaultLayout.footer>