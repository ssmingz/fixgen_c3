class PlaceHold {
  int hitTest(int id, int sel, NSPoint point) {
    if ((state & DISABLED) != 0) {
      return 0;
    }
    if (!isActive()) {
      return 0;
    }
    if (regionPath != null) {
      NSView superview = new NSView(id).superview();
      if (superview != null) {
        NSPoint pt = superview.convertPoint_toView_(point, view);
        if (!regionPath.containsPoint(pt)) {
          return 0;
        }
      }
    }
    return super.hitTest(id, sel, point);
  }
}
