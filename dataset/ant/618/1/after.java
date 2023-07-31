class PlaceHold {
  @Test
  public void testZipEmptyCreate() {
    buildRule.executeTarget("zipEmptyCreate");
    assertContains("Note: creating empty", buildRule.getLog());
  }
}
