class PlaceHold {
  void setNeedsDisplayInRect(int id, int sel, int arg0) {
    if (getDrawCount() != 0) {
      return;
    }
    NSRect rect = new NSRect();
    OS.memmove(rect, arg0, sizeof);
    NSView view = new NSView(id);
    if (display.isPainting.containsObject(view)) {
      NSMutableArray needsDisplayInRect = display.needsDisplayInRect;
      if (needsDisplayInRect == null) {
        needsDisplayInRect =
            display.needsDisplayInRect = ((NSMutableArray) (new NSMutableArray().alloc()));
        needsDisplayInRect.initWithCapacity(12);
      }
      needsDisplayInRect.addObject(view);
      needsDisplayInRect.addObject(NSValue.valueWithRect(rect));
      return;
    }
    objc_super super_struct = new objc_super();
    super_struct.receiver = id;
    super_struct.super_class = OS.objc_msgSend(id, sel_superclass);
    OS.objc_msgSendSuper(super_struct, sel, rect);
  }
}
