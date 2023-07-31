class PlaceHold {
  public void removeBuildListener(BuildListener listener) {
    eventSupport.removeBuildListener(listener);
    if (mainFrame != null) {
      mainFrame.removeBuildListener(listener);
    }
  }
}
