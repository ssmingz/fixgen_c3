class PlaceHold {
  void doDeleteWordPrevious() {
    if (selection.x != selection.y) {
      doBackspace();
    } else {
      Event event = new Event();
      event.text = "";
      event.start = getWordStart(caretOffset);
      event.end = caretOffset;
      sendKeyEvent(event);
    }
  }
}
