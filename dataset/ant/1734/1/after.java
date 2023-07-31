class PlaceHold {
  public void addBuildListener(BuildListener listener) {
    eventSupport.addBuildListener(listener);
    if (mainFrame != null) {
      mainFrame.addBuildListener(listener);
    }
  }
}
