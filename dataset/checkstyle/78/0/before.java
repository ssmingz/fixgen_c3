class PlaceHold {
  public void testOpWrapOn() throws Exception {
    mConfig.setJavadocScope(NOTHING);
    mConfig.setBooleanFlag(IGNORE_OP_WRAP_PROP, false);
    final Checker c = createChecker();
    final String filepath = getPath("InputOpWrap.java");
    assertNotNull(c);
    final String[] expected =
        new String[] {
          filepath + ":15:19: '+' should be on a new line.",
          filepath + ":16:15: '-' should be on a new line.",
          filepath + ":24:18: '&&' should be on a new line."
        };
    verify(c, filepath, expected);
  }
}
