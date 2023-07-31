class PlaceHold {
  @Test
  public void testJarAndClassName() {
    try {
      buildRule.executeTarget("testJarAndClassName");
      fail("Build exception should have been thrown - both classname and JAR are not allowed");
    } catch (BuildException ex) {
      assertEquals("Cannot use 'jar' and 'classname' attributes in same command", ex.getMessage());
    }
  }
}
