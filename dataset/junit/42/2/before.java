class PlaceHold {
  public void addListener(RunListener listener) {
    if (listener == null) {
      throw new NullPointerException("Cannot add a null listener");
    }
    fListeners.add(wrapIfNotThreadSafe(listener));
  }
}
