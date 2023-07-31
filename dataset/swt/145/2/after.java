class PlaceHold {
  public int GetButton(short[] aButton) {
    return XPCOM.VtblCall(this.getGetterIndex("button"), getAddress(), aButton);
  }
}
