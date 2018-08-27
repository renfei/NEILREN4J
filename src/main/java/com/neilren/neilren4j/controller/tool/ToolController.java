package com.neilren.neilren4j.controller.tool;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.service.interfaces.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ToolController
 * @Description TODO
 * @Date 2018/7/31 14:19
 */
@Controller
@Slf4j
@RequestMapping("/tool")
public class ToolController extends BasePageController {
    @Autowired
    UploadService ossService;

    @RequestMapping("ueditor")
    @ResponseBody
    public String ueditor(@RequestParam(value = "action", required = false) String action) {
        String re = "";
        String config = "/* 前后端通信相关的配置,注释只允许使用多行方式 */\n" +
                "{\n" +
                "    /* 上传图片配置项 */\n" +
                "    \"imageActionName\": \"uploadimage\", /* 执行上传图片的action名称 */\n" +
                "    \"imageFieldName\": \"upfile\", /* 提交的图片表单名称 */\n" +
                "    \"imageMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 上传图片格式显示 */\n" +
                "    \"imageCompressEnable\": true, /* 是否压缩图片,默认是true */\n" +
                "    \"imageCompressBorder\": 1600, /* 图片压缩最长边限制 */\n" +
                "    \"imageInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    \"imageUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "                                /* {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 */\n" +
                "                                /* {rand:6} 会替换成随机数,后面的数字是随机数的位数 */\n" +
                "                                /* {time} 会替换成时间戳 */\n" +
                "                                /* {yyyy} 会替换成四位年份 */\n" +
                "                                /* {yy} 会替换成两位年份 */\n" +
                "                                /* {mm} 会替换成两位月份 */\n" +
                "                                /* {dd} 会替换成两位日期 */\n" +
                "                                /* {hh} 会替换成两位小时 */\n" +
                "                                /* {ii} 会替换成两位分钟 */\n" +
                "                                /* {ss} 会替换成两位秒 */\n" +
                "                                /* 非法字符 \\ : * ? \" < > | */\n" +
                "                                /* 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename */\n" +
                "\n" +
                "    /* 涂鸦图片上传配置项 */\n" +
                "    \"scrawlActionName\": \"uploadscrawl\", /* 执行上传涂鸦的action名称 */\n" +
                "    \"scrawlFieldName\": \"upfile\", /* 提交的图片表单名称 */\n" +
                "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"scrawlMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"scrawlUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"scrawlInsertAlign\": \"none\",\n" +
                "\n" +
                "    /* 截图工具上传 */\n" +
                "    \"snapscreenActionName\": \"uploadimage\", /* 执行上传截图的action名称 */\n" +
                "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"snapscreenUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"snapscreenInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "\n" +
                "    /* 抓取远程图片配置 */\n" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
                "    \"catcherActionName\": \"catchimage\", /* 执行抓取远程图片的action名称 */\n" +
                "    \"catcherFieldName\": \"source\", /* 提交的图片列表表单名称 */\n" +
                "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"catcherUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"catcherMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 抓取图片格式显示 */\n" +
                "\n" +
                "    /* 上传视频配置 */\n" +
                "    \"videoActionName\": \"uploadvideo\", /* 执行上传视频的action名称 */\n" +
                "    \"videoFieldName\": \"upfile\", /* 提交的视频表单名称 */\n" +
                "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"videoUrlPrefix\": \"\", /* 视频访问路径前缀 */\n" +
                "    \"videoMaxSize\": 102400000, /* 上传大小限制，单位B，默认100MB */\n" +
                "    \"videoAllowFiles\": [\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], /* 上传视频格式显示 */\n" +
                "\n" +
                "    /* 上传文件配置 */\n" +
                "    \"fileActionName\": \"uploadfile\", /* controller里,执行上传视频的action名称 */\n" +
                "    \"fileFieldName\": \"upfile\", /* 提交的文件表单名称 */\n" +
                "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"fileUrlPrefix\": \"\", /* 文件访问路径前缀 */\n" +
                "    \"fileMaxSize\": 51200000, /* 上传大小限制，单位B，默认50MB */\n" +
                "    \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ], /* 上传文件格式显示 */\n" +
                "\n" +
                "    /* 列出指定目录下的图片 */\n" +
                "    \"imageManagerActionName\": \"listimage\", /* 执行图片管理的action名称 */\n" +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\", /* 指定要列出图片的目录 */\n" +
                "    \"imageManagerListSize\": 20, /* 每次列出文件数量 */\n" +
                "    \"imageManagerUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"imageManagerInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 列出的文件类型 */\n" +
                "\n" +
                "    /* 列出指定目录下的文件 */\n" +
                "    \"fileManagerActionName\": \"listfile\", /* 执行文件管理的action名称 */\n" +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\", /* 指定要列出文件的目录 */\n" +
                "    \"fileManagerUrlPrefix\": \"\", /* 文件访问路径前缀 */\n" +
                "    \"fileManagerListSize\": 20, /* 每次列出文件数量 */\n" +
                "    \"fileManagerAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ] /* 列出的文件类型 */\n" +
                "\n" +
                "}";
        switch (action) {
            case "config":
                re = config;
                break;
            default:
                re = config;
                break;
        }
        return re;
    }

    @RequestMapping("ueditor/demo")
    public ModelAndView ueditordemo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ueditordemo");
        return mv;
    }

    @RequestMapping(value = "FileUploadPoint",method = RequestMethod.POST)
    @ResponseBody
    public String FileUploadPoint(MultipartFile upfile) {
        // 获取文件名
        String fileName = upfile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        final File excelFile;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
            // MultipartFile to File
            upfile.transferTo(excelFile);
            String url = ossService.upload(excelFile);
            //程序结束时，删除临时文件
            deleteFile(excelFile);
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + url + "\"," +
                    "\"title\": \"" + url.substring(url.lastIndexOf("/")) + "\"," +
                    "\"original\": \"" + url.substring(url.lastIndexOf("/")) + "\"}";
            return config;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
