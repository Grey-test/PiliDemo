package com.zbb.grey.pilidemo.ui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */
public class PhonePR {

    public int id;
    public String name;
    public String prefix;
    public boolean isTrue;

    public PhonePR() {
    }

    public PhonePR(int id, String name, String prefix, boolean isTrue) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.isTrue = isTrue;
    }

    public static List<PhonePR> getPRList() {
        List<PhonePR> list = new ArrayList<>();
        list.add(new PhonePR(1, "中国大陆", "+86", true));
        list.add(new PhonePR(2, "澳门特区", "+853", false));
        list.add(new PhonePR(3, "台湾", "+886", false));
        list.add(new PhonePR(4, "美国", "+1", false));
        list.add(new PhonePR(5, "香港", "+852", false));
        list.add(new PhonePR(6, "比利时", "+32", false));
        list.add(new PhonePR(7, "澳大利亚", "+61", false));
        list.add(new PhonePR(8, "法国", "+33", false));
        list.add(new PhonePR(9, "加拿大", "+1", false));
        list.add(new PhonePR(10, "日本", "+81", false));
        list.add(new PhonePR(11, "新加坡", "+65", false));
        list.add(new PhonePR(12, "韩国", "+82", false));
        list.add(new PhonePR(13, "马来西亚", "+60", false));
        list.add(new PhonePR(14, "英国", "+44", false));
        list.add(new PhonePR(15, "意大利", "+39", false));
        list.add(new PhonePR(16, "德国", "+49", false));
        list.add(new PhonePR(17, "俄罗斯", "+7", false));
        list.add(new PhonePR(18, "新西兰", "+64", false));
        return list;
    }


}
