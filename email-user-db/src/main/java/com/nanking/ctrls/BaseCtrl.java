package com.nanking.ctrls;


import com.nanking.common.Result;
import com.nanking.common.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller 基类
 */
@ControllerAdvice
public class BaseCtrl {

    @Value("${static.domain}")
    private String domain;

    @Value("${static.addVersionFiles}")
    private String addVersionFiles;

    /**
     * 处理成功时的返回
     * @param o 返回的结果集
     * @return Result
     */
    protected Result send(Object o) {
        Result ret = new Result();
        ret.setResult(o);
        return ret;
    }

    /**
     * 非正常情况的返回
     * @param code 错误码
     * @param message 错误信息
     * @return Result
     */
    protected Result send(int code, String message) {
        Result ret = new Result();
        ret.setStatus(code, message);
        return ret;
    }

    /**
     * 特殊情况的反馈
     * @param o 结果集
     * @param code 状态码
     * @param message 状态信息
     * @return Result
     */
    protected Result send(Object o, int code, String message) {
        Result ret = new Result();
        ret.setStatus(code, message);
        ret.setResult(o);
        return ret;
    }

    protected ModelAndView render(String viewName, Map<String, Object> data) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put("staticDomain", this.domain);
        //add versions
        URL url = this.getClass().getClassLoader().getResource("");
        for (String name : this.addVersionFiles.split(";")) {
            File file = new File(url.getFile() + "static/dist/" + name);
            String versionKey = name.substring(0, name.lastIndexOf(".")) + "-version";
            if (file.exists()) {
                data.put(versionKey, "-" + Util.getFileMD5(file));
            } else {
                data.put(versionKey, "");
            }
        }
        mv.addAllObjects(data);
        return mv;
    }

    /**
     * 格式化表单验证信息
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result formatValidatorMsg(BindException exception, HttpServletResponse response) {
        Result ret = new Result();
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> map = new HashMap<>();
        List<FieldError> list = bindingResult.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        ret.setStatus(1001, "参数有误");
        ret.setResult(map);
        return ret;
    }
////    @ExceptionHandler(NullPointerException.class)
////    @ResponseBody
////    public Result NullPointerrMsg(NullPointerException exception, HttpServletResponse response) {
////        Result ret = new Result();
////        Map<String, String> map = new HashMap<>();
////        if(exception !=null){
////            ret.setStatus(1000, "请输入相关数据");
////            ret.setResult(map);
////        }
//
//        return ret;
//    }
}
