class PlaceHold {
  public void testWrongClass() throws Exception {
    expectBuildExceptionContaining(
        "testWrongClass", "class not an RMIC adapter", ERROR_NOT_RMIC_ADAPTER);
  }
}
