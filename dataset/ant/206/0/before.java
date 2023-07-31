class PlaceHold {
  public void testExitAndOtherException() {
    try {
      executeTarget("testExitAndOtherException");
    } catch (ExitStatusException ex) {
      assertEquals(42, ex.getStatus());
    }
  }
}
