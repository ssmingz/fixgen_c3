class StreamRedirectThread {
  public StreamRedirectThread(String name, InputStream in, OutputStream out, ThreadGroup tg) {
    this(name, in, out, true, tg, false);
  }
}
