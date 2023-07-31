class PlaceHold {
  private ExecutionFrame createExecutionFrame(final Project project) throws TaskException {
    final DefaultComponentManager componentManager =
        new DefaultComponentManager(m_componentManager);
    final TypeManager typeManager = m_typeManager.createChildTypeManager();
    componentManager.put(ROLE, typeManager);
    final Deployer deployer;
    try {
      deployer = m_deployer.createChildDeployer(componentManager);
      componentManager.put(ROLE, deployer);
    } catch (ComponentException e) {
      throw new TaskException(e.getMessage(), e);
    }
    deployTypeLib(deployer, project);
    componentManager.put(ROLE, this);
    componentManager.put(ROLE, project);
    final String[] names = project.getProjectNames();
    for (int i = 0; i < names.length; i++) {
      final String name = names[i];
      final Project other = project.getProject(name);
      componentManager.put((Project.ROLE + "/") + name, other);
    }
    final DefaultTaskContext context = new DefaultTaskContext(m_baseContext, componentManager);
    context.setProperty(BASE_DIRECTORY, project.getBaseDirectory());
    final DefaultExecutionFrame frame = new DefaultExecutionFrame();
    try {
      final Logger logger = new LogKitLogger(m_hierarchy.getLoggerFor("project" + m_projectID));
      m_projectID++;
      frame.enableLogging(logger);
      frame.contextualize(context);
    } catch (final Exception e) {
      final String message = REZ.getString("bad-frame.error");
      throw new TaskException(message, e);
    }
    return frame;
  }
}
