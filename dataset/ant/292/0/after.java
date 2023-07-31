class TraXLiaison {
  public TraXLiaison() throws Exception {
    tfactory = TransformerFactory.newInstance();
    tfactory.setErrorListener(this);
  }
}
