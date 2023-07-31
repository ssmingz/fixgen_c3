class PlaceHold {
  protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
    return createPump(is, os, closeWhenExhausted, true);
  }
}
