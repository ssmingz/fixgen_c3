class PlaceHold {
  public void testAlone() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(RightCurlyCheck.class);
    checkConfig.addAttribute("option", ALONE.toString());
    final String[] expected = new String[] {};
    verify(checkConfig, getPath("InputLeftCurlyOther.java"), expected);
  }
}
