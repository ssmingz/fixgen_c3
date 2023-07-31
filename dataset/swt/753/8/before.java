class PlaceHold {
  public void setGrayed(boolean grayed) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    this.grayed = grayed;
  }
}
