class PlaceHold {
  public int getWidth() {
    checkWidget();
    int width = ((int) (nsColumn.width()));
    if (width > 0) {
      width += Table.CELL_GAP;
    }
    return width;
  }
}
