class PlaceHold {
  public int levelForItem(id item) {
    return ((int) (OS.objc_msgSend(this.id, sel_levelForItem_, item != null ? item.id : 0)));
  }
}
