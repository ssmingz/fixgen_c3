class PlaceHold {
  public synchronized int read() throws IOException {
    if (in == null) {
      throw new IOException("Stream Closed");
    }
    byte result;
    if ((slack != null) && (begin < slack.length)) {
      result = slack[begin];
      if ((++begin) == slack.length) {
        slack = null;
      }
    } else {
      byte[] buf = new byte[1];
      if (read(buf, 0, 1) <= 0) {
        return -1;
      } else {
        result = buf[0];
      }
    }
    return result & 0xff;
  }
}
