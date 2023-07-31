class PlaceHold {
  public Point getLocation() {
    checkWidget();
    if (window != null) {
      NSRect frame = window.frame();
      float y = display.getPrimaryFrame().height - ((int) (frame.y + frame.height));
      Point point = new Point(((int) (frame.x)), ((int) (y)));
      float scaleFactor = view.window().userSpaceScaleFactor();
      point.x /= scaleFactor;
      point.y /= scaleFactor;
      return point;
    } else {
      NSPoint pt = new NSPoint();
      NSRect primaryFrame = display.getPrimaryFrame();
      if (!view.isFlipped()) {
        pt.y = view.bounds().height - pt.y;
      }
      pt = view.convertPoint_toView_(pt, null);
      pt = view.window().convertBaseToScreen(pt);
      pt.y = primaryFrame.height - pt.y;
      float scaleFactor = view.window().userSpaceScaleFactor();
      pt.x /= scaleFactor;
      pt.y /= scaleFactor;
      return new Point(((int) (pt.x)), ((int) (pt.y)));
    }
  }
}
