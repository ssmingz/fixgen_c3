class PlaceHold {
  StreamPumper createInputPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
    StreamPumper pumper = new StreamPumper(is, os, closeWhenExhausted, false);
    pumper.setAutoflush(true);
    return pumper;
  }
}
