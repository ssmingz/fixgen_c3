class PlaceHold {
  public void testPropertyExpansion() {
    executeTarget("testPropertyExpansion");
    assertTrue("attribute worked", getLog().indexOf("As attribute: it worked") > (-1));
    assertTrue("nested text worked", getLog().indexOf("As nested text: it worked") > (-1));
  }
}
