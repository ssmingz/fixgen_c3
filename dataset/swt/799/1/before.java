class PlaceHold {
  void doContentStart() {
    if (caretOffset > 0) {
      caretOffset = 0;
      showCaret();
    }
  }
}
