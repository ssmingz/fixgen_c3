class PlaceHold {
  void handleMouseDown(Event event) {
    forceFocus();
    if (dragDetect && checkDragDetect(event)) {
      return;
    }
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
    clickCount = event.count;
    if (clickCount == 1) {
      boolean select = (event.stateMask & SWT.MOD2) != 0;
      doMouseLocationChange(event.x, event.y, select);
    } else if (doubleClickEnabled) {
      clearSelection(false);
      int offset = getOffsetAtPoint(event.x, event.y);
      int lineIndex = content.getLineAtOffset(offset);
      int lineOffset = content.getOffsetAtLine(lineIndex);
      int lineEnd = content.getCharCount();
      if ((lineIndex + 1) < content.getLineCount()) {
        lineEnd = content.getOffsetAtLine(lineIndex + 1);
      }
      int start;
      int end;
      if ((clickCount & 1) == 0) {
        start = Math.max(0, getWordPrevious(offset, MOVEMENT_WORD_START));
        end = Math.min(content.getCharCount(), getWordNext(start, MOVEMENT_WORD_END));
      } else {
        start = lineOffset;
        end = lineEnd;
      }
      selection.x = selection.y = start;
      selectionAnchor = -1;
      caretOffset = end;
      showCaret();
      doMouseSelection();
      doubleClickSelection = new Point(selection.x, selection.y);
    }
  }
}
