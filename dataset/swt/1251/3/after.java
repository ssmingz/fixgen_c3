class PlaceHold {
  public void setTabs(int tabs) {
    checkWidget();
    tabLength = tabs;
    renderer.setTabLength(tabLength);
    if (caretOffset > 0) {
      caretOffset = 0;
      getAccessible().textCaretMoved(caretOffset);
      if (isBidi()) {
        showBidiCaret();
      } else {
        showCaret();
      }
      clearSelection(false);
    }
    lineCache.reset(0, content.getLineCount(), false);
    redraw();
  }
}
