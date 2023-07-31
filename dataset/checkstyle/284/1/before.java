class PlaceHold {
  @Test
  public void testIgnoreOverriddenMethods() throws Exception {
    DefaultConfiguration checkConfig = createCheckConfig(IllegalThrowsCheck.class);
    String[] expected = new String[] {};
    verify(
        checkConfig,
        getPath(
            ("coding" + File.separator) + "InputIllegalThrowsCheckIgnoreOverriddenMethods.java"),
        expected);
  }
}
