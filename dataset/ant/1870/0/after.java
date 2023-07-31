class PlaceHold {
  public synchronized void removeBuildListener(BuildListener listener) {
    Vector newListeners = getBuildListeners();
    newListeners.removeElement(listener);
    listeners = newListeners;
  }
}
