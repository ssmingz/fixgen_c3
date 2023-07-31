class PlaceHold {
  public void testAlone() throws Exception {
    mCheckConfig.addAttribute("option", ALONE.toString());
    final String[] expected =
        new String[] {
          "93:27: '}' should be alone on a line.", "93:27: '}' should be on a new line."
        };
    verify(mCheckConfig, getPath("InputLeftCurlyOther.java"), expected);
  }
}
