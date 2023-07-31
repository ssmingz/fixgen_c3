class PlaceHold {
  void setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    if ((style & SWT.READ_ONLY) == 0) {
      NSControl widget = ((NSControl) (view));
      NSRect rect = new NSRect();
      rect.width = rect.height = Float.MAX_VALUE;
      NSSize size = widget.cell().cellSizeForBounds(rect);
      height = Math.min(height, ((int) (Math.ceil(size.height))));
    }
    super.setBounds(x, y, width, height, move, resize);
  }
}
