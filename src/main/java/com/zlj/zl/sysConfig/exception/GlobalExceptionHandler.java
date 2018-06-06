package com.zlj.zl.sysConfig.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlj.zl.util.resultJson.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

//import org.hibernate.JDBCException;

/**
 * 全局错误处理
 * LD
 */
@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ParseException.class)
    public ResponseResult<String> parseException(HttpServletRequest request,
                                                 Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据转换错误，请确认日期，数字等格式是否正确");
        return result;
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<String> illegalArgumentException(HttpServletRequest request,
                                                           Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("非法参数错误，请确认数据准确性");
        return result;
    }

    /**
     * office 文件操作错误
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = POIXMLException.class)
    public ResponseResult<String> pOIXMLException(HttpServletRequest request,
                                                  Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("office 文件操作错误");
        return result;
    }

    /**
     * 不支持的字符集
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    public ResponseResult<String> unsupportedEncodingException(HttpServletRequest request,
                                                               Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("不支持的字符集，当前方法所采用的字符集为UTF-8");
        return result;
    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public void authorizationException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws Exception {
        //        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        return new ModelAndView("/403");
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {

            ResponseResult result = new ResponseResult();
            result.setSuccess(false);
            result.setMessage(new String("权限不足".getBytes("UTF-8"), "UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper();
//            JsonGenerator jsonGenerator = objectMapper.getFactory()
//                    .createGenerator(System.out, JsonEncoding.UTF8);
            //对象转JSON
            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().append(json);
        } else {
//            非ajax请求
            response.sendRedirect("/403");
        }
    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public void UnauthorizedException(HttpServletRequest request,
                                      Exception exception,
                                      HttpServletResponse response) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        return new ModelAndView("/403");
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {

            if (request.getHeader("L") != null) {
//                跳转页面的ajax
                response.sendRedirect("/403");
            }

            ResponseResult result = new ResponseResult();
            result.setSuccess(false);
            result.setMessage(new String("权限不足!".getBytes("UTF-8"), "UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper();
//            JsonGenerator jsonGenerator = objectMapper.getFactory()
//                    .createGenerator(System.out, JsonEncoding.UTF8);
            //对象转JSON
            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().append(json);
        } else {
//            非ajax请求
            response.sendRedirect("/403");
        }
    }

    /**
     * 未登录
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    public void UnknownAccountException(HttpServletRequest request,
                                        Exception exception,
                                        HttpServletResponse response) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        return new ModelAndView("/403");
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            ResponseResult result = new ResponseResult();
            result.setSuccess(false);
            result.setMessage(new String("登录超时,请从新登录".getBytes("UTF-8"), "UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper();
//            JsonGenerator jsonGenerator = objectMapper.getFactory()
//                    .createGenerator(System.out, JsonEncoding.UTF8);
            //对象转JSON
            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().append(json);
        } else {
//            非ajax请求
            response.sendRedirect("/index");
        }
    }


    /**
     * 类型强制转换错误
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ClassCastException.class)
    public ResponseResult<String> ClassCastException(HttpServletRequest request,
                                                     Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("类型强制转换错误，请确认数据准确性");
        return result;
    }


    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public ResponseResult<String> ArrayIndexOutOfBoundsException(HttpServletRequest request,
                                                                 Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数组下标越界错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseResult<String> FileNotFoundException(HttpServletRequest request,
                                                        Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("文件未找到错误");
        return result;
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseResult<String> SQLException(HttpServletRequest request,
                                               Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("操作数据库错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseResult<String> IOException(HttpServletRequest request,
                                              Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("输入输出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = NoSuchMethodException.class)
    public ResponseResult<String> NoSuchMethodException(HttpServletRequest request,
                                                        Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("方法未找到错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = OutOfMemoryError.class)
    public ResponseResult<String> OutOfMemoryError(HttpServletRequest request,
                                                   Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("内存不足错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = StackOverflowError.class)
    public ResponseResult<String> StackOverflowError(HttpServletRequest request,
                                                     Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("堆栈溢出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = VirtualMachineError.class)
    public ResponseResult<String> VirtualMachineError(HttpServletRequest request,
                                                      Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("虚拟机错误，请确认数据准确性");
        return result;
    }

//    @ExceptionHandler(value = JDBCException.class)
//    @ResponseBody
//    public String JDBCException(HttpServletRequest request,
//                                Exception exception) throws Exception {
//        //exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getSuppressed() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getStackTrace() + "::::::" + new Date());
//        return "JDBCException，请确认数据准确性";
//    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult<String> nullPointerException(HttpServletRequest request,
                                                       Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("空指针错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXException.class)
    public ResponseResult<String> SAXException(HttpServletRequest request,
                                               Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面存在多个<!DOCTYPE html>");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXParseException.class)
    public ResponseResult<String> SAXParseException(HttpServletRequest request,
                                                    Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("class 重复");
        return result;
    }

    @ExceptionHandler(value = org.springframework.beans.factory.BeanCreationException.class)
    public void BeanCreationException(HttpServletRequest request,
                                      Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        System.out.println("请购买此产品");
    }

    @ExceptionHandler(value = CannotGetJdbcConnectionException.class)

    public ResponseResult<String> CannotGetJdbcConnectionException(HttpServletRequest request,
                                                                   Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据库链接错误");
        return result;
    }

    @ExceptionHandler(value = TemplateInputException.class)
    public ResponseResult<String> TemplateInputException(HttpServletRequest request,
                                                         Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面错误");
        return result;
    }
}
