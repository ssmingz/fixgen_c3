class PlaceHold {
  public NSDictionary deviceDescription() {
    long result = OS.objc_msgSend(this.id, sel_deviceDescription);
    return result != 0 ? new NSDictionary(result) : null;
  }
}
