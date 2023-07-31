class PlaceHold {
  public void testSingleExit() {
    try {
      executeTarget("testSingleExit");
    } catch (ExitStatusException ex) {
      assertEquals(42, ex.getStatus());
    }
  }
}
