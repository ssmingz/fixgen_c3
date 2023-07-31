class PlaceHold {
  public void testSemantic2() throws Exception {
    mConfig.setJavadocScope(NOTHING);
    mConfig.setTryBlock(TEXT);
    mConfig.setCatchBlock(TEXT);
    mConfig.setFinallyBlock(TEXT);
    mConfig.setBooleanFlag(IGNORE_IMPORTS_PROP, true);
    mConfig.setBooleanFlag(IGNORE_LONG_ELL_PROP, true);
    mConfig.setIllegalInstantiations("");
    final Checker c = createChecker();
    final String filepath = getPath("InputSemantic.java");
    assertNotNull(c);
    final String[] expected =
        new String[] {
          filepath + ":51:65: Empty catch block.",
          filepath + ":71:52: Empty catch block.",
          filepath + ":72:45: Empty catch block.",
          filepath + ":74:13: Empty try block.",
          filepath + ":76:17: Empty finally block."
        };
    verify(c, filepath, expected);
  }
}
