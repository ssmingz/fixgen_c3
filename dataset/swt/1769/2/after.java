class PlaceHold {
  void draggedImage_endedAt_operation(int id, int sel, int arg0, NSPoint arg1, int arg2) {
    int swtOperation = osOpToOp(arg2);
    NSFileManager fileManager = NSFileManager.defaultManager();
    if (paths != null) {
      for (int i = 0; i < paths.length; i++) {
        if ((paths[i] != null) && exist[i]) {
          if (!fileManager.fileExistsAtPath(paths[i])) {
            swtOperation &= ~DND.DROP_MOVE;
            swtOperation |= DND.DROP_TARGET_MOVE;
          }
        }
      }
      paths = null;
      exist = null;
    }
    OS.objc_msgSend(id, sel_retain);
    try {
      Event event = new DNDEvent();
      event.widget = this;
      event.time = ((int) (System.currentTimeMillis()));
      event.doit = swtOperation != DND.DROP_NONE;
      event.detail = swtOperation;
      notifyListeners(DragEnd, event);
      dragImageFromListener = null;
      if (new NSObject(id).isKindOfClass(class_NSTableView)) {
        dndCallSuper(id, sel, arg0, arg1, arg2);
      }
    } finally {
      OS.objc_msgSend(id, sel_release);
    }
  }
}
