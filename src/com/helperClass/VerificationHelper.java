package com.helperClass;

import android.widget.EditText;

public class VerificationHelper {
	public static boolean isMatch(String str1, String str2) {
		return str1.equals(str2);	
	}
	public static boolean isMatch(EditText edt1, EditText edt2) {
		String str1 = edt1.getText().toString().trim();
		String str2 = edt2.getText().toString().trim();
		return str1.equals(str2);	
	}

}
