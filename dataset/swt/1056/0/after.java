class PlaceHold {
  void initializeWidgets() {
    Display display = shell.getDisplay();
    addFonts(display.getFontList(null, false));
    addFonts(display.getFontList(null, true));
    setItemsSorted(charSetCombo, getFonts());
    if (initialFontData != null) {
      Font initialFont = new Font(display, initialFontData);
      initialFontData = null;
      ignoreEvents = true;
      setFontCombos(initialFont.getFontData()[0]);
      ignoreEvents = false;
      initialFont.dispose();
      updateSampleFont();
    }
  }
}
