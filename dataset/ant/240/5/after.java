class PlaceHold {
  @Test
  public void testMultipleTargetsOneDoesntExist_FOEtrue() {
    try {
      buildRule.executeTarget("multipleTargetsOneDoesntExist_FOEtrue");
      fail("BuildException expected: Calling not existent target");
    } catch (BuildException ex) {
      assertContains("Target \"three\" does not exist in the project \"subant\"", ex.getMessage());
    }
  }
}
