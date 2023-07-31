class PlaceHold {
  public void removeBuildListener(BuildListener listener) {
    Vector newListeners = getBuildListeners();
    newListeners.removeElement(listener);
    listeners = newListeners;
  }
}
