/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

import c.models.ParameterComplexModel;
import c.models.ParameterComplexModelList;
import c.models.ParameterSimpleModel;
import c.models.ParameterSimpleModelList;
import c.util.FileUtil;
import c.util.PropertiesUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")
public class Admin {

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/menu1")
    public String menu1() {
        return "admin/menu1";
    }

    @RequestMapping(value = "/singlefileupload1", method = RequestMethod.POST)
    public @ResponseBody
    Boolean singleFileUpload1(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multipartRequest.getFileNames();
                while (iterator.hasNext()) {
                    MultipartFile multipartFile = multipartRequest.getFile(iterator.next());
                    if (multipartFile != null) {
                        String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(multipartFile.getOriginalFilename());
                        PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
                        String savePath = propertiesUtil.getValueByKey("file_save_path") + fileName;
                        File localFile = new File(savePath);
                        multipartFile.transferTo(localFile);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(value = "/singlefileupload2", method = RequestMethod.POST)
    public @ResponseBody
    Boolean singleFileUpload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(file.getOriginalFilename());
            PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
            String savePath = propertiesUtil.getValueByKey("file_save_path") + fileName;
            File localFile = new File(savePath);
            file.transferTo(localFile);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(value = "/singletwofileupload1", method = RequestMethod.POST)
    public @ResponseBody
    Boolean singleTwoFileUpload1(@RequestParam("file1") CommonsMultipartFile file1, @RequestParam("file2") CommonsMultipartFile file2, HttpServletRequest request) {
        try {
            PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
            String fileName1 = UUID.randomUUID() + FileUtil.getFileExtensionName(file1.getOriginalFilename());
            String savePath1 = propertiesUtil.getValueByKey("file_save_path") + fileName1;
            File localFile1 = new File(savePath1);
            file1.transferTo(localFile1);
            String fileName2 = UUID.randomUUID() + FileUtil.getFileExtensionName(file2.getOriginalFilename());
            String savePath2 = propertiesUtil.getValueByKey("file_save_path") + fileName2;
            File localFile2 = new File(savePath2);
            file2.transferTo(localFile2);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(value = "/singletwofileupload2", method = RequestMethod.POST)
    public @ResponseBody
    Boolean singleTwoFileUpload2(@RequestParam("file1") CommonsMultipartFile file1, @RequestParam("file2") CommonsMultipartFile file2, HttpServletRequest request) {
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multipartRequest.getFileNames();
                while (iterator.hasNext()) {
                    MultipartFile multipartFile = multipartRequest.getFile(iterator.next());
                    if (multipartFile != null) {
                        String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(multipartFile.getOriginalFilename());
                        PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
                        String savePath = propertiesUtil.getValueByKey("file_save_path") + fileName;
                        File localFile = new File(savePath);
                        multipartFile.transferTo(localFile);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(value = "/multiplefileupload", method = RequestMethod.POST)
    public @ResponseBody
    Boolean multipleFileUpload(@RequestParam("files") CommonsMultipartFile[] files, HttpServletRequest request) {
        try {
            for (int i = 0; i < files.length; i++) {
                String fileName = UUID.randomUUID() + FileUtil.getFileExtensionName(files[i].getOriginalFilename());
                PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
                String savePath = propertiesUtil.getValueByKey("file_save_path") + fileName;
                File localFile = new File(savePath);
                files[i].transferTo(localFile);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping("/parametersimplemodel")
    public @ResponseBody
    Boolean parameterSimpleModel(@RequestBody ParameterSimpleModel model, HttpServletRequest request) {
        return true;
    }

    @RequestMapping("/parametercomplexmodel1")
    public @ResponseBody
    Boolean parameterComplexModel1(@RequestBody ParameterComplexModel model, HttpServletRequest request) {
        return true;
    }

    @RequestMapping("/parametercomplexmodel2")
    public @ResponseBody
    Boolean parameterComplexModel2(@RequestBody ParameterSimpleModelList model, HttpServletRequest request) {
        return true;
    }

    @RequestMapping("/parametercomplexmodel3")
    public @ResponseBody
    Boolean parameterComplexModel3(@RequestBody ParameterComplexModelList model, HttpServletRequest request) {
        return true;
    }

    @RequestMapping(value = "/imageconverter", method = RequestMethod.POST)
    public @ResponseBody
    String imageConverter(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String imageData = FileUtil.fileToBase64(file.getInputStream());
            return "data:image/jpg;base64," + imageData;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        PropertiesUtil propertiesUtil = new PropertiesUtil("application.properties");
        String path = propertiesUtil.getValueByKey("file_save_path");
        String fileName = "test.jpg";
        File file = new File(path + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            try {
                try (FileInputStream fileInputStream = new FileInputStream(file);
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                        OutputStream outputStream = response.getOutputStream()) {
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }
}
