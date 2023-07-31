class PlaceHold {
  void destroyItem(TreeColumn column) {
    int index = 0;
    while (index < columnCount) {
      if (columns[index] == column) {
        break;
      }
      index++;
    }
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if (item != null) {
        if (columnCount <= 1) {
          item.strings = null;
          item.images = null;
          item.cellBackground = null;
          item.cellForeground = null;
          item.cellFont = null;
        } else {
          if (item.strings != null) {
            String[] strings = item.strings;
            if (index == 0) {
              item.text = (strings[1] != null) ? strings[1] : "";
            }
            String[] temp = new String[columnCount - 1];
            System.arraycopy(strings, 0, temp, 0, index);
            System.arraycopy(strings, index + 1, temp, index, (columnCount - 1) - index);
            item.strings = temp;
          } else if (index == 0) {
            item.text = "";
          }
          if (item.images != null) {
            Image[] images = item.images;
            if (index == 0) {
              item.image = images[1];
            }
            Image[] temp = new Image[columnCount - 1];
            System.arraycopy(images, 0, temp, 0, index);
            System.arraycopy(images, index + 1, temp, index, (columnCount - 1) - index);
            item.images = temp;
          } else if (index == 0) {
            item.image = null;
          }
          if (item.cellBackground != null) {
            Color[] cellBackground = item.cellBackground;
            Color[] temp = new Color[columnCount - 1];
            System.arraycopy(cellBackground, 0, temp, 0, index);
            System.arraycopy(cellBackground, index + 1, temp, index, (columnCount - 1) - index);
            item.cellBackground = temp;
          }
          if (item.cellForeground != null) {
            Color[] cellForeground = item.cellForeground;
            Color[] temp = new Color[columnCount - 1];
            System.arraycopy(cellForeground, 0, temp, 0, index);
            System.arraycopy(cellForeground, index + 1, temp, index, (columnCount - 1) - index);
            item.cellForeground = temp;
          }
          if (item.cellFont != null) {
            Font[] cellFont = item.cellFont;
            Font[] temp = new Font[columnCount - 1];
            System.arraycopy(cellFont, 0, temp, 0, index);
            System.arraycopy(cellFont, index + 1, temp, index, (columnCount - 1) - index);
            item.cellFont = temp;
          }
        }
      }
    }
    int oldIndex = ((int) (((NSOutlineView) (view)).columnWithIdentifier(column.nsColumn)));
    if (columnCount == 1) {
      firstColumn = column.nsColumn;
      firstColumn.setWidth(0);
    } else {
      if (index == 0) {
        ((NSOutlineView) (view)).setOutlineTableColumn(columns[1].nsColumn);
      }
      ((NSOutlineView) (view)).removeTableColumn(column.nsColumn);
    }
    System.arraycopy(columns, index + 1, columns, index, (--columnCount) - index);
    columns[columnCount] = null;
    NSArray array = ((NSOutlineView) (view)).tableColumns();
    int arraySize = ((int) (array.count()));
    for (int i = oldIndex; i < arraySize; i++) {
      int columnId = array.objectAtIndex(i).id;
      for (int j = 0; j < columnCount; j++) {
        if (columns[j].nsColumn.id == columnId) {
          columns[j].sendEvent(Move);
          break;
        }
      }
    }
  }
}
