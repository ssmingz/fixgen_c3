class PlaceHold {
  public void cc(String cc) throws IOException {
    sendRcpt(cc);
    this.cc.addElement(cc);
  }
}
