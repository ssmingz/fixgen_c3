class PlaceHold {
  void setCaretOffset(int offset, int alignment) {
    if (caretOffset != offset) {
      caretOffset = offset;
      if (isListening(CaretMoved)) {
        StyledTextEvent event = new StyledTextEvent(content);
        event.end = caretOffset;
        notifyListeners(CaretMoved, event);
      }
    }
    if (alignment != SWT.DEFAULT) {
      caretAlignment = alignment;
    }
  }
}
