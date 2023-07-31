class PlaceHold {
  public void testTearDownAfterError() {
    TornDown fails = new TornDown("fails");
    verifyError(fails);
    assert fails.fTornDown;
  }
}
