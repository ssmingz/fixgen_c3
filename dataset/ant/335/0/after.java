class PlaceHold {
  public void load(InputStream inStream) throws IOException {
    String s = readLines(inStream);
    byte[] ba = s.getBytes(ISO_8859_1);
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    super.load(bais);
  }
}
