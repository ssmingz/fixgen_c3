class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return parent.getItemCount(this);
  }
}
