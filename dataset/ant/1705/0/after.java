class PlaceHold {
  public void addText(String value) {
    this.value += getProject().replaceProperties(value);
  }
}
