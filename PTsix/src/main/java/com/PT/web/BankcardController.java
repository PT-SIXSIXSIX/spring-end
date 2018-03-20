package com.PT.web;


import com.PT.entity.Bankcard;
import com.PT.service.BankcardService;
import com.PT.tools.BeanToMapUtil;
import com.PT.tools.InfoCheckUtil;
import com.PT.tools.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * created by yxhuang
 * 店长个人银行卡管理控制器
 */
@Controller
@RequestMapping("/api/v1/users/{userId}")
public class BankcardController {
    @Autowired
    BankcardService bankcardService;


    /**
     * 根据个人userId获取银行卡
     * @param userId
     * @param response
     * @return
     */
    @RequestMapping(value = "/bankcards", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getCardsByUserId(@PathVariable("userId") int userId
                    , HttpServletResponse response) {
        ResponseData responseData = ResponseData.ok();
        try {
            List list = bankcardService.getBankcardByUserId(userId);
            responseData.putDataValue("bankcards", list);
        } catch (Exception e) {
            response.setStatus(400);
            responseData.setError(1, e.getMessage());
        }
        return responseData.getBody();
    }

    /**
     * 根据个人userId和银行卡信息添加银行卡，每个人最多绑定3张银行卡
     * @param userId
     * @param map
     * @param response
     * @return
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/bankcards", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addCardsByUserId(@PathVariable("userId") int userId
            , @RequestBody Map map, HttpServletResponse response) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        Bankcard bankcard = (Bankcard) BeanToMapUtil.convertMap(Bankcard.class, map);
        ResponseData responseData = ResponseData.ok();
        if(!InfoCheckUtil.bankardCheck(bankcard.getCardId())) {
            response.setStatus(400);
            responseData.setError(1, "银行卡号校验失败");
            return responseData.getBody();
        }
        try {
            if(bankcardService.addBankcard(bankcard, userId) == true) {
                response.setStatus(201);
            } else {
                response.setStatus(400);
                responseData.setError(1, "添加银行卡失败");
            }
        } catch (Exception e) {
            response.setStatus(400);
            responseData.setError(1, e.getMessage());
        }
        return responseData.getBody();
    }

    /**
     * 银行卡解除绑定
     * @param userId
     * @param map
     * @param response
     * @return
     */
    @RequestMapping(value = "/bankcards", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteCardsByIds(@PathVariable("userId") int userId
            , @RequestBody Map map, HttpServletResponse response) {
        ResponseData responseData = ResponseData.ok();
        List<String> list = (List<String>) map.get("cardIds");
        try {
            if(bankcardService.deleteBankcard(list, userId) == false) {
                response.setStatus(400);
                responseData.setError(1, "删除银行卡失败");
            }
        } catch (Exception e) {
            response.setStatus(400);
            responseData.setError(1, e.getMessage());
        }
        return responseData.getBody();
    }
}
