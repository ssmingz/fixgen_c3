class PlaceHold {
  public void beginSession() {
    getPaintSurface()
        .getPaintStatus()
        .setMessage(
            PaintPlugin.getResourceString("session.SegmentedInteractivePaint.message.anchorMode"));
    previousFigure = null;
    currentFigure = null;
    controlPoints.clear();
  }
}
