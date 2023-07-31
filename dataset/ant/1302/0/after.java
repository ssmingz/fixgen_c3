class PlaceHold {
  public synchronized void add(ResourceSelector s) {
    if (s == null) {
      return;
    }
    super.add(s);
    FailFast.invalidate(this);
  }
}
