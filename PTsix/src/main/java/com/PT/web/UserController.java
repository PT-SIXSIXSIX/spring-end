package com.PT.web;


import com.PT.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //GET /api/v1/users/user_id/orders/type?page=?ipp=?q=
    @RequestMapping(value = "/{userid}/orders", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,String> getOrder(@PathVariable String userid,
                            @RequestParam String type,
                            @RequestParam String page,
                            @RequestParam String ipp){
        Map<String, String> map = new HashMap<String,String>();
        map.put("userid",userid);
        map.put("type",type);
        map.put("page",page);
        map.put("ipp",ipp);
        return map;
    }
}
