class PlaceHold {
  public boolean isExists() {
    if (isReference()) {
      return ((Resource) (getCheckedRef())).isExists();
    }
    return (exists == null) || exists.booleanValue();
  }
}
