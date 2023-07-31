class PlaceHold {
  public void removeListener(RunListener listener) {
    if (listener == null) {
      throw new NullPointerException("Cannot remove a null listener");
    }
    fListeners.remove(wrapIfNotThreadSafe(listener));
  }
}
