class PlaceHold {
  @Test
  public void testTypeNamesForFourPermittedCapitalLetters() throws Exception {
    final int expectedCapitalCount = 4;
    warningMessage = getCheckMessage(MSG_KEY, expectedCapitalCount);
    final DefaultConfiguration checkConfig = createCheckConfig(AbbreviationAsWordInNameCheck.class);
    checkConfig.addAttribute("allowedAbbreviationLength", String.valueOf(expectedCapitalCount));
    checkConfig.addAttribute("allowedAbbreviations", "CLASS,FACTORY");
    checkConfig.addAttribute("tokens", "CLASS_DEF");
    checkConfig.addAttribute("ignoreOverriddenMethods", "true");
    final String[] expected = new String[] {"32: " + warningMessage};
    verify(checkConfig, getPath("naming/InputAbbreviationAsWordInTypeNameCheck.java"), expected);
  }
}
