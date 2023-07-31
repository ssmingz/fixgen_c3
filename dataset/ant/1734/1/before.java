class PlaceHold {
  public void addBuildListener(BuildListener listener) {
    eventSupport.addBuildListener(listener);
    mainFrame.addBuildListener(listener);
  }
}
