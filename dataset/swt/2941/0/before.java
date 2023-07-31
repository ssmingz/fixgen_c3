class PlaceHold {
  void HandleTextInput(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    sendKeyEvent(KeyDown, e, true);
  }
}
