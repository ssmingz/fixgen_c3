class PlaceHold {
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (isDisposed()) {
      return;
    }
    if (!visible) {
      popup.setVisible(false);
    }
  }
}
