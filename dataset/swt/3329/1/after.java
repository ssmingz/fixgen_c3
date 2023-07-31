class PlaceHold {
  public Font getFont() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    return Font.motif_new(data.device, data.font.handle);
  }
}
