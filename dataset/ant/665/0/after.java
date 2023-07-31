class PlaceHold {
  private void createComponents() throws Exception {
    String component = null;
    component = getParameter("org.apache.ant.convert.engine.ConverterEngine");
    m_converterEngine = ((ConverterEngine) (createComponent(component, ConverterEngine.class)));
    component = getParameter("org.apache.ant.tasklet.engine.DataTypeEngine");
    m_dataTypeEngine = ((DataTypeEngine) (createComponent(component, DataTypeEngine.class)));
    component = getParameter(ROLE);
    m_executor = ((Executor) (createComponent(component, Executor.class)));
    component = getParameter(ROLE);
    m_projectManager = ((ProjectManager) (createComponent(component, ProjectManager.class)));
    component = getParameter(ROLE);
    m_builder = ((ProjectBuilder) (createComponent(component, ProjectBuilder.class)));
    component = getParameter(ROLE);
    m_deployer = ((TskDeployer) (createComponent(component, TskDeployer.class)));
    component = getParameter(ROLE);
    m_configurer = ((Configurer) (createComponent(component, Configurer.class)));
  }
}
