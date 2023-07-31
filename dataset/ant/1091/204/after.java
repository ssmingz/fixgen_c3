class PlaceHold {
  @Test
  public void testRoundTrip() throws IOException {
    buildRule.executeTarget("testRoundTrip");
    assertPropertyContains("testRoundTrip", "file:");
    String property = getProperty("testRoundTrip");
    URL url = new URL(property);
    InputStream instream = url.openStream();
    instream.close();
  }
}
