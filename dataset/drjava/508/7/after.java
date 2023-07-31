class PlaceHold {
  public int getDistToNextNewline() {
    TokenList.Iterator copyCursor = _cursor.copy();
    if (copyCursor.atStart()) {
      copyCursor.next();
    }
    if (copyCursor.atEnd() || copyCursor.current().getType().equals("\n")) {
      return 0;
    }
    int walkcount = copyCursor.current().getSize() - _offset;
    copyCursor.next();
    while ((!copyCursor.atEnd()) && (!copyCursor.current().getType().equals("\n"))) {
      walkcount += copyCursor.current().getSize();
      copyCursor.next();
    }
    return walkcount;
  }
}
