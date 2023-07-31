class PlaceHold {
  public void test_1168408_3() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocMethodCheck.class);
    checkConfig.addAttribute("allowThrowsTagsForSubclasses", "true");
    checkConfig.addAttribute("allowUndeclaredRTE", "true");
    final String[] expected = new String[] {};
    verify(checkConfig, getPath("javadoc/Test3.java"), expected);
  }
}
