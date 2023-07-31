class PlaceHold {
  public void buildStarted(BuildEvent event) {
    postEvent(event, BUILD_STARTED);
    _context.getEventBus().postEvent(new BuildStartedEvent(_context, event));
  }
}
