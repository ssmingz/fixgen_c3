class PlaceHold {
  public int nextWordFromIndex(int location, boolean isForward) {
    return ((int) (OS.objc_msgSend(this.id, sel_nextWordFromIndex_forward_, location, isForward)));
  }
}
