class PlaceHold {
  void modifyContent(Event event, boolean updateCaret) {
    event.doit = true;
    notifyListeners(Verify, event);
    if (event.doit) {
      StyledTextEvent styledTextEvent = null;
      int replacedLength = event.end - event.start;
      boolean isCharacterRemove = (replacedLength == 1) && (event.text.length() == 0);
      boolean isBackspace = event.start < caretOffset;
      boolean isDirectionBoundary = false;
      if ((updateCaret && isBidi()) && isCharacterRemove) {
        int line = content.getLineAtOffset(caretOffset);
        int lineStartOffset = content.getOffsetAtLine(line);
        int offsetInLine = caretOffset - lineStartOffset;
        String lineText = content.getLine(line);
        GC gc = getGC();
        StyledTextBidi bidi =
            new StyledTextBidi(gc, lineText, getBidiSegments(lineStartOffset, lineText));
        if (isBackspace) {
          if (offsetInLine > 0) {
            isDirectionBoundary =
                (offsetInLine < lineText.length())
                    && ((bidi.isRightToLeft(offsetInLine) != bidi.isRightToLeft(offsetInLine - 1))
                        || (bidi.isLocalNumber(offsetInLine)
                            != bidi.isLocalNumber(offsetInLine - 1)));
            bidi.setKeyboardLanguage(offsetInLine - 1);
          }
        } else if (offsetInLine < lineText.length()) {
          isDirectionBoundary =
              (offsetInLine > 0)
                  && ((bidi.isRightToLeft(offsetInLine) != bidi.isRightToLeft(offsetInLine - 1))
                      || (bidi.isLocalNumber(offsetInLine)
                          != bidi.isLocalNumber(offsetInLine - 1)));
          bidi.setKeyboardLanguage(offsetInLine);
        }
        gc.dispose();
      }
      if (isListening(ExtendedModify)) {
        styledTextEvent = new StyledTextEvent(logicalContent);
        styledTextEvent.start = event.start;
        styledTextEvent.end = event.start + event.text.length();
        styledTextEvent.text = content.getTextRange(event.start, replacedLength);
      }
      content.replaceTextRange(event.start, replacedLength, event.text);
      if (updateCaret) {
        internalSetSelection(event.start + event.text.length(), 0, true);
        if (isBidi()) {
          if (isCharacterRemove) {
            updateBidiDirection(isBackspace, isDirectionBoundary);
          } else {
            lastCaretDirection = ST.COLUMN_NEXT;
          }
          showBidiCaret();
        } else {
          showCaret();
        }
      }
      notifyListeners(Modify, event);
      if (isListening(ExtendedModify)) {
        notifyListeners(ExtendedModify, styledTextEvent);
      }
    }
  }
}
