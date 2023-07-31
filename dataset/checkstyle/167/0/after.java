class PlaceHold {
  @Test
  public void packageInfoValid() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocStyleCheck.class);
    final String[] expected = ArrayUtils.EMPTY_STRING_ARRAY;
    verify(
        createChecker(checkConfig),
        getPath(((("pkginfo" + File.separator) + "valid") + File.separator) + "package-info.java"),
        expected);
  }
}
