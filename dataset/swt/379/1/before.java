class PlaceHold {
  public Rectangle getBounds() {
    checkDevice();
    PMRect paperRect = new PMRect();
    OS.PMGetAdjustedPaperRect(pageFormat, paperRect);
    return new Rectangle(
        0,
        0,
        ((int) (paperRect.right - paperRect.left)),
        ((int) (paperRect.bottom - paperRect.top)));
  }
}
