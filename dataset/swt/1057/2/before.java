class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    return (state & HIDDEN) == 0;
  }
}
