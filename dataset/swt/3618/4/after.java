class PlaceHold {
  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (isDisposed()) {
      return;
    }
    if (visible && (pendingShowItem != null)) {
      showItem(pendingShowItem);
      pendingShowItem = null;
    }
  }
}
