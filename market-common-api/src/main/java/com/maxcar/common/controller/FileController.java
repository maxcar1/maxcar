//package com.maxcar.common.controller;
//
//import com.maxcar.base.service.UploadService;
//import com.maxcar.core.base.pojo.InterfaceResult;
//import com.maxcar.core.utils.Result;
//import com.maxcar.core.utils.StringUtils;
//import com.maxcar.core.utils.UUIDUtils;
//import com.maxcar.core.utils.file.FilePojo;
//import com.maxcar.core.utils.file.FileUploadUtil;
//import com.maxcar.core.utils.file.StringHelper;
//import com.maxcar.core.utils.oss.OSSManageUtil;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//
//@SuppressWarnings("SpringJavaAutowiringInspection")
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//	@Autowired
//    private UploadService uploadService;
//
//	/*
//	 * 原来的单独上传文件，无参数方法，放弃
//	@ResponseBody
//    @RequestMapping(value = "/upload2",method = RequestMethod.POST)
//    public Result upload2(@RequestParam("file")MultipartFile file){
//    	Result result = uploadService.upload(file);
//        return result;
//    }
//    */
//
//    /**
//     * 文件下载
//     * @param params 文件url
//     * @return
//     */
//    @PostMapping(value = "/download")
//    public Result download(@RequestBody JSONObject params){
//    	String url = params.getString("url");
//    	Result result = uploadService.download(url);
//		return result;
//    }
//
//    /**
//     * 文件删除
//     * @param params 文件url
//     * @return
//     */
//    @PostMapping(value = "/delete")
//    public Result delete(@RequestBody JSONObject params){
//    	String url = params.getString("url");
//    	Result result = uploadService.delete(url);
//		return result;
//    }
//
//
//    /**
//     *
//     * @param file
//     * @param businessPath 存放业务路径支持多层
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/upload/{businessPath}",method = RequestMethod.POST)
//    public InterfaceResult upload(@RequestParam("file")MultipartFile file, @PathVariable("businessPath")String businessPath, HttpServletRequest request){
//        InterfaceResult interfaceResult = new InterfaceResult();
//        try {
//            //绝对目录
//            String uid = UUIDUtils.getUUID();
//            String realPath = request.getSession().getServletContext().getRealPath("/");
//            //源文件上传
//            FilePojo filePojo = new FilePojo();
//            //执行文件上传
//            String extension = StringHelper.unqualify(file.getOriginalFilename()).toLowerCase();
//            //获取文件后缀
//            String filetype = ".jpg";
//            //oss服务地址
//            String fileName = uid + filetype;
//            String ossPath = businessPath + "/" + uid + StringHelper.FOLDER_SEPARATOR + fileName;
//            filePojo.setExtension(extension);
//            filePojo.setId(uid);
//            filePojo.setPath(realPath);
//            filePojo.setName(fileName);
//            filePojo.setOssPath(ossPath);
//            filePojo = FileUploadUtil.uploadFileCommon(file, filePojo);
//            interfaceResult.InterfaceResult200(filePojo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            interfaceResult.InterfaceResult500();
////            return interfaceResult.InterfaceResult500();
//        }
//        return interfaceResult;
//    }
//}
