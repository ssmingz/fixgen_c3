class PlaceHold {
  private void setupComponents() throws Exception {
    createComponent(ConverterRegistry.class, PREFIX + "converter.DefaultConverterRegistry");
    createComponent(ExtensionManager.class, PREFIX + "extensions.DefaultExtensionManager");
    createComponent(Converter.class, PREFIX + "converter.DefaultMasterConverter");
    createComponent(Configurer.class, PREFIX + "configurer.DefaultConfigurer");
    createComponent(TypeManager.class, PREFIX + "type.DefaultTypeManager");
    createComponent(RoleManager.class, PREFIX + "role.DefaultRoleManager");
    createComponent(AspectManager.class, PREFIX + "aspect.DefaultAspectManager");
    createComponent(Deployer.class, PREFIX + "deployer.DefaultDeployer");
    createComponent(ClassLoaderManager.class, PREFIX + "deployer.DefaultClassLoaderManager");
    createComponent(Executor.class, PREFIX + "executor.AspectAwareExecutor");
    createComponent(PropertyResolver.class, PREFIX + "workspace.DefaultPropertyResolver");
    for (Iterator iterator = m_components.iterator(); iterator.hasNext(); ) {
      final Object component = iterator.next();
      setupObject(component, m_serviceManager, m_parameters);
    }
  }
}
