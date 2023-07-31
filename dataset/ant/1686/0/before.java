class PlaceHold {
  public int defaultInput(byte[] buffer, int offset, int length) throws IOException {
    if (defaultInputStream != null) {
      return defaultInputStream.read(buffer, offset, length);
    } else {
      throw new EOFException("No input provided for project");
    }
  }
}
