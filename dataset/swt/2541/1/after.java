class PlaceHold {
  public void deselectAll() {
    checkWidget();
    TreeItem[] oldSelection = selectedItems;
    selectedItems = new TreeItem[0];
    for (int i = 0; i < oldSelection.length; i++) {
      redrawItem(oldSelection[i].availableIndex, true);
    }
  }
}
