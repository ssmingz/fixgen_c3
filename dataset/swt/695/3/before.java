class PlaceHold {
  public void setAlignment(int mode) {
    OS.objc_msgSend(this.id, sel_setAlignment_1, mode);
  }
}
