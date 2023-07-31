class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return;
    }
    if (index == 0) {
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(this.image)) {
          return;
        }
      }
      width = -1;
      super.setImage(image);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 <= index) && (index < count)) {
      if (images == null) {
        images = new Image[count];
      }
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(images[index])) {
          return;
        }
      }
      images[index] = image;
    }
    NSTableView tableView = ((NSTableView) (parent.view));
    if (parent.columnCount == 0) {
      index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
    } else {
      TableColumn column = parent.getColumn(index);
      index = ((int) (tableView.columnWithIdentifier(column.nsColumn)));
    }
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    tableView.setNeedsDisplayInRect(rect);
  }
}
