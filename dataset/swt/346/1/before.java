class PlaceHold {
  public void setMinValue(double newMinimum) {
    OS.objc_msgSend(this.id, sel_setMinValue_1, newMinimum);
  }
}
