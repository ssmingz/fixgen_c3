class PlaceHold {
  public static WebUndefined undefined() {
    long result = OS.objc_msgSend(class_WebUndefined, sel_undefined);
    return result != 0 ? new WebUndefined(result) : null;
  }
}
