class PlaceHold {
  public int getItemCount() {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    return parent.getItemCount(this);
  }
}
