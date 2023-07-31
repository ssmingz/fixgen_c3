class PlaceHold {
  @Test
  public void test11() {
    try {
      buildRule.executeTarget("test11");
      fail("BuildException expected: Attribute has no value");
    } catch (BuildException ex) {
      assertContains("Attributes must have name and value", ex.getMessage());
    }
  }
}
