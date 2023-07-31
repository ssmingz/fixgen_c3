class PlaceHold {
  void setNeedsDisplay(int id, int sel, boolean flag) {
    if (flag && (getDrawCount() != 0)) {
      return;
    }
    NSView view = new NSView(id);
    if (flag && display.isPainting.containsObject(view)) {
      NSMutableArray needsDisplay = display.needsDisplay;
      if (needsDisplay == null) {
        needsDisplay = display.needsDisplay = ((NSMutableArray) (new NSMutableArray().alloc()));
        needsDisplay.initWithCapacity(12);
      }
      needsDisplay.addObject(view);
      return;
    }
    objc_super super_struct = new objc_super();
    super_struct.receiver = id;
    super_struct.super_class = OS.objc_msgSend(id, sel_superclass);
    OS.objc_msgSendSuper(super_struct, sel, flag ? 1 : 0);
  }
}
