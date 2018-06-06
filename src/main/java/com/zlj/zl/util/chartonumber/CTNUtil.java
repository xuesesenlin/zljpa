package com.zlj.zl.util.chartonumber;

import java.util.StringJoiner;

public class CTNUtil {
    public static int CharToNumber(String s) {
        String p = "一二三四五六七八九十";
        StringJoiner t = new StringJoiner("0");
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (p.indexOf(chars[i]) != -1)
                t.add((p.indexOf(chars[i]) + 1) + "");
            else
                break;
        }
        int i = Integer.parseInt(t.toString());
        return i;
    }
}
