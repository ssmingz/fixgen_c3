class PlaceHold {
  public String getClassName() {
    if (isReference()) {
      return ((AntFilterReader) (getCheckedRef())).getClassName();
    }
    dieOnCircularReference();
    return className;
  }
}
