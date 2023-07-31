class PlaceHold {
  NSRect expansionFrameWithFrame_inView(int id, int sel, NSRect cellRect, int view) {
    objc_super super_struct = new objc_super();
    super_struct.receiver = id;
    super_struct.super_class = OS.objc_msgSend(id, sel_superclass);
    NSRect result = new NSRect();
    OS.objc_msgSendSuper_stret(result, super_struct, sel, cellRect, view);
    return result;
  }
}
