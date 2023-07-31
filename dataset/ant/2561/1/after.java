class PlaceHold {
  @Test
  public void testReadBadFileNoFail() {
    buildRule.executeTarget("testReadBadFileNoFail");
    assertContains("srcfile is a directory!", buildRule.getLog());
  }
}
