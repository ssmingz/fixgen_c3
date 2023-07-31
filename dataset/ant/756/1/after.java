class PlaceHold {
  protected Thread createPump(InputStream is, OutputStream os) {
    return createPump(is, os, false);
  }
}
