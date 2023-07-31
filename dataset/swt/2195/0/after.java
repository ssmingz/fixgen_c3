class PlaceHold {
  void doDeleteWordNext() {
    if (selection.x != selection.y) {
      doDelete();
    } else {
      Event event = new Event();
      event.text = "";
      event.start = caretOffset;
      event.end = getWordNext(caretOffset, MOVEMENT_WORD);
      sendKeyEvent(event);
    }
  }
}
