class PlaceHold {
  public void testNL2() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(LeftCurlyCheck.class);
    checkConfig.addAttribute("option", NL.toString());
    final String[] expected =
        new String[] {
          "14:39: '{' should be on a new line.",
          "21:20: '{' should be on a new line.",
          "34:31: '{' should be on a new line.",
          "43:24: '{' should be on a new line.",
          "56:35: '{' should be on a new line.",
          "60:24: '{' should be on a new line.",
          "74:20: '{' should be on a new line.",
          "87:31: '{' should be on a new line."
        };
    verify(checkConfig, getPath("InputLeftCurlyMethod.java"), expected);
  }
}
