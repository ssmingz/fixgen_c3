class PlaceHold {
  public void testSemantic2() throws Exception {
    mConfig.setBooleanProperty(IGNORE_WHITESPACE_PROP, true);
    mConfig.setJavadocScope(NOTHING);
    mConfig.setBlockOptionProperty(TRY_BLOCK_PROP, TEXT);
    mConfig.setBlockOptionProperty(CATCH_BLOCK_PROP, TEXT);
    mConfig.setBlockOptionProperty(FINALLY_BLOCK_PROP, TEXT);
    mConfig.setBooleanProperty(IGNORE_IMPORTS_PROP, true);
    mConfig.setBooleanProperty(IGNORE_LONG_ELL_PROP, true);
    mConfig.setStringSetProperty(ILLEGAL_INSTANTIATIONS_PROP, "");
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
