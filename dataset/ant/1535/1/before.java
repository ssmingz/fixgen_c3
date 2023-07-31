class PlaceHold {
  public void run() {
    synchronized (this) {
      finished = false;
    }
    final byte[] buf = new byte[SIZE];
    int length;
    try {
      while ((length = is.read(buf)) > 0) {
        os.write(buf, 0, length);
      }
    } catch (Exception e) {
    } finally {
      if (closeWhenExhausted) {
        os.close();
      }
      synchronized (this) {
        finished = true;
        notify();
      }
    }
  }
}
