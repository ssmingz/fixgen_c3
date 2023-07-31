class PlaceHold {
  public void testTypeParamsTags() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocMethodCheck.class);
    final String[] expected =
        new String[] {
          "26:8: Unused @param tag for '<BB>'.", "28:13: Expected @param tag for '<Z>'."
        };
    verify(checkConfig, getPath("InputTypeParamsTags.java"), expected);
  }
}
