class PlaceHold {
  public void setJustify(boolean justify) {
    checkLayout();
    if (justify == this.justify) {
      return;
    }
    freeRuns();
    this.justify = justify;
  }
}
