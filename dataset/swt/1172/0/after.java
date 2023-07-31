class PlaceHold {
  int getPartialBottomIndex() {
    if (isFixedLineHeight()) {
      int lineHeight = renderer.getLineHeight();
      int partialLineCount = Compatibility.ceil(clientAreaHeight, lineHeight);
      return Math.max(0, Math.min(content.getLineCount(), topIndex + partialLineCount) - 1);
    }
    return getLineIndex(clientAreaHeight - bottomMargin);
  }
}
