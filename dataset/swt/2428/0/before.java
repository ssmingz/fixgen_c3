class PlaceHold {
  void doWordPrevious() {
    if ((selection.y - selection.x) > 0) {
      caretOffset = selection.x;
      showCaret();
    } else {
      doSelectionWordPrevious();
    }
  }
}
