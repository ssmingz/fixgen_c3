class PlaceHold {
  public int characterIndexForGlyphAtIndex(int glyphIndex) {
    return OS.objc_msgSend(this.id, sel_characterIndexForGlyphAtIndex_, glyphIndex);
  }
}
