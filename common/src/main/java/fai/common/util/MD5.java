package fai.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
  
  public static String getMD5(String s) throws Exception {
    return DigestUtils.md5Hex(s.getBytes("UTF-8"));
  }
  

}
