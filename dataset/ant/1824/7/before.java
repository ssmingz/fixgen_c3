class PlaceHold {
  public void testProperty() {
    executeTarget("property");
    String log = getLog();
    assertTrue(
        "Expecting property in attribute value replaced",
        log.indexOf("Attribute value = test") != (-1));
  }
}
