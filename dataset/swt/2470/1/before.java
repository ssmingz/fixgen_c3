class PlaceHold {
  public int levelForItem(id item) {
    return OS.objc_msgSend(this.id, sel_levelForItem_, item != null ? item.id : 0);
  }
}
