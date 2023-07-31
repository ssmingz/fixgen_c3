class PlaceHold {
  public void paste() {
    checkWidget();
    String text = ((String) (getClipboardContent(CLIPBOARD)));
    if ((text != null) && (text.length() > 0)) {
      if (blockSelection && (blockXLocation != (-1))) {
        insertBlockSelectionText(getModelDelimitedText(text), NULL);
        clearBlockSelection();
        return;
      }
      Event event = new Event();
      event.start = selection.x;
      event.end = selection.y;
      event.text = getModelDelimitedText(text);
      sendKeyEvent(event);
    }
  }
}
