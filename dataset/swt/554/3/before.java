class PlaceHold {
  void draggingExited(int id, int sel, NSObject sender) {
    clearDropNotAllowed();
    if (keyOperation == (-1)) {
      return;
    }
    keyOperation = -1;
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    event.detail = DND.DROP_NONE;
    notifyListeners(DragLeave, event);
    if (new NSObject(id).isKindOfClass(class_NSTableView)) {
      callSuper(id, sel, sender.id);
    }
  }
}
