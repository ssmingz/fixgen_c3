class PlaceHold {
  @Test
  public void testReferenceAbuse() {
    try {
      buildRule.executeTarget("test-reference-abuse");
      fail("BuildException should have been thrown by reference abuse");
    } catch (BuildException ex) {
      assertContains("reference abuse rejected", "You must not specify", ex.getMessage());
    }
  }
}
