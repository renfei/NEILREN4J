<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head  title="任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客"
keywords="任霏,网站,博客,互联网,IT,技术,软件,应用,开发,建站"
description="任霏个人博客，是一个关注分享关于互联网、IT技术、软件应用、程序开发等计算机科技领域的IT科技独立博客站点，作者任霏（NeilRen）免费为软件开发者提供帮助与支持。"
bgcolour="bg-black">
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
                                                        <div class="animated" data-animation="fadeIn" data-animation-delay="60">
                                                        <#if articleWithBLOBs??>
                                                            <div class="entry-content">
                                                                    <h3 class="text-default animated" data-animation="fadeIn" data-animation-delay="50">
                                                                        ${articleWithBLOBs.getTitle()!""}
                                                                    </h3>
                                                                <div class="row entry-info">
                                                                    <div class="col-xs-6">
                                                                        <span class="entry-date">${articleWithBLOBs.getArticleDat()?date}</span>
                                                                        <span class="entry-byline">By <a href="${articleWithBLOBs.getAuthorUrl()!"javascript:void(0)"}" target="_blank">${articleWithBLOBs.getAuthor()!""}</a></span>
                                                                        <span class="entry-byline">Views:${articleWithBLOBs.getViews()!""}</span>
                                                                    </div>
                                                                    <div class="col-xs-6 rating-wrap text-right">
                                                                        <div class="score-callback" data-score="${articleWithBLOBs.getGrade()!""}" style="float: right;"></div>
                                                                    </div>
                                                                </div>
                                                                <p class="animated" data-animation="fadeIn" data-animation-delay="100">
                                                                    ${articleWithBLOBs.getContent()}
                                                                </p>
                                                            </div>
                                                        </#if>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-5 svm" style="vertical-align: top;">
                                                <div class="section-info text-left">
                                                    <h2 class="text-default animated" data-animation="fadeIn" data-animation-delay="50">
                                                        Popular Tags
                                                    </h2>
                                                <#if tagList??>
                                                    <#list tagList as tag>
                                                        <a href="/tag/${tag.getEnName()!""}" class="btn animated" data-animation-delay="60" style="margin-bottom: 5px;">
                                                        ${tag.getZhName()!""}
                                                        </a>
                                                    </#list>
                                                </#if>
                                                    <h2 class="text-default animated" data-animation="fadeIn" data-animation-delay="50">
                                                        Most Recent
                                                    </h2>
                                                <#if articleTop10ByDateList??>
                                                    <#list articleTop10ByDateList as articleTop10ByDate>
                                                        <a href="/Article/${articleTop10ByDate.getId()?c}" class="animated" data-animation-delay="60" style="display:block;margin-bottom: 5px;font-size: 14px">
                                                            <#if articleTop10ByDate.getTitle()?length gt 28>
                                                            ${articleTop10ByDate.getTitle()?substring(0,28)?html}...
                                                            <#else>
                                                            ${articleTop10ByDate.getTitle()!""}
                                                            </#if>
                                                        </a>
                                                    </#list>
                                                </#if>
                                                    <h2 class="text-default animated" data-animation="fadeIn" data-animation-delay="50">
                                                        Archives
                                                    </h2>
                                                <#if archivesList??>
                                                    <#list archivesList as archives>
                                                        <a href="/Archives/${archives.getDateYmd()}" class="animated" data-animation-delay="60" style="display:block;margin-bottom: 5px;">
                                                        ${archives.getDateYmd()!""}（${archives.getNumber()!""}）
                                                        </a>
                                                    </#list>
                                                </#if>
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
<script type="text/javascript" src="/static/js/jquery.raty.min.js"></script>
<script>
    $(function() {

        $.fn.raty.defaults.path = '/static/img';

        $('.score-callback').raty({

            score: function() {

                return $(this).attr('data-score');

            }

        });

    });
</script>
</@defaultLayout.footer>