class PlaceHold {
  void doWordPrevious() {
    if ((selection.y - selection.x) > 0) {
      setCaretOffset(selection.x, DEFAULT);
      showCaret();
    } else {
      doSelectionWordPrevious();
    }
  }
}
