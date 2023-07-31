class PlaceHold {
  private void createComponents() throws Exception {
    String component = null;
    component = getParameter("myrmidon.comp.converter");
    m_converterEngine = ((ConverterEngine) (createComponent(component, ConverterEngine.class)));
    component = getParameter("myrmidon.comp.datatype");
    m_dataTypeEngine = ((DataTypeEngine) (createComponent(component, DataTypeEngine.class)));
    component = getParameter("myrmidon.comp.task");
    m_executor = ((Executor) (createComponent(component, Executor.class)));
    component = getParameter("myrmidon.comp.project");
    m_projectManager = ((ProjectManager) (createComponent(component, ProjectManager.class)));
    component = getParameter("myrmidon.comp.builder");
    m_builder = ((ProjectBuilder) (createComponent(component, ProjectBuilder.class)));
    component = getParameter("myrmidon.comp.deployer");
    m_deployer = ((TskDeployer) (createComponent(component, TskDeployer.class)));
    component = getParameter("myrmidon.comp.configurer");
    m_configurer = ((Configurer) (createComponent(component, Configurer.class)));
  }
}
