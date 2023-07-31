class PlaceHold {
  public void resetSession() {
    getPaintSurface().clearRubberbandSelection();
    if (previousFigure != null) {
      getPaintSurface().drawFigure(previousFigure);
    }
    getPaintSurface()
        .getPaintStatus()
        .setMessage(
            PaintPlugin.getResourceString("session.SegmentedInteractivePaint.message.anchorMode"));
    previousFigure = null;
    currentFigure = null;
    controlPoints.clear();
  }
}
