class PlaceHold {
  @Test
  public void testBadDirectory() {
    try {
      buildRule.executeTarget("test-bad-directory");
      fail("Build exception should have been thrown on bad directory");
    } catch (BuildException ex) {
      assertContains("Jar's directory not found:", ex.getMessage());
    }
    assertNull(buildRule.getProject().getProperty("jar.classpath"));
  }
}
