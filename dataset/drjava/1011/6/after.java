class PlaceHold {
  public void setSuperClass(SymbolData superClass) {
    assert superClass != null;
    _superClass = superClass;
    addEnclosingData(superClass);
  }
}
