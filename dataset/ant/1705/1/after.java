class PlaceHold {
  public void addText(String text) {
    this.text = getProject().replaceProperties(text);
  }
}
