class PlaceHold {
  public synchronized void removeBuildListener(BuildListener listener) {
    listeners.remove(listener);
  }
}
