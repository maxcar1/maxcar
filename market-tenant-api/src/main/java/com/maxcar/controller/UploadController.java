package com.maxcar.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.file.FileUploadUtil;
import com.maxcar.base.util.file.StringHelper;
import com.maxcar.user.entity.Staff;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UploadController extends BaseController {

    /**
     * @param type : 业务类型 1.交易过户
     * describe: 上传照片
     * create_date:  lxy   2018/8/17  10:04
     **/
    @PostMapping("/uploadFileStream/{type}")
    public InterfaceResult uploadFileStream(MultipartFile file, @PathVariable("type") int type, HttpServletRequest request) throws Exception {

        Staff staff = getCurrentStaff(request);

        //绝对目录
        String uid = UuidUtils.generateIdentifier();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //源文件上传
        FilePojo filePojo = new FilePojo();
        //执行文件上传
        String extension = StringHelper.unqualify(file.getOriginalFilename()).toLowerCase();
        //获取文件后缀
        String fileType = "." + extension;
        //oss服务地址
        String fileName = uid + fileType;

        if (type == 1) {
            String ossPath = staff.getMarketId() + "/transfer/" + uid;
            filePojo.setExtension(extension);
            filePojo.setId(uid);
            filePojo.setPath(realPath);
            filePojo.setName(fileName);
            filePojo.setOssPath(ossPath);
            filePojo = FileUploadUtil.uploadFileCommon(file, filePojo);
        } else {
            return getInterfaceResult("600", "不支持该业务类型");
        }

        return getInterfaceResult("200", filePojo);
    }

}
