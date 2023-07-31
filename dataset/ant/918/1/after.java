class PlaceHold {
  public void service(final ServiceManager serviceManager) throws ServiceException {
    m_converter = ((Converter) (serviceManager.lookup(ROLE)));
  }
}
