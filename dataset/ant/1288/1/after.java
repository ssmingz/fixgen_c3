class PlaceHold {
  @Test
  public void testStringTokenizer() throws IOException {
    buildRule.executeTarget("stringtokenizer");
    assertContains("#This#is#a#number#of#words#", buildRule.getLog());
  }
}
