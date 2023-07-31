class PlaceHold {
  @Test
  public void testBadNoProperty() {
    try {
      buildRule.executeTarget("test-bad-no-property");
      fail("Build exception should have been thrown on no property");
    } catch (BuildException ex) {
      assertContains("Missing 'property' attribute!", ex.getMessage());
    }
    assertNull(buildRule.getProject().getProperty("jar.classpath"));
  }
}
