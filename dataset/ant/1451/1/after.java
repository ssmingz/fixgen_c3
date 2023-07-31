class PlaceHold {
  protected TypeManager getTypeManager() throws ServiceException {
    return ((TypeManager) (getServiceManager().lookup(ROLE)));
  }
}
