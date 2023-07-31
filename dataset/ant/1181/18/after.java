class PlaceHold {
  @Test
  public void testRunAdapterError() {
    try {
      buildRule.executeTarget("runadaptererror");
      fail("BuildException expected: no public run method");
    } catch (BuildException ex) {
      assertContains("No public run() method in", ex.getMessage());
    }
  }
}
