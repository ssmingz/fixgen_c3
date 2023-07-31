class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    float width = 0;
    float height = 0;
    String string = Double.toString(buttonView.maxValue());
    Font font = Font.cocoa_new(display, textView.font());
    NSAttributedString str = parent.createString(string, font, null, 0, false, true, false);
    NSSize size = str.size();
    str.release();
    width = ((float) (size.width));
    height = ((float) (size.height));
    NSRect frameRect = textView.frame();
    NSCell cell = new NSCell(textView.cell());
    NSRect cellRect = cell.drawingRectForBounds(frameRect);
    width += frameRect.width - cellRect.width;
    height += frameRect.height - cellRect.height;
    width += GAP;
    size = buttonView.cell().cellSize();
    width += ((int) (size.width));
    height = Math.max(height, size.height);
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    Rectangle trim = computeTrim(0, 0, ((int) (Math.ceil(width))), ((int) (Math.ceil(height))));
    return new Point(trim.width, trim.height);
  }
}
