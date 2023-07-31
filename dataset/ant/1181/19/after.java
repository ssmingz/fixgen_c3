class PlaceHold {
  @Test
  public void test10() {
    try {
      buildRule.executeTarget("test10");
      fail("BuildException expected: Attribute has no name");
    } catch (BuildException ex) {
      assertContains("Attributes must have name and value", ex.getMessage());
    }
  }
}
