package com.maxcar.base.util;

import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.oss.OSSManageUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordToPdfToImageUtils {

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "张三");

        params.put("count", "");

        params.put("market", "010");
        /*XWPFDocument doc = WordUtil.generateWord(params, "V:\\pdf\\1111.docx");
        FileOutputStream fopts = new FileOutputStream("V:\\pdf\\1111_1.docx");
        doc.write(fopts);
        fopts.close();*/

        WordToPdfToImage("V:\\pdf\\1111.docx", "V:\\pdf\\3333.pdf", "V:/pdf", params);
    }

    /**
     * @param docPath   word 模板存放路径
     * @param pdfPath   pdf 存放路径
     * @param imagePath image 存放文件夹路径
     * @param params
     * @return
     */
    public static List<String> WordToPdfToImage(String docPath, String pdfPath, String imagePath, Map params) throws Exception {
        WordToPdf(docPath, pdfPath, params);

        return pdf2Image(pdfPath, imagePath, 500);
    }


    /**
     * param:
     * describe: 以下代码都是word转pdf
     * create_date:  lxy   2018/10/24  11:16
     **/
    public static void WordToPdf(String docPath, String pdfPath, Map params) throws Exception {

        InputStream source = new FileInputStream(docPath);
        OutputStream target = new FileOutputStream(pdfPath);

        PdfOptions options = PdfOptions.create();

        wordConverterToPdf(source, target, options, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source,
                                          OutputStream target, Map<String, String> params) throws Exception {
        wordConverterToPdf(source, target, null, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source  源为word文档， 必须为docx文档
     * @param target  目标输出
     * @param params  需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options,
                                          Map<String, String> params) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);


        paragraphReplace(doc.getParagraphs(), params);
        // 修改表格
        List<XWPFTable> tables = doc.getTables();

        if (null != tables && !tables.isEmpty()) {
            // 第一个是物业表格 定死
            XWPFTable table = tables.get(0);

            String listSize = params.get("listSize");
            if (null == listSize || listSize.isEmpty()) {
                XWPFTableRow row = table.createRow();
                table.addRow(row);
                for (int i = 0; i < 12; i++) {
                    table.removeRow(0);
                }
            } else {
                for (int i = Integer.valueOf(listSize); i < 8; i++) {
                    table.removeRow(Integer.valueOf(listSize) + 1);
                }
            }


            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    paragraphReplace(cell.getParagraphs(), params);
                }
            }
        }

        PdfConverter.getInstance().convert(doc, target, options);
    }


    /**
     * 替换段落中内容
     */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs) {
                for (XWPFRun r : p.getRuns()) {
                    String content = r.getText(r.getTextPosition());
                    //logger.info(content);
                    if (StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }

    /**
     * param:
     * describe: 以下是pdf转image
     * create_date:  lxy   2018/10/24  11:16 
     **/


    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param PdfFilePath pdf完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     * @return
     */
    public static List<String> pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) throws Exception {
        List<String> images = new ArrayList<>();

        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }

            if (createDirectory(imgFolderPath)) {

                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                /* dpi越大转换后越清晰，相对转换速度越慢 */
              /*  PdfReader reader = new PdfReader(PdfFilePath);
                int pages = reader.getNumberOfPages();*/

                int pages = pdDocument.getPages().getCount();

                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".png");
                    File dstFile = new File(imgFilePath.toString());

                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);

                    // 上传图片至oss
                    FilePojo filePojo = new FilePojo();
                    filePojo.setPath(dstFile.getPath());
                    filePojo.setName(dstFile.getName());
                    filePojo.setOssPath("contract");
                    FilePojo filePojo1 = OSSManageUtil.uploadFile(filePojo);
                    images.add(filePojo1.getOssPath());
                }

                return images;

            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }


}
