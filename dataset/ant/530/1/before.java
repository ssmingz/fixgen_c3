class PlaceHold {
  public void buildStarted(BuildEvent event) {
    postEvent(event, BUILD_STARTED);
  }
}
