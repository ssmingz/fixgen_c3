class PlaceHold {
  public void setExpandHorizontal(boolean expand) {
    checkWidget();
    if (expand == expandHorizontal) {
      return;
    }
    expandHorizontal = expand;
    layout();
  }
}
