class PlaceHold {
  void redraw(int columnIndex) {
    if (parent.ignoreRedraw || (!isDrawing())) {
      return;
    }
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    NSRect rect;
    if ((((columnIndex == (-1)) || parent.hooks(MeasureItem)) || parent.hooks(EraseItem))
        || parent.hooks(PaintItem)) {
      rect = outlineView.rectOfRow(outlineView.rowForItem(handle));
    } else {
      int index;
      if (parent.columnCount == 0) {
        index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
      } else if ((0 <= columnIndex) && (columnIndex < parent.columnCount)) {
        index = ((int) (outlineView.columnWithIdentifier(parent.columns[columnIndex].nsColumn)));
      } else {
        return;
      }
      rect = outlineView.frameOfCellAtColumn(index, outlineView.rowForItem(handle));
    }
    outlineView.setNeedsDisplayInRect(rect);
  }
}
