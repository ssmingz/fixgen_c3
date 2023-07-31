class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    final Executor executor =
        ((Executor) (componentManager.lookup("org.apache.myrmidon.components.executor.Executor")));
    m_taskRegistry = executor.getRegistry();
    final ConverterEngine converterEngine =
        ((ConverterEngine)
            (componentManager.lookup("org.apache.ant.convert.engine.ConverterEngine")));
    m_converterInfoRegistry = converterEngine.getInfoRegistry();
    m_converterRegistry = converterEngine.getRegistry();
    final DataTypeEngine dataTypeEngine =
        ((DataTypeEngine)
            (componentManager.lookup("org.apache.ant.tasklet.engine.DataTypeEngine")));
    m_dataTypeRegistry = dataTypeEngine.getRegistry();
  }
}
