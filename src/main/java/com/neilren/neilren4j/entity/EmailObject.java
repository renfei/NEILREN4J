package com.neilren.neilren4j.entity;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName EmailObject
 * @Description TODO
 * @Date 2018/8/6 21:47
 */
@Data
public class EmailObject {
    private String toEmail;             //收件人
    private String emailSubject;        //邮件主题
    private String emailContent;        //邮件正文
    private String sabstract;           //邮件摘要
    private String securityrating;      //涉密等级
    private String event;               //关联事件
    private String name;                //称呼

    public String getEmailContent() {
        //读取邮件模板，构建发送内容
        String filePath = "emailtemplate.html";//类路径，编译后classes目录下
        Resource resource1 = new ClassPathResource(filePath);
        try {
            File file = resource1.getFile(); //获取file对象
            String encoding = "UTF-8";
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "", temphtml = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    temphtml += lineTxt;
                }
                read.close();
                temphtml = temphtml.replace("${toemail}", this.getToEmail());
                temphtml = temphtml.replace("${abstract}", this.getSabstract());
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
                temphtml = temphtml.replace("${senddate}", df.format(new Date()));
                temphtml = temphtml.replace("${securityrating}", this.getSecurityrating());
                temphtml = temphtml.replace("${event}", this.getEvent());
                temphtml = temphtml.replace("${name}", this.getName());
                temphtml = temphtml.replace("${content}", this.emailContent);
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                temphtml = temphtml.replace("${datetime}", df2.format(new Date()));
                SimpleDateFormat df3 = new SimpleDateFormat("yyyy");//设置日期格式
                temphtml = temphtml.replace("${year}", df3.format(new Date()));
                return temphtml;
            }
            return emailContent;
        } catch (Exception e) {
            return emailContent;
        }
    }
}
