class PlaceHold {
  public int characterIndexForGlyphAtIndex(int glyphIndex) {
    return ((int) (OS.objc_msgSend(this.id, sel_characterIndexForGlyphAtIndex_, glyphIndex)));
  }
}
