class PlaceHold {
  public int elementAtIndex(int index, int points) {
    return OS.objc_msgSend(this.id, sel_elementAtIndex_associatedPoints_, index, points);
  }
}
