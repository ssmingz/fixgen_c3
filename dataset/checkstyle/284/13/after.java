class PlaceHold {
  @Test
  public void test_generics_3() throws Exception {
    checkConfig.addAttribute("validateThrows", "true");
    final String[] expected =
        new String[] {
          "8:8: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "RE"),
          "17:34: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "RE"),
          "33:13: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "<NPE>"),
          "40:12: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "E"),
          "43:38: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "RuntimeException"),
          "44:13: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "java.lang.RuntimeException")
        };
    verify(checkConfig, getPath("javadoc/TestGenerics.java"), expected);
  }
}
