class PlaceHold {
  public static NSArray arrayWithObject(id anObject) {
    int result =
        OS.objc_msgSend(class_NSArray, sel_arrayWithObject_, anObject != null ? anObject.id : 0);
    return result != 0 ? new NSArray(result) : null;
  }
}
