class PlaceHold {
  public void to(String to) throws IOException {
    sendRcpt(to);
    this.to.addElement(to);
  }
}
