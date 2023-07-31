class PlaceHold {
  public void setFont(Font font) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (font == null) {
      font = data.device.systemFont;
    }
    if (font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    data.font = font;
    if (data.renderTable != 0) {
      OS.XmRenderTableFree(data.renderTable);
    }
    data.renderTable = 0;
    data.stringWidth = data.stringHeight = data.textWidth = data.textHeight = -1;
  }
}
