class PlaceHold {
  @Test
  public void testValidIfWithChecker() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(IndentationCheck.class);
    checkConfig.addAttribute("arrayInitIndent", "4");
    checkConfig.addAttribute("basicOffset", "4");
    checkConfig.addAttribute("braceAdjustment", "0");
    checkConfig.addAttribute("caseIndent", "4");
    checkConfig.addAttribute("forceStrictCondition", "false");
    checkConfig.addAttribute("lineWrappingIndentation", "4");
    checkConfig.addAttribute("tabWidth", "4");
    checkConfig.addAttribute("throwsIndent", "4");
    final String fileName = getPath("InputValidIfIndent.java");
    final String[] expected = new String[] {"231: " + getCheckMessage(MSG_ERROR, "(", 8, 12)};
    verifyWarns(checkConfig, fileName, expected);
  }
}
