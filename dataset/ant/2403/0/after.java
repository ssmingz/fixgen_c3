class PlaceHold {
  @Test
  public void testBadNoClassPath() {
    try {
      buildRule.executeTarget("test-bad-no-classpath");
      fail("Build exception should have been thrown on no classpath");
    } catch (BuildException ex) {
      assertContains("Missing nested <classpath>!", ex.getMessage());
    }
    assertNull(buildRule.getProject().getProperty("jar.classpath"));
  }
}
