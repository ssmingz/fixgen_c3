class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    Table parent = getParent();
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellFont == null) {
      cellFont = new Font[count];
    }
    if (cellFont[index] == font) {
      return;
    }
    if ((cellFont[index] != null) && cellFont[index].equals(font)) {
      return;
    }
    cellFont[index] = font;
    clearTextWidths(index);
    redraw();
  }
}
