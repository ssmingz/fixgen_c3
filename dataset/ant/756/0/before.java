class PlaceHold {
  protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
    final Thread result = new ThreadWithPumper(new StreamPumper(is, os, closeWhenExhausted, true));
    result.setDaemon(true);
    return result;
  }
}
