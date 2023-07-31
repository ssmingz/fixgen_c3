class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    m_tskDeployer =
        ((TskDeployer)
            (componentManager.lookup("org.apache.myrmidon.components.deployer.TskDeployer")));
    m_converterEngine =
        ((ConverterEngine)
            (componentManager.lookup("org.apache.ant.convert.engine.ConverterEngine")));
  }
}
