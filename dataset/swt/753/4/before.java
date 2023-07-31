class PlaceHold {
  void itemChanged(SelectableItem changedItem, int repaintStartX, int repaintWidth) {
    super.itemChanged(changedItem, repaintStartX, repaintWidth);
    if ((firstColumnImage == false) && (changedItem.getImage() != null)) {
      firstColumnImage = true;
    }
    setFirstColumnWidth(((TableItem) (changedItem)));
  }
}
