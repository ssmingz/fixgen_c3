class PlaceHold {
  @Test
  public void test9() {
    try {
      buildRule.executeTarget("test9");
      fail("BuildException expected: Construction is invalid - Name attribute should not be used");
    } catch (BuildException ex) {
      assertContains(
          "Specify the section name using the \"name\" attribute of the <section> element",
          ex.getMessage());
    }
  }
}
