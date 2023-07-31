class PlaceHold {
  private boolean findEntry() {
    Cursor waitCursor = shell.getDisplay().getSystemCursor(CURSOR_WAIT);
    shell.setCursor(waitCursor);
    boolean matchCase = searchDialog.getMatchCase();
    boolean matchWord = searchDialog.getMatchWord();
    String searchString = searchDialog.getSearchString();
    int column = searchDialog.getSelectedSearchArea();
    searchString = (matchCase) ? searchString : searchString.toLowerCase();
    boolean found = false;
    if (searchDialog.getSearchDown()) {
      for (int i = table.getSelectionIndex() + 1; i < table.getItemCount(); i++) {
        if (found = findMatch(searchString, table.getItem(i), column, matchWord, matchCase)) {
          table.setSelection(i);
          break;
        }
      }
    } else {
      for (int i = table.getSelectionIndex() - 1; i > (-1); i--) {
        if (found = findMatch(searchString, table.getItem(i), column, matchWord, matchCase)) {
          table.setSelection(i);
          break;
        }
      }
    }
    shell.setCursor(null);
    return found;
  }
}
