class PlaceHold {
  public static NSArray arrayWithObject(id anObject) {
    int result =
        OS.objc_msgSend(
            class_NSMutableArray, sel_arrayWithObject_, anObject != null ? anObject.id : 0);
    return result != 0 ? new NSMutableArray(result) : null;
  }
}
