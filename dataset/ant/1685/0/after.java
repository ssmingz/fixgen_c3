class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    m_tskDeployer = ((TskDeployer) (componentManager.lookup(ROLE)));
  }
}
