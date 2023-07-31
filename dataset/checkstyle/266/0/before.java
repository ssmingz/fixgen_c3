class PlaceHold {
  public void testScopeAnonInnerPrivate() throws Exception {
    final CheckConfiguration checkConfig = new CheckConfiguration();
    checkConfig.setClassname(JavadocMethodCheck.class.getName());
    checkConfig.addProperty("scope", PRIVATE.getName());
    final Checker c = createChecker(checkConfig);
    final String fname = getPath("InputScopeAnonInner.java");
    final String[] expected = new String[] {};
    verify(c, fname, expected);
  }
}
