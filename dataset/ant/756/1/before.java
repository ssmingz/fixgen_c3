class PlaceHold {
  protected Thread createPump(InputStream is, OutputStream os) {
    final Thread result = new Thread(new StreamPumper(is, os));
    result.setDaemon(true);
    return result;
  }
}
