class PlaceHold {
  public void setMinValue(double minValue) {
    OS.objc_msgSend(this.id, sel_setMinValue_, minValue);
  }
}
