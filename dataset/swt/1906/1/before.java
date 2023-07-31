class PlaceHold {
  public void paint(GC gc, int width, int height) {
    if (!example.checkAdvancedGraphics()) {
      return;
    }
    Device device = gc.getDevice();
    String fontFace = ((String) (fontNames.get(fontFaceCb.getSelectionIndex())));
    int points = fontPointSpinner.getSelection();
    int style = styleValues[fontStyleCb.getSelectionIndex()];
    Font font = new Font(device, fontFace, points, style);
    gc.setFont(font);
    gc.setTextAntialias(ON);
    Point size = gc.stringExtent(text);
    int textWidth = size.x;
    int textHeight = size.y;
    Pattern pattern = null;
    if (fontForeground.getBgColor1() != null) {
      gc.setForeground(fontForeground.getBgColor1());
    } else if (fontForeground.getBgImage() != null) {
      pattern = new Pattern(device, fontForeground.getBgImage());
      gc.setForegroundPattern(pattern);
    }
    gc.drawString(text, (width - textWidth) / 2, (height - textHeight) / 2);
    font.dispose();
    if (pattern != null) {
      pattern.dispose();
    }
  }
}
