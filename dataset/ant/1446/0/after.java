class PlaceHold {
  @Test
  public void testNoTargets() throws Exception {
    try {
      buildRule.executeTarget("testNoTargets");
      fail("Build exception expected: no params");
    } catch (BuildException ex) {
      assertEquals(ERROR_NO_HOSTNAME, ex.getMessage());
    }
  }
}
