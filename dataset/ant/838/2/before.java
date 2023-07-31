class PlaceHold {
  protected final String readFully() throws IOException {
    return FileUtils.readFully(in, 8192);
  }
}
