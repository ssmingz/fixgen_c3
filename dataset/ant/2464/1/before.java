class PlaceHold {
  protected synchronized void handle(Resource r) {
    long size = r.getSize();
    if (size == Resource.UNKNOWN_SIZE) {
      log("Size unknown for " + r.getName(), MSG_WARN);
    } else {
      accum += size;
    }
  }
}
