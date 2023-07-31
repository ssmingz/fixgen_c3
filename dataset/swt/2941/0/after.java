class PlaceHold {
  void HandlePreviewTextInput(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    sendKeyEvent(KeyDown, e, true);
  }
}
