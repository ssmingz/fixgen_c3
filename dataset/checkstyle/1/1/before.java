class PlaceHold {
  public void testAssertIdentifier() throws Exception {
    mConfig.setJavadocScope(NOTHING);
    final Checker c = createChecker();
    final String filepath = getPath("InputAssertIdentifier.java");
    assertNotNull(c);
    final String[] expected = new String[] {};
    verify(c, filepath, expected);
  }
}
