class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return parent.getItemCount(handle);
  }
}
