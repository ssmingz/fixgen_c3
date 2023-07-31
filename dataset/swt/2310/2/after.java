class PlaceHold {
  void sendVerticalSelection() {
    setFocus();
    if (isDropped()) {
      hideCalendar();
    } else {
      showCalendar();
    }
  }
}
