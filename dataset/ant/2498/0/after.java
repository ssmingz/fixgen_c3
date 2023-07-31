class PlaceHold {
  @Test
  public void testProperty() {
    buildRule.executeTarget("property");
    String log = buildRule.getLog();
    assertTrue(
        "Expecting property in attribute value replaced",
        log.indexOf("Attribute value = test") != (-1));
  }
}
