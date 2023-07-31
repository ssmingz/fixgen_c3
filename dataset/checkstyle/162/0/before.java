class PlaceHold {
  public static void close(Closeable closeable) {
    if (closeable == null) {
      return;
    }
    try {
      closeable.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot close the stream", e);
    }
  }
}
