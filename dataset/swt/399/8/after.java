class PlaceHold {
  void setExampleWidgetFont() {
    if (colorAndFontTable == null) {
      return;
    }
    Control[] controls = getExampleControls();
    if (!instance.startup) {
      for (int i = 0; i < controls.length; i++) {
        controls[i].setFont(font);
      }
    }
    Font ft = font;
    if (controls.length == 0) {
      return;
    }
    if (ft == null) {
      ft = controls[0].getFont();
    }
    TableItem item = colorAndFontTable.getItem(FONT);
    Image oldImage = item.getImage();
    if (oldImage != null) {
      oldImage.dispose();
    }
    item.setImage(fontImage(ft));
    item.setFont(ft);
    colorAndFontTable.layout();
  }
}
