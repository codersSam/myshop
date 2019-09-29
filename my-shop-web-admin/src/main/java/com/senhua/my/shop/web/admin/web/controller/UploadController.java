package com.senhua.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2019/3/12.
 */
@Controller
public class UploadController {

    public static final String UPLOAD_PATH = "/static/upload";
    @ResponseBody
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropzFile, MultipartFile[] editorFiles, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        //Dropzone 上传
        if(dropzFile != null){
            result.put("fileName" , writeFile(dropzFile,request));
        }
        //wangEditor 上传
        if(editorFiles != null && editorFiles.length>0){
            List<String> fileNames = new ArrayList<>();
            for(MultipartFile editorFile :  editorFiles){
                fileNames.add(writeFile(editorFile,request));
            }
            result.put("errno",0);
            result.put("data",fileNames);
        }
        //返回json数据，这里只带入了文件名
        return result;
    }

    /**
     * 将图片写入指定目录
     * @param multipartFile
     * @param request
     * @return 返回文件完整路径
     */
    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
        //获得文件名
        String fileName = multipartFile.getOriginalFilename();
        //设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //获得文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        //判断并创建上传用得文件夹
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //重新设置文件名为UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        //写入文件
        try{
            multipartFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }

        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return serverPath + UPLOAD_PATH + "/" + file.getName();
    }

}
