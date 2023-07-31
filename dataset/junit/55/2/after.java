class PlaceHold {
  public void testTearDownAfterError() {
    TornDown fails = new TornDown("fails");
    verifyError(fails);
    assertTrue(fails.fTornDown);
  }
}
