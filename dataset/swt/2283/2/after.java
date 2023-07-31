class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    super.setRedraw(redraw);
    if (redraw && (drawCount == 0)) {
      checkItems();
      setScrollWidth();
    }
  }
}
