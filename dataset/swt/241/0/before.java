class PlaceHold {
  boolean getEditable(TableItem item, int column) {
    if (!(item.getData() instanceof Node)) {
      return false;
    }
    String attribName = item.getText();
    return attribName.startsWith("swt_");
  }
}
