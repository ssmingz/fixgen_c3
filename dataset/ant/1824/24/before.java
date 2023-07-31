class PlaceHold {
  public void testClassname() {
    executeTarget("testClassname");
    assertNotNull(getProject().getProperty("antmain"));
  }
}
