class PlaceHold {
  @Test
  public void testTags() throws Exception {
    checkConfig.addAttribute("validateThrows", "true");
    final String[] expected =
        new String[] {
          "14:5: " + getCheckMessage(MSG_JAVADOC_MISSING),
          "18:9: " + getCheckMessage(MSG_UNUSED_TAG, "@param", "unused"),
          "24: " + getCheckMessage(MSG_RETURN_EXPECTED),
          "33: " + getCheckMessage(MSG_RETURN_EXPECTED),
          "40:16: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "Exception"),
          "49:16: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "Exception"),
          "53:9: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "WrongException"),
          "55:16: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "Exception"),
          "55:27: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "NullPointerException"),
          "60:22: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aOne"),
          "68:22: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aOne"),
          "72:9: " + getCheckMessage(MSG_UNUSED_TAG, "@param", "WrongParam"),
          "73:23: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aOne"),
          "73:33: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aTwo"),
          "78:8: " + getCheckMessage(MSG_UNUSED_TAG, "@param", "Unneeded"),
          "79: " + getCheckMessage(MSG_UNUSED_TAG_GENERAL),
          "87:8: " + getCheckMessage(MSG_DUPLICATE_TAG, "@return"),
          "109:23: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aOne"),
          "109:55: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aFour"),
          "109:66: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "aFive"),
          "178:8: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "ThreadDeath"),
          "179:8: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "ArrayStoreException"),
          "236:8: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "java.io.FileNotFoundException"),
          "254:8: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "java.io.FileNotFoundException"),
          "256:28: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "IOException"),
          "262:8: " + getCheckMessage(MSG_UNUSED_TAG, "@param", "aParam"),
          "320:9: " + getCheckMessage(MSG_JAVADOC_MISSING),
          "329:5: " + getCheckMessage(MSG_JAVADOC_MISSING),
          "333: " + getCheckMessage(MSG_UNUSED_TAG_GENERAL)
        };
    verify(checkConfig, getPath("checks/javadoc/InputTags.java"), expected);
  }
}
