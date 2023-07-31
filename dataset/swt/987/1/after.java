class PlaceHold {
  public Rectangle getChevronBounds() {
    checkWidget();
    return new Rectangle(chevronRect.x, chevronRect.y, chevronRect.width, chevronRect.height);
  }
}
