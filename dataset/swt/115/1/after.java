class PlaceHold {
  NSRect titleRectForBounds(int id, int sel, NSRect cellFrame) {
    objc_super super_struct = new objc_super();
    super_struct.receiver = id;
    super_struct.super_class = OS.objc_msgSend(id, sel_superclass);
    NSRect result = new NSRect();
    OS.objc_msgSendSuper_stret(result, super_struct, sel, cellFrame);
    return result;
  }
}
