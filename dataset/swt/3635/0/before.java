class PlaceHold {
  void handleResize(Event event) {
    int oldHeight = clientAreaHeight;
    clientAreaHeight = getClientArea().height;
    if (clientAreaHeight > oldHeight) {
      int lineCount = content.getLineCount();
      int oldBottomIndex = topIndex + (oldHeight / lineHeight);
      int newItemCount = ((int) (Math.ceil(((float) (clientAreaHeight - oldHeight)) / lineHeight)));
      oldBottomIndex = Math.min(oldBottomIndex, lineCount);
      newItemCount = Math.min(newItemCount, lineCount - oldBottomIndex);
      calculateContentWidth(oldBottomIndex, newItemCount);
    }
    setScrollBars();
    claimBottomFreeSpace();
    claimRightFreeSpace();
  }
}
