class PlaceHold {
  public void load(InputStream inStream) throws IOException {
    String s = readLines(inStream);
    byte[] ba = s.getBytes("ISO-8859-1");
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    super.load(bais);
  }
}
