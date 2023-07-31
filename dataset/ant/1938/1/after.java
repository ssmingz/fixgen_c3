class PlaceHold {
  @Test
  public void testDouble() {
    buildRule.executeTarget("double");
    assertEquals(
        "@{prop} is 'property', value of ${property} is 'A property value'", buildRule.getLog());
  }
}
