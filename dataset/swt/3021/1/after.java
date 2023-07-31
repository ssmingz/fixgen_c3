class PlaceHold {
  public int clickCount() {
    return ((int) (OS.objc_msgSend(this.id, sel_clickCount)));
  }
}
