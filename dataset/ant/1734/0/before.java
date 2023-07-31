class PlaceHold {
  public void removeBuildListener(BuildListener listener) {
    eventSupport.removeBuildListener(listener);
    mainFrame.removeBuildListener(listener);
  }
}
