class PlaceHold {
  public int retainCount() {
    return ((int) (OS.objc_msgSend(this.id, sel_retainCount)));
  }
}
