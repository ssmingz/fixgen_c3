class PlaceHold {
  int tableView_objectValueForTableColumn_row(
      int id, int sel, int aTableView, int aTableColumn, int rowIndex) {
    NSAttributedString attribStr =
        createString(items[((int) (rowIndex))], null, foreground, 0, false, true, false);
    attribStr.autorelease();
    return attribStr.id;
  }
}
