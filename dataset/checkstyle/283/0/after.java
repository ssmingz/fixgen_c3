class PlaceHold {
  @Test
  public void testMax() throws Exception {
    DefaultConfiguration checkConfig = createCheckConfig(ThrowsCountCheck.class);
    checkConfig.addAttribute("max", "2");
    String[] expected = new String[] {"22:20: " + getCheckMessage(MSG_KEY, 3, 2)};
    verify(checkConfig, getPath(("design" + File.separator) + "InputThrowsCount.java"), expected);
  }
}
