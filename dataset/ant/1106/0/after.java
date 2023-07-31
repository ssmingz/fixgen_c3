class PlaceHold {
  @Test
  public void testFilesmatchOddsizes() {
    buildRule.executeTarget("filesmatch-oddsizes");
    assertNull(buildRule.getProject().getProperty("filesmatch-oddsizes"));
  }
}
