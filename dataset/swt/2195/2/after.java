class PlaceHold {
  void doDeleteWordPrevious() {
    if (selection.x != selection.y) {
      doBackspace();
    } else {
      Event event = new Event();
      event.text = "";
      event.start = getWordPrevious(caretOffset, MOVEMENT_WORD);
      event.end = caretOffset;
      sendKeyEvent(event);
    }
  }
}
