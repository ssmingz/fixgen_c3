class PlaceHold {
  public void testPrefixSuccess() {
    executeTarget("prefix.success");
    assertEquals("80", project.getProperty("server1.http.port"));
  }
}
