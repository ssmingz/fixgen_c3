class PlaceHold {
  private Parameters createDefaultParameters() {
    final Parameters defaults = new Parameters();
    defaults.setParameter("myrmidon.bin.path", "bin");
    defaults.setParameter("myrmidon.lib.path", "lib");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.deployer.DefaultRoleManager");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.converter.DefaultMasterConverter");
    defaults.setParameter(
        ROLE, "org.apache.myrmidon.components.converter.DefaultConverterRegistry");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.type.DefaultTypeManager");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.executor.DefaultExecutor");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.manager.DefaultProjectManager");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.builder.DefaultProjectBuilder");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.deployer.DefaultTskDeployer");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.configurer.DefaultConfigurer");
    return defaults;
  }
}
