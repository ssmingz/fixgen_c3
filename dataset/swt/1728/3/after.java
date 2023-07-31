class PlaceHold {
  public boolean getEditable() {
    checkWidget();
    return (style & SWT.READ_ONLY) == 0;
  }
}
