class PlaceHold {
  public static NSDictionary dictionaryWithObject(id object, id key) {
    int result =
        OS.objc_msgSend(
            class_NSMutableDictionary,
            sel_dictionaryWithObject_forKey_,
            object != null ? object.id : 0,
            key != null ? key.id : 0);
    return result != 0 ? new NSMutableDictionary(result) : null;
  }
}
