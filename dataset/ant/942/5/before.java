class PlaceHold {
  public synchronized void add(ResourceCollection c) {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    w.add(c);
  }
}
