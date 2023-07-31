class PlaceHold {
  @Test
  public void testExitAndOtherException() {
    try {
      buildRule.executeTarget("testExitAndOtherException");
      fail("ExitStatusException should have been thrown");
    } catch (ExitStatusException ex) {
      assertEquals(42, ex.getStatus());
    }
  }
}
