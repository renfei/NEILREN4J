<?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet type="text/xsl" href="https://www.neilren.com/sitemap.xsl"?>
<urlset
        xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
        xmlns:mobile="http://www.google.com/schemas/sitemap-mobile/1.0">

<#if siteMapXmls??>
    <#list siteMapXmls as siteMapXml>
        <url>
            <loc>${siteMapXml.getLoc()!""}</loc>
            <mobile type="pc,mobile"/>
            <changefreq>${siteMapXml.getChangefreq()!""}</changefreq>
            <priority>${siteMapXml.getPriority()!""}</priority>
            <#if siteMapXml.getLastmod()!="">
                <lastmod>${siteMapXml.getLastmod()!""}</lastmod>
            </#if>
        </url>
    </#list>
</#if>
</urlset>