class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    NSBox widget = ((NSBox) (view));
    int border = ((int) (Math.ceil(widget.borderWidth())));
    NSSize margins = widget.contentViewMargins();
    NSRect frame = contentView.frame();
    width += (margins.width + border) * 2;
    height += ((margins.height + border) * 2) + frame.y;
    return super.computeTrim(x, y, width, height);
  }
}
