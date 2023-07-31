class PlaceHold {
  public void testTearDownSetupFails() {
    TornDown fails =
        new TornDown("fails") {
          protected void setUp() {
            throw new Error();
          }
        };
    verifyError(fails);
    assertTrue(!fails.fTornDown);
  }
}
