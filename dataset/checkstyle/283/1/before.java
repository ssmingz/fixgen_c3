class PlaceHold {
  @Test
  public void testCustonestingDepth() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(NestedTryDepthCheck.class);
    checkConfig.addAttribute("max", "2");
    final String[] expected = new String[] {"34:21: Nested try depth is 3 (max allowed is 2)."};
    verify(checkConfig, getPath("coding/InputNestedTryDepth.java"), expected);
  }
}
