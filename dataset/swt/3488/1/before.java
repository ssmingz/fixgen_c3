class PlaceHold {
  public void paste() {
    checkWidget();
    if (fMenuHandle == 0) {
      Clipboard clipboard = new Clipboard(getDisplay());
      TextTransfer textTransfer = TextTransfer.getInstance();
      String clipBoard = ((String) (clipboard.getContents(textTransfer)));
      clipboard.dispose();
      _replaceTextSelection(clipBoard);
    }
  }
}
