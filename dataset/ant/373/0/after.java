class PlaceHold {
  private void setupComponents() throws Exception {
    createComponent(ExtensionManager.class, PREFIX + "extensions.DefaultExtensionManager");
    final Object converter =
        createComponent(Converter.class, PREFIX + "converter.DefaultMasterConverter");
    m_serviceManager.put(ROLE, converter);
    createComponent(Configurer.class, PREFIX + "configurer.DefaultConfigurer");
    createComponent(TypeManager.class, PREFIX + "type.DefaultTypeManager");
    createComponent(RoleManager.class, PREFIX + "role.DefaultRoleManager");
    createComponent(AspectManager.class, PREFIX + "aspect.DefaultAspectManager");
    createComponent(Deployer.class, PREFIX + "deployer.DefaultDeployer");
    createComponent(ClassLoaderManager.class, PREFIX + "classloader.DefaultClassLoaderManager");
    createComponent(Executor.class, PREFIX + "executor.AspectAwareExecutor");
    createComponent(PropertyResolver.class, PREFIX + "property.DefaultPropertyResolver");
    m_serviceManager.put(ROLE, this);
    for (Iterator iterator = m_components.iterator(); iterator.hasNext(); ) {
      final Object component = iterator.next();
      setupObject(component, m_serviceManager, null);
    }
  }
}
