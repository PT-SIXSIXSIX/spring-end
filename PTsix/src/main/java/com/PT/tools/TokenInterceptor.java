package com.PT.tools;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * created by yxhuang
 * 拦截器实现，获取解析token串
 */
public class TokenInterceptor implements HandlerInterceptor{
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }
    //拦截每个请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

//        放过OPTIONS方法
        if(request.getMethod().equals("OPTIONS"))
            return true;
//        获取token和userId
        response.setCharacterEncoding("utf-8");
        String userId = request.getHeader("X-YKAT-USER-ID");
        String token = request.getHeader("X-YKAT-ACCESS-TOKEN");

        ResponseData responseData = ResponseData.ok();
        if(userId == null || token == null) {
            responseData.setError(1,"获取userId或者token为空，请检查字段是否正确");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
//        签名
        String sign = TokenOptions.getKey(TokenOptions.TOKEN_PREFIX+userId);
        if(null == sign || StringUtils.isBlank(sign)) {
            responseData.setError(1,"token过期");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        else if(StringUtils.equals(sign, token)) {
//            token验证正确
            return true;
        }
        else {
            responseData.setError(1,"token验证失败");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
    }
    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        response.setStatus(400);
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData.getBody());
        out.print(json);
        out.flush();
        out.close();
    }
}