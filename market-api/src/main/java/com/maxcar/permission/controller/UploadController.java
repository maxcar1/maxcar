package com.maxcar.permission.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.ProvinceService;
import com.maxcar.base.service.UploadService;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.file.FileUploadUtil;
import com.maxcar.base.util.file.StringHelper;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


@RestController
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ConfigurationService configurationService;

    /**
     * param:
     * describe: 上传照片
     * create_date:  lxy   2018/8/17  10:04
     **/
    @RequestMapping("uploadFileStream/{businessPath}")
    public InterfaceResult uploadFileStream(MultipartFile file, @PathVariable("businessPath") String businessPath, HttpServletRequest request) throws Exception {

        User user = super.getCurrentUser(request);
        //获取文件原名
        String fileOriginalName = file.getOriginalFilename();
        //绝对目录
        String uid = UuidUtils.generateIdentifier();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //源文件上传
        FilePojo filePojo = new FilePojo();
        //执行文件上传
        String extension = StringHelper.unqualify(file.getOriginalFilename()).toLowerCase();
        //获取文件后缀
        String filetype = "." + extension;
        //oss服务地址
        String fileName = uid + filetype;
        String ossPath = user.getMarketId() + "/" + businessPath + "/" + uid;
        filePojo.setExtension(extension);
        filePojo.setId(uid);
        filePojo.setPath(realPath);
        filePojo.setName(fileName);
        filePojo.setOssPath(ossPath);
        filePojo = FileUploadUtil.uploadFileCommon(file, filePojo);
        filePojo.setFileOriginalName(fileOriginalName);
        return getInterfaceResult("200", filePojo);
    }

    /**
     * 上传合同
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadLocal", method = RequestMethod.POST)
    public InterfaceResult uploadLocal(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            // 获取文件后缀
            String extension = StringHelper.unqualify(multipartFile.getOriginalFilename()).toLowerCase();
            if (extension.equals("docx")){
                //源文件上传
                FilePojo filePojo = new FilePojo();
                String path = "";
                // 获取合同上传服务器保存地址
                List<Configuration> configurations = configurationService.searchConfigurationByKey(Constants.PROPERTY_DOC_URL);
                    if (configurations != null && configurations.size() == 1){
                    path = configurations.get(0).getConfigurationValue();
                }
                //获取文件名
                String fileName = multipartFile.getOriginalFilename();
                //绝对目录
                String uid = UuidUtils.gettimeFormartyyyyMMddHHmmss();
                String realPath = path+File.separator+uid;
                File file = new File(realPath);
                if (!file.exists()){
                    FileUtils.forceMkdir(file);
                }
                // 文件存放路径
                String endPath = realPath+File.separator+fileName;
                File realFile = new File(endPath);

                multipartFile.transferTo(realFile);
                if (super.files(endPath)){
                    filePojo.setExtension(extension);
                    filePojo.setId(uid);
                    filePojo.setPath(endPath);
                    filePojo.setName(fileName);
                    interfaceResult.InterfaceResult200(filePojo);
                }else {
                    interfaceResult.InterfaceResult600("文件上传失败");
                }
            }else {
                interfaceResult.InterfaceResult600("文件类型有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

}
