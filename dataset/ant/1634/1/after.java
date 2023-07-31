class PlaceHold {
  @Test
  public void testParentLevel2TooDeep() {
    try {
      buildRule.executeTarget("test-parent-level2-too-deep");
      fail("Build exception should have been thrown on no suitable path");
    } catch (BuildException ex) {
      assertContains("No suitable relative path from ", ex.getMessage());
    }
    assertNull(buildRule.getProject().getProperty("jar.classpath"));
  }
}
