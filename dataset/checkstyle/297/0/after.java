class PlaceHold {
  public void testProtected() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocTypeCheck.class);
    checkConfig.addAttribute("scope", PROTECTED.getName());
    final Checker c = createChecker(checkConfig);
    final String fname = getPath("InputPublicOnly.java");
    final String[] expected = new String[] {"7: Missing a Javadoc comment."};
    verify(c, fname, expected);
  }
}
