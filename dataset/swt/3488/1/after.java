class PlaceHold {
  public void paste() {
    checkWidget();
    if (menuHandle == 0) {
      Clipboard clipboard = new Clipboard(getDisplay());
      TextTransfer textTransfer = TextTransfer.getInstance();
      String clipBoard = ((String) (clipboard.getContents(textTransfer)));
      clipboard.dispose();
      _replaceTextSelection(clipBoard);
    }
  }
}
