class PlaceHold {
  public void setMenuBar(Menu menu) {
    checkWidget();
    if ((menu != null) && menu.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
  }
}
