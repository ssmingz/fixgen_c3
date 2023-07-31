class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    return (state & DISABLED) == 0;
  }
}
