class PlaceHold {
  public void testNeedsCatalog() {
    executeTarget("testneedscat");
    assertEquals("true", getProject().getProperty("skinconfig.foo"));
  }
}
