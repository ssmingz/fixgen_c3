class PlaceHold {
  public synchronized void add(ResourceCollection c) {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    if (c == null) {
      return;
    }
    w.add(c);
  }
}
