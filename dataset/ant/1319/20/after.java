class PlaceHold {
  @Test
  public void testDuplicateTextName2() {
    try {
      buildRule.executeTarget("duplicatetextname2");
      fail(
          "BuildException expected: the attribute name \"text\" has already been used by the text"
              + " element");
    } catch (BuildException ex) {
    }
  }
}
