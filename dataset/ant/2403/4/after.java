class PlaceHold {
  @Test
  public void testBadNoJarfile() {
    try {
      buildRule.executeTarget("test-bad-no-jarfile");
      fail("Build exception should have been thrown on bad jar file");
    } catch (BuildException ex) {
      assertContains("Missing 'jarfile' attribute!", ex.getMessage());
    }
    assertNull(buildRule.getProject().getProperty("jar.classpath"));
  }
}
