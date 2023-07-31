class PlaceHold {
  public void fatalError(final SAXParseException spe) {
    m_failed = true;
    getLogger().error(getMessage(spe), spe);
  }
}
