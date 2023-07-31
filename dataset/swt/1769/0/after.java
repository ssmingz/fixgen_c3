class PlaceHold {
  int dragImageForRowsWithIndexes_tableColumns_event_offset(
      int id, int sel, int arg0, int arg1, int arg2, int arg3) {
    if (dragImageFromListener != null) {
      NSPoint point = new NSPoint();
      point.x = dragOffset.x;
      point.y = dragOffset.y;
      OS.memmove(arg3, point, sizeof);
      return dragImageFromListener.handle.id;
    } else {
      return dndCallSuperObject(id, sel, arg0, arg1, arg2, arg3);
    }
  }
}
