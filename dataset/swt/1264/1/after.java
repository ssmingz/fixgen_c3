class PlaceHold {
  public int glyphIndexForCharacterAtIndex(int charIndex) {
    return ((int) (OS.objc_msgSend(this.id, sel_glyphIndexForCharacterAtIndex_, charIndex)));
  }
}
