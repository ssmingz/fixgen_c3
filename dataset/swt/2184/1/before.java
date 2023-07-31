class PlaceHold {
  void doContent(char key) {
    Event event = new Event();
    event.start = selection.x;
    event.end = selection.y;
    if ((key == SWT.CR) || (key == SWT.LF)) {
      if (!isSingleLine()) {
        event.text = getLineDelimiter();
      }
    } else if (((selection.x == selection.y) && overwrite) && (key != TAB)) {
      int lineIndex = content.getLineAtOffset(event.end);
      int lineOffset = content.getOffsetAtLine(lineIndex);
      String line = content.getLine(lineIndex);
      if (event.end < (lineOffset + line.length())) {
        event.end++;
      }
      event.text = new String(new char[] {key});
    } else {
      event.text = new String(new char[] {key});
    }
    if (event.text != null) {
      if ((textLimit > 0) && ((content.getCharCount() - (event.end - event.start)) >= textLimit)) {
        return;
      }
      sendKeyEvent(event);
    }
  }
}
