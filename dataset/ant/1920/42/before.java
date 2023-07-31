class PlaceHold {
  public void testFileset() {
    executeTarget("testFileset");
    assertPropertyContains("testFileset", ".xml ");
    assertPropertyEndsWith("testFileset", ".xml");
  }
}
