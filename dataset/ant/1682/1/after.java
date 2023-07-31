class PlaceHold {
  private Parameters createDefaultParameters() {
    final Parameters defaults = new Parameters();
    defaults.setParameter("myrmidon.bin.path", "bin");
    defaults.setParameter("myrmidon.lib.path", "lib");
    defaults.setParameter(
        "org.apache.ant.convert.engine.ConverterEngine",
        "org.apache.ant.convert.engine.DefaultConverterEngine");
    defaults.setParameter(
        "org.apache.ant.tasklet.engine.DataTypeEngine",
        "org.apache.ant.tasklet.engine.DefaultDataTypeEngine");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.type.DefaultTypeManager");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.executor.DefaultExecutor");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.manager.DefaultProjectManager");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.builder.DefaultProjectBuilder");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.deployer.DefaultTskDeployer");
    defaults.setParameter(ROLE, "org.apache.myrmidon.components.configurer.DefaultConfigurer");
    return defaults;
  }
}
