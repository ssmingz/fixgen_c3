class PlaceHold {
  @Test
  public void testSingleExit() {
    try {
      buildRule.executeTarget("testSingleExit");
      fail("ExitStatusException should have been thrown");
    } catch (ExitStatusException ex) {
      assertEquals(42, ex.getStatus());
    }
  }
}
