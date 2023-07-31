class PlaceHold {
  @Test
  public void testNoVersionInfoFail() {
    try {
      buildRule.executeTarget("testNoVersionInfoFail");
      fail("BuildException expected: Manifest Implemention information missing.");
    } catch (BuildException ex) {
      assertContains("No Implementation-Title set.", ex.getMessage());
    }
  }
}
