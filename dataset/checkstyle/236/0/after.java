class PlaceHold {
  public void testSame() throws Exception {
    mCheckConfig.addAttribute("option", SAME.toString());
    final String[] expected =
        new String[] {
          "25:17: '}' should be on the same line.",
          "28:17: '}' should be on the same line.",
          "40:13: '}' should be on the same line.",
          "44:13: '}' should be on the same line.",
          "93:27: '}' should be alone on a line.",
          "93:27: '}' should be on a new line."
        };
    verify(mCheckConfig, getPath("InputLeftCurlyOther.java"), expected);
  }
}
