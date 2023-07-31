class PlaceHold {
  public void setName(String name) {
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.name = name;
    this.string = null;
  }
}
