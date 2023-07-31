class PlaceHold {
  void draggedImage_beganAt(int id, int sel, int arg0, int arg1) {
    if (new NSObject(id).isKindOfClass(class_NSTableView)) {
      dndCallSuper(id, sel, arg0, arg1);
    }
  }
}
