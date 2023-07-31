class PlaceHold {
  public void paste() {
    checkWidget();
    String text = ((String) (getClipboardContent(CLIPBOARD)));
    if ((text != null) && (text.length() > 0)) {
      Event event = new Event();
      event.start = selection.x;
      event.end = selection.y;
      event.text = getModelDelimitedText(text);
      sendKeyEvent(event);
    }
  }
}
