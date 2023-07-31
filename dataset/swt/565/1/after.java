class PlaceHold {
  public void deselectAll() {
    checkWidget();
    TreeItem2[] oldSelection = selectedItems;
    selectedItems = new TreeItem2[0];
    for (int i = 0; i < oldSelection.length; i++) {
      redrawItem(oldSelection[i].availableIndex);
    }
  }
}
