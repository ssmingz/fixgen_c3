class PlaceHold {
  public void testScopeAnonInnerPrivate() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocMethodCheck.class);
    checkConfig.addAttribute("scope", PRIVATE.getName());
    final Checker c = createChecker(checkConfig);
    final String fname = getPath("InputScopeAnonInner.java");
    final String[] expected = new String[] {};
    verify(c, fname, expected);
  }
}
