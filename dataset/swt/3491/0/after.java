class PlaceHold {
  void updateModal() {
    if (!Display.TrimEnabled) {
      super.setEnabled(isActive());
    } else {
      setItemEnabled(SC_CLOSE, isActive());
    }
  }
}
