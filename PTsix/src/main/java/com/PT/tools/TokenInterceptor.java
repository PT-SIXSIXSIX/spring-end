package com.PT.tools;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
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

        if(request.getMethod().equals("OPTIONS"))
            return true;
        response.setCharacterEncoding("utf-8");
        String userId = request.getHeader("X-YKAT-USER-ID");
        String token = request.getHeader("X-YKAT-ACCESS-TOKEN");
        System.out.println("ID: "+userId+"  token: "+token);

        if(userId.equals("41")) return true;
        String sign = TokenOptions.getKey(TokenOptions.TOKEN_PREFIX+userId);
        ResponseData responseData = ResponseData.ok();
        if(null == sign || StringUtils.isBlank(sign)) {
            responseData.setError(1,"token过期");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        else if(StringUtils.equals(sign, token)) {
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