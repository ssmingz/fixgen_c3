class PlaceHold {
  public void test_1168408_1() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocMethodCheck.class);
    final String[] expected = new String[] {};
    verify(checkConfig, getPath("javadoc/Test1.java"), expected);
  }
}
