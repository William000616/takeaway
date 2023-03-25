package com.hjx.takeout.controller;


import cn.hutool.core.io.FileUtil;
import com.hjx.takeout.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/file")
public class UploadController {
    private static final String BASE_FILE_PATH = System.getProperty("user.dir")+"/files/";
    private static final String URL = "http://localhost:3000";

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        long flag =System.currentTimeMillis();
        String filePath=BASE_FILE_PATH+flag+"_"+originalFileName;
        try{
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            return Result.success(URL+"/file/download/"+flag);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play, HttpServletResponse res){
        OutputStream os;
        List<String> fileNames=FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName=fileNames.stream().filter(name->name.contains(flag)).findAny().orElse("");
        try{
            String realName=fileName.substring(fileName.indexOf("_")+1);
            res.addHeader("Content-Disposition","inline;filename="+ URLEncoder.encode(realName,"UTF-8"));
            byte[] bytes=FileUtil.readBytes(BASE_FILE_PATH+fileName);
            os=res.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
