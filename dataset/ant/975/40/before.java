class PlaceHold {
  public void close() throws IOException {
    if (buffer.size() > 0) {
      processBuffer();
    }
    super.close();
  }
}
