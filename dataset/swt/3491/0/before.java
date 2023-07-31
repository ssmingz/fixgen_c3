class PlaceHold {
  void updateModal() {
    if (!display.TrimEnabled) {
      super.setEnabled(isActive());
    } else {
      setItemEnabled(SC_CLOSE, isActive());
    }
  }
}
