class PlaceHold {
  public long clickedRow() {
    return OS.objc_msgSend(this.id, sel_clickedRow);
  }
}
