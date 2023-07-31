class PlaceHold {
  public NSDictionary infoDictionary() {
    int result = OS.objc_msgSend(this.id, sel_infoDictionary);
    return result != 0 ? new NSDictionary(result) : null;
  }
}
