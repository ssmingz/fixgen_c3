class PlaceHold {
  public void redrawRange(int start, int length, boolean clearBackground) {
    checkWidget();
    int firstLine = content.getLineAtOffset(start);
    int lastLine = content.getLineAtOffset(start + length);
    contentWidth.reset(firstLine, (lastLine - firstLine) + 1, true);
    internalRedrawRange(start, length, clearBackground);
  }
}
