class PlaceHold {
  public void testNoTargets() throws Exception {
    expectBuildExceptionContaining("testNoTargets", "no params", ERROR_NO_HOSTNAME);
  }
}
