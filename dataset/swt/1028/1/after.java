class PlaceHold {
  public void setGrayed(boolean grayed) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    if (this.grayed == grayed) {
      return;
    }
    this.grayed = grayed;
    redraw();
  }
}
