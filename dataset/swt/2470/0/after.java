class PlaceHold {
  public int rowForItem(id item) {
    return ((int) (OS.objc_msgSend(this.id, sel_rowForItem_, item != null ? item.id : 0)));
  }
}
