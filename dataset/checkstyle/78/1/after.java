class PlaceHold {
  public void testOpWrapOff() throws Exception {
    mConfig.setJavadocScope(NOTHING);
    mConfig.setBooleanProperty(IGNORE_OP_WRAP_PROP, true);
    final Checker c = createChecker();
    final String filepath = getPath("InputOpWrap.java");
    assertNotNull(c);
    final String[] expected = new String[] {};
    verify(c, filepath, expected);
  }
}
