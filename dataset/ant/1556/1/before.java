class PlaceHold {
  public synchronized void add(ResourceSelector s) {
    super.add(s);
    FailFast.invalidate(this);
  }
}
