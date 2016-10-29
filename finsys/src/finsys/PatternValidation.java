/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

import java.util.regex.Pattern;

/**
 *
 * @author 320
 */
public class PatternValidation {
    Pattern aadharPattern = Pattern.compile("\\d{12}");
      //  boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
   Pattern PATTERN_ALPHABETS = Pattern.compile("[a-zA-Z\\.\\ ]+");
Pattern PATTERN_ALPHABETS_OPT = Pattern.compile("[a-zA-Z\\.\\ ]*");
Pattern PATTERN_ALPHA_NUMERIC = Pattern.compile("[0-9a-zA-Z\\ ]+");
Pattern PATTERN_ALPHA_NUMERIC_OPT= Pattern.compile("[0-9a-zA-Z\\ ]*");

Pattern PATTERN_NUMERIC = Pattern.compile("\\d+");
Pattern PATTERN_NUMERIC_OPT = Pattern.compile("\\d*");
Pattern PATTERN_NUMERICDECIMAL = Pattern.compile("\\d+(\\.\\d{1,2})?");
Pattern PATTERN_PERCENTAGE2D = Pattern.compile("\\d{1,2}(?:\\.\\d{1,2})?");
Pattern PATTERN_MOBILE = Pattern.compile("([1-9]){1}([0-9]){9}");

public boolean ValidateNumeric(String str){
    return PATTERN_NUMERICDECIMAL.matcher(str).matches();
}

public boolean ValidatePer(String str){
    return PATTERN_PERCENTAGE2D.matcher(str).matches();
}

public boolean ValidateAlpha(String str){
    return PATTERN_ALPHABETS.matcher(str).matches();
}

public boolean ValidatePhone(String str){
    return PATTERN_MOBILE.matcher(str).matches();
}

//Pattern PATTERN_PERCENTAGE2D = /^\d+(\.\d{1,2})?$/;

//Pattern PATTERN_PERCENTAGE2D_OPT = /^(\d{1,2}(?:\.\d{1,2})?)?$/;
//Pattern PATTERN_PINCODE = /^([1-9]){1}([0-9]){5}$/;
//Pattern PATTERN_PINCODE_OPT = /^(([1-9]){1}([0-9]){5})?$/;
//Pattern PATTERN_MONEY = /^[1-9][0-9]{0,7}(\.?([0-9]){2})*$/;
//Pattern PATTERN_MONEY_OPT = /^([1-9][0-9]{0,7}(\.?([0-9]){2})*)?$/;
//

//
//Pattern PATTERN_MOBILE = /^([1-9]){1}([0-9]){9}$/;
//Pattern PATTERN_ADHAR = /^([1-9]){1}([0-9]){11}$/;
//Pattern PATTERN_MOBILE_OPT = /^(([1-9]){1}([0-9]){9})*$/;
//Pattern PATTERN_LANDLINE = /^([0-9]){10}[0-9]?[0-9]?[0-9]?/;
//Pattern PATTERN_LANDLINE_OPT = /^(([0-9]){10}[0-9]?[0-9]?[0-9]?)?$/;
//Pattern PATTERN_EMAIL = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
//Pattern PATTERN_EMAIL_OPT = /^([_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,}))?$/;
//
//
////Pattern PATTERN_DATE = /^(\d{4})[\/\-\.](0?[1-9]|1[012])[\/\-\.](0?[1-9]|[12][0-9]|3[01])$/;
//Pattern PATTERN_DATE = /^(0?[1-9]|[12][0-9]|3[01])[\/\-\.](0?[1-9]|1[012])[\/\-\.](\d{4})$/;
//Pattern PATTERN_YEAR = /^([1-9]){1}([0-9]){3}$/;
//Pattern PATTERN_MONTH = /^(0[1-9]|1[0-2])$/;
//Pattern PATTERN_DAY = /^(0[1-9]|[1-2][0-9]|3[0-1])$/;
//
//Pattern PATTERN_DATE_OPT = /^((0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-([1-9]){1}([0-9]){3})?$/;
//Pattern PATTERN_YEAR_OPT = /^(([1-9]){1}([0-9]){3})?$/;
//Pattern PATTERN_MONTH_OPT = /^((0[1-9]|1[0-2]))?$/;
//Pattern PATTERN_DAY_OPT = /^((0[1-9]|[1-2][0-9]|3[0-1]))?$/;
//
//Pattern PATTERN_GENDER = /^(M|F|X)$/;
//Pattern PATTERN_MARITALSTATUS = /^(M|U)$/;
//Pattern PATTERN_YESNO = /^(Y|N)$/;
//Pattern PATTERN_SCSTOBCGEN = /^(SC|ST|OBC|GEN)$/;
//Pattern PATTERN_DIVISION = /^(1st|2nd|3rd)$/;
//
//Pattern PATTERN_IPADDRESS = /(^[2][5][0-5].|^[2][0-4][0-9].|^[1][0-9][0-9].|^[0-9][0-9].|^[0-9].)([2][0-5][0-5].|[2][0-4][0-9].|[1][0-9][0-9].|[0-9][0-9].|[0-9].)([2][0-5][0-5].|[2][0-4][0-9].|[1][0-9][0-9].|[0-9][0-9].|[0-9].)([2][0-5][0-5]|[2][0-4][0-9]|[1][0-9][0-9]|[0-9][0-9]|[0-9])$/;
//
//Pattern PATTERN_MAX = /^.{1}$/;
//Pattern PATTERN_LENGTH = /^.{1,150}$/;
//
//Pattern PATTERN_FILE_IMG = /(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/;
//Pattern PATTERN_FILE_ENC = /(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG|\.pdf|\.PDF)$/;
//
//Pattern PATTERN_BASIC_RESTRICTED_CHARACTER = /^([^<]|\<[^a-zA-Z])*[<]?$/;
//Pattern PATTERN_ALL_RESTRICTED_CHARACTER = /^([^\<\>\"\'\%\&]*)$/;
////Pattern PATTERN_ALL_RESTRICTED_CHARACTER = /^([^\<\>\"\'\%\;\)\(\&\+]*)$/;
// 
}
