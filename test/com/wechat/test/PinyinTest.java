package com.wechat.test;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class PinyinTest {
	
	public static void main(String[] args) {
		try {
			String str = "	你        好  世 		界";
			str = str.replaceAll("\\s", "");
			System.out.println(str);
			System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK));
			PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
			System.out.println();
			PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
			PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE); // ni,hao,shi,jie
			PinyinHelper.getShortPinyin(str); // nhsj
		} catch (PinyinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
