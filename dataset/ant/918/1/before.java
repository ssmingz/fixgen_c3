class PlaceHold {
  public void service(final ServiceManager serviceManager) throws ServiceException {
    m_converter = ((MasterConverter) (serviceManager.lookup(ROLE)));
  }
}
