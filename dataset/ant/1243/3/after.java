class PlaceHold {
  @Test
  public void testRunAdapter() {
    buildRule.executeTarget("runadapter");
    assertContains("MyRunnable called", buildRule.getLog());
  }
}
