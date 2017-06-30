<?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet type="text/xsl" href="http://localhost:8080/sitemap.xsl"?>
<urlset
        xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
        xmlns:mobile="http://www.google.com/schemas/sitemap-mobile/1.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
    		http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">

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