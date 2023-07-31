class PlaceHold {
  public void showSelection() {
    checkWidget();
    checkItems();
    TreeItem[] selection = getSelection();
    if (selection.length > 0) {
      showItem(selection[0], true);
    }
  }
}
