package com.maxcar.tenant.app.bean;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 因为要转换为xml，所以会出现下划线命名的字段
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxPayResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String return_code;

    private String return_msg;

    public static WxPayResponseBean successResult() {
        WxPayResponseBean wxPayResponseBean = new WxPayResponseBean();
        wxPayResponseBean.setReturn_code("SUCCESS");
        wxPayResponseBean.setReturn_msg("");
        return wxPayResponseBean;
    }

    public static WxPayResponseBean failResult(String reason) {
        WxPayResponseBean wxPayResponseBean = new WxPayResponseBean();
        wxPayResponseBean.setReturn_code("fail");
        wxPayResponseBean.setReturn_msg(reason == null ? "" : reason);
        return wxPayResponseBean;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

}
