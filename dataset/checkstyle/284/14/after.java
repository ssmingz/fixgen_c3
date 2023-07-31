class PlaceHold {
  @Test
  public void test_generics_1() throws Exception {
    checkConfig.addAttribute("allowThrowsTagsForSubclasses", "true");
    checkConfig.addAttribute("allowUndeclaredRTE", "true");
    checkConfig.addAttribute("validateThrows", "true");
    final String[] expected =
        new String[] {
          "17:34: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "RE"),
          "33:13: " + getCheckMessage(MSG_EXCPECTED_TAG, "@param", "<NPE>"),
          "40:12: " + getCheckMessage(MSG_UNUSED_TAG, "@throws", "E"),
          "43:38: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "RuntimeException"),
          "44:13: " + getCheckMessage(MSG_EXCPECTED_TAG, "@throws", "java.lang.RuntimeException")
        };
    verify(checkConfig, getPath("javadoc/TestGenerics.java"), expected);
  }
}
