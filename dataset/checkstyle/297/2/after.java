class PlaceHold {
  public void testProtest() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocTypeCheck.class);
    checkConfig.addAttribute("scope", PROTECTED.getName());
    final Checker c = createChecker(checkConfig);
    final String fname = getPath("InputScopeInnerInterfaces.java");
    final String[] expected =
        new String[] {
          "7: Missing a Javadoc comment.",
          "29: Missing a Javadoc comment.",
          "38: Missing a Javadoc comment."
        };
    verify(c, fname, expected);
  }
}
