class PlaceHold {
  @Test
  public void testMax() throws Exception {
    DefaultConfiguration checkConfig = createCheckConfig(ThrowsCountCheck.class);
    checkConfig.addAttribute("max", "2");
    String[] expected = new String[] {"22:20: Throws count is 3 (max allowed is 2)."};
    verify(checkConfig, getPath(("design" + File.separator) + "InputThrowsCount.java"), expected);
  }
}
