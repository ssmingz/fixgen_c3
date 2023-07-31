class PlaceHold {
  public int rowForItem(id item) {
    return OS.objc_msgSend(this.id, sel_rowForItem_, item != null ? item.id : 0);
  }
}
