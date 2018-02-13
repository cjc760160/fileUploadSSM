package com.cjc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author cjc
 * @date 2018-02-13
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/tofile")
    public String toFileUpload() {
        return "fileUpload";
    }

    @RequestMapping("/onefile")
    public String oneFileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, ModelMap modelMap) {
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名：" + fileName);

        //新文件名
        String newFileName = UUID.randomUUID() + fileName;

        //获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        //上传位置
        String path = sc.getRealPath("/img") + "/";
        File realFile = new File(path);
        if (!realFile.exists()) {
            realFile.mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                int b;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("上传图片到：" + path + newFileName);
        //保存文件地址，用于jsp页面回显
        modelMap.addAttribute("fileUrl", path + fileName);
        return "fileUpload";
    }
}
