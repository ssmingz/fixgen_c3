class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    background = new Color(getDisplay(), color.getRGB());
    redraw();
  }
}
