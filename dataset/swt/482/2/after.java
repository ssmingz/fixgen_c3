class PlaceHold {
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (isDisposed()) {
      return;
    }
    if ((popup == null) || popup.isDisposed()) {
      return;
    }
    if (!visible) {
      popup.setVisible(false);
    }
  }
}
