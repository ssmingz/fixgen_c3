class PlaceHold {
  public void setAlignment(int alignment) {
    OS.objc_msgSend(this.id, sel_setAlignment_1, alignment);
  }
}
