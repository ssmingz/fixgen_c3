class PlaceHold {
  public void testNullPointer() throws IOException {
    try {
      CBZip2InputStream cb = new CBZip2InputStream(new ByteArrayInputStream(new byte[0]));
      fail("expected an exception");
    } catch (IOException e) {
    }
  }
}
