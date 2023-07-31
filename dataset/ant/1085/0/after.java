class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    m_componentManager = ((DefaultComponentManager) (componentManager));
    m_executor = ((Executor) (componentManager.lookup(ROLE)));
  }
}
