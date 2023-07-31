class PlaceHold {
  public void testCircularReference() {
    try {
      executeTarget("testCircularReference");
    } catch (BuildException e) {
      assertTrue(
          "Circular definition not detected - ",
          e.getMessage().indexOf("was circularly defined") != (-1));
      return;
    }
    fail("Did not throw exception on circular exception");
  }
}
