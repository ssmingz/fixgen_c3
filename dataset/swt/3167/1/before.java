class PlaceHold {
  void drawText(String label, GC gc, Point position, int index) {
    Table parent = getParent();
    int textOffset;
    int alignmentOffset;
    if (label != null) {
      boolean drawSelection =
          ((index == TableColumn.FIRST) || ((parent.getStyle() & SWT.FULL_SELECTION) != 0))
              && (((parent.style & SWT.HIDE_SELECTION) == 0) || parent.isFocusControl());
      if ((isSelected() == true) && (drawSelection == true)) {
        gc.setForeground(getSelectionForegroundColor());
      } else {
        gc.setForeground(getForeground(index));
      }
      alignmentOffset = getAlignmentOffset(index, getBounds(index).width, gc);
      textOffset = (parent.getItemHeight() - parent.getFontHeight()) / 2;
      gc.drawString(label, position.x + alignmentOffset, position.y + textOffset, true);
    }
  }
}
