class PlaceHold {
  void sendVerticalSelection() {
    if (isDropped()) {
      hideCalendar();
    } else {
      showCalendar();
    }
  }
}
