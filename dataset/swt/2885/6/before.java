class PlaceHold {
  public void mouseDown(MouseEvent event) {
    if (event.button != 1) {
      return;
    }
    getPaintSurface()
        .getPaintStatus()
        .setMessage(
            PaintPlugin.getResourceString(
                "session.SegmentedInteractivePaint.message.interactiveMode"));
    previousFigure = currentFigure;
    if (controlPoints.size() > 0) {
      final Point lastPoint = ((Point) (controlPoints.elementAt(controlPoints.size() - 1)));
      if ((lastPoint.x == event.x) || (lastPoint.y == event.y)) {
        return;
      }
    }
    controlPoints.add(new Point(event.x, event.y));
  }
}
