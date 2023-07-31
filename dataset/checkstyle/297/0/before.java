class PlaceHold {
  public void testProtected() throws Exception {
    final CheckConfiguration checkConfig = new CheckConfiguration();
    checkConfig.setClassname(JavadocTypeCheck.class.getName());
    checkConfig.addProperty("scope", PROTECTED.getName());
    final Checker c = createChecker(checkConfig);
    final String fname = getPath("InputPublicOnly.java");
    final String[] expected = new String[] {"7: Missing a Javadoc comment."};
    verify(c, fname, expected);
  }
}
