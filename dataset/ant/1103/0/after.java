class PlaceHold {
  @Test
  public void testPropertyExpansion() {
    buildRule.executeTarget("testPropertyExpansion");
    assertTrue("attribute worked", buildRule.getLog().indexOf("As attribute: it worked") > (-1));
    assertTrue(
        "nested text worked", buildRule.getLog().indexOf("As nested text: it worked") > (-1));
  }
}
