class PlaceHold {
  void handleMouseDown(Event event) {
    mouseDown = true;
    mouseDoubleClick = false;
    setFocus();
    if (event.button == 2) {
      String text = ((String) (getClipboardContent(SELECTION_CLIPBOARD)));
      if ((text != null) && (text.length() > 0)) {
        doMouseLocationChange(event.x, event.y, false);
        Event e = new Event();
        e.start = selection.x;
        e.end = selection.y;
        e.text = getModelDelimitedText(text);
        sendKeyEvent(e);
      }
    }
    if ((event.button != 1) || (IS_CARBON && ((event.stateMask & SWT.MOD4) != 0))) {
      return;
    }
    boolean select = (event.stateMask & SWT.MOD2) != 0;
    doMouseLocationChange(event.x, event.y, select);
  }
}
