class PlaceHold {
  public static NSString stringWithFormat(NSString format) {
    int result =
        OS.objc_msgSend(class_NSString, sel_stringWithFormat_, format != null ? format.id : 0);
    return result != 0 ? new NSString(result) : null;
  }
}
