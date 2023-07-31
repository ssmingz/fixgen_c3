class PlaceHold {
  protected synchronized void handle(Resource r) {
    long size = r.getSize();
    if (size == Resource.UNKNOWN_SIZE) {
      log("Size unknown for " + r.toString(), MSG_WARN);
    } else {
      accum += size;
    }
  }
}
