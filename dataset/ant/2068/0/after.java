class PlaceHold {
  @Test
  public void testNullPointer() throws IOException {
    try {
      new CBZip2InputStream(new ByteArrayInputStream(new byte[0]));
      fail("expected an exception");
    } catch (IOException e) {
    }
  }
}
