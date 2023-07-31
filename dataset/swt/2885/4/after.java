class PlaceHold {
  public void beginSession() {
    getPaintSurface()
        .setStatusMessage(
            PaintPlugin.getResourceString("session.SegmentedInteractivePaint.message.anchorMode"));
    previousFigure = null;
    currentFigure = null;
    controlPoints.clear();
  }
}
