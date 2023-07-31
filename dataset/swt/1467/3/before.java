class PlaceHold {
  void handleTextChanging(TextChangingEvent event) {
    int firstLine;
    int textChangeY;
    boolean isMultiLineChange = (event.replaceLineCount > 0) || (event.newLineCount > 0);
    if (event.replaceCharCount < 0) {
      event.start += event.replaceCharCount;
      event.replaceCharCount *= -1;
    }
    lastTextChangeStart = event.start;
    lastTextChangeNewLineCount = event.newLineCount;
    lastTextChangeNewCharCount = event.newCharCount;
    lastTextChangeReplaceLineCount = event.replaceLineCount;
    lastTextChangeReplaceCharCount = event.replaceCharCount;
    firstLine = content.getLineAtOffset(event.start);
    textChangeY = ((firstLine * lineHeight) - verticalScrollOffset) + topMargin;
    if (isMultiLineChange) {
      redrawMultiLineChange(textChangeY, event.newLineCount, event.replaceLineCount);
    }
    if (defaultLineStyler != null) {
      defaultLineStyler.textChanging(event);
    }
    int newEndOfText = (content.getCharCount() - event.replaceCharCount) + event.newCharCount;
    if (caretOffset > newEndOfText) {
      caretOffset = newEndOfText;
    }
  }
}
