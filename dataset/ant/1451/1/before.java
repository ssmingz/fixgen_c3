class PlaceHold {
  protected TypeManager getTypeManager() throws ComponentException {
    return ((TypeManager) (getComponentManager().lookup(ROLE)));
  }
}
