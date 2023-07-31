class PlaceHold {
  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    if (handle == null) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    NSGraphicsContext context = NSGraphicsContext.currentContext();
    NSGraphicsContext.setCurrentContext(handle);
    checkGC(FILL);
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    if (((width == 0) || (height == 0)) || (arcAngle == 0)) {
      return;
    }
    handle.saveGraphicsState();
    NSAffineTransform transform = NSAffineTransform.transform();
    float xOffset = data.drawXOffset;
    float yOffset = data.drawYOffset;
    transform.translateXBy((x + xOffset) + (width / 2.0F), (y + yOffset) + (height / 2.0F));
    transform.scaleXBy(width / 2.0F, height / 2.0F);
    NSBezierPath path = data.path;
    NSPoint center = new NSPoint();
    path.moveToPoint(center);
    float sAngle = -startAngle;
    float eAngle = -(startAngle + arcAngle);
    path.appendBezierPathWithArcWithCenter_radius_startAngle_endAngle_clockwise_(
        center, 1, sAngle, eAngle, arcAngle > 0);
    path.closePath();
    path.transformUsingAffineTransform(transform);
    path.fill();
    path.removeAllPoints();
    handle.restoreGraphicsState();
    NSGraphicsContext.setCurrentContext(context);
  }
}
