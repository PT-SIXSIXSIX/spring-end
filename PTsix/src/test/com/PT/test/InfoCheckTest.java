package com.PT.test;

import com.PT.tools.InfoCheckUtil;
import org.junit.Test;

public class InfoCheckTest extends BaseTest {
    @Test
    public void passwordCheck() {
        String p1 = "12356";
        String p2 = "asget2";
        String p3 = "112345wertyjtyt32yhe4tfgery34453623tgerert";
        String p4 = "&*daf3qwt<>";
        System.out.println(InfoCheckUtil.passwordCheck(p1));
        System.out.println(InfoCheckUtil.passwordCheck(p2));
        System.out.println(InfoCheckUtil.passwordCheck(p3));
        System.out.println(InfoCheckUtil.passwordCheck(p4));
    }
    @Test
    public void phoneCheck() {
        String p1 = "18996094270";
        String p2 = "13290046270";
        String p3 = "10086";
        String p4 = "1345634512x";
        String p5 = "xafgwawrq@f";
        System.out.println(InfoCheckUtil.phoneNoCheck(p1));
        System.out.println(InfoCheckUtil.phoneNoCheck(p2));
        System.out.println(InfoCheckUtil.phoneNoCheck(p3));
        System.out.println(InfoCheckUtil.phoneNoCheck(p4));
        System.out.println(InfoCheckUtil.phoneNoCheck(p5));
    }
    @Test
    public void IDCardCheck() {
        String p1 = "510181199503231711";
        String p2 = "510231235213452652";
        String p3 = "36232919970815451X";
        String p4 = "124513563464523432";
        System.out.println(InfoCheckUtil.IDCardCheck(p1));
        System.out.println(InfoCheckUtil.IDCardCheck(p2));
        System.out.println(InfoCheckUtil.IDCardCheck(p3));
        System.out.println(InfoCheckUtil.IDCardCheck(p4));
    }
    @Test
    public void bankcardCheck() {
        String p1 = "6230200172878899";
        String p2 = "6230200172565899";
        String p3 = "1354624674674584";
        System.out.println(InfoCheckUtil.bankardCheck(p1));
        System.out.println(InfoCheckUtil.bankardCheck(p2));
        System.out.println(InfoCheckUtil.bankardCheck(p3));
    }
}
