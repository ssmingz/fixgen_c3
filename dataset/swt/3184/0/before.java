class PlaceHold {
  public static NSDictionary dictionaryWithObject(id object, id key) {
    int result =
        OS.objc_msgSend(
            class_NSDictionary,
            sel_dictionaryWithObject_forKey_,
            object != null ? object.id : 0,
            key != null ? key.id : 0);
    return result != 0 ? new NSDictionary(result) : null;
  }
}
