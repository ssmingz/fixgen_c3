class PlaceHold {
  boolean performDragOperation(int id, int sel, NSObject sender) {
    if (new NSObject(id).isKindOfClass(class_NSTableView)) {
      return callSuper(id, sel, sender.id) != 0;
    }
    return drop(sender);
  }
}
