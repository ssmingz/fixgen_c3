class PlaceHold {
  @Test
  public void testPrefixSuccess() {
    buildRule.executeTarget("prefix.success");
    assertEquals("80", buildRule.getProject().getProperty("server1.http.port"));
  }
}
