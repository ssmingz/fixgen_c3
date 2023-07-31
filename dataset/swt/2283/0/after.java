class PlaceHold {
  public void setItemCount(int count) {
    checkWidget();
    checkItems();
    count = Math.max(0, count);
    setItemCount(null, count);
  }
}
