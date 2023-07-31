class PlaceHold {
  @Test
  public void testAndIncomplete() {
    buildRule.executeTarget("andincomplete");
    assertNull(buildRule.getProject().getProperty("andincomplete"));
  }
}
