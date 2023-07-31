class PlaceHold {
  @Test
  public void testFilesmatchIdentical() {
    buildRule.executeTarget("filesmatch-identical");
    assertEquals("true", buildRule.getProject().getProperty("filesmatch-identical"));
  }
}
