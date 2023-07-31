class PlaceHold {
  private void createComponents() throws Exception {
    createComponent(ConverterRegistry.class, PREFIX + "converter.DefaultConverterRegistry");
    createComponent(ExtensionManager.class, PREFIX + "extensions.DefaultExtensionManager");
    createComponent(MasterConverter.class, PREFIX + "converter.DefaultMasterConverter");
    createComponent(Configurer.class, PREFIX + "configurer.DefaultConfigurer");
    createComponent(TypeManager.class, PREFIX + "type.DefaultTypeManager");
    createComponent(RoleManager.class, PREFIX + "role.DefaultRoleManager");
    createComponent(AspectManager.class, PREFIX + "aspect.DefaultAspectManager");
    createComponent(Deployer.class, PREFIX + "deployer.DefaultDeployer");
    createComponent(ClassLoaderManager.class, PREFIX + "deployer.DefaultClassLoaderManager");
    createComponent(Executor.class, PREFIX + "executor.AspectAwareExecutor");
    createComponent(ServiceManager.class, PREFIX + "service.DefaultServiceManager");
  }
}
