class PlaceHold {
  private Parameters createDefaultParameters() {
    final Parameters defaults = new Parameters();
    defaults.setParameter("myrmidon.bin.path", "bin");
    defaults.setParameter("myrmidon.lib.path", "lib");
    defaults.setParameter(
        "myrmidon.comp.converter", "org.apache.ant.convert.engine.DefaultConverterEngine");
    defaults.setParameter(
        "myrmidon.comp.datatype", "org.apache.ant.tasklet.engine.DefaultDataTypeEngine");
    defaults.setParameter(
        "myrmidon.comp.task", "org.apache.myrmidon.components.executor.DefaultExecutor");
    defaults.setParameter(
        "myrmidon.comp.project", "org.apache.myrmidon.components.manager.DefaultProjectManager");
    defaults.setParameter(
        "myrmidon.comp.builder", "org.apache.myrmidon.components.builder.DefaultProjectBuilder");
    defaults.setParameter(
        "myrmidon.comp.deployer", "org.apache.myrmidon.components.deployer.DefaultTskDeployer");
    defaults.setParameter(
        "myrmidon.comp.configurer", "org.apache.myrmidon.components.configurer.DefaultConfigurer");
    return defaults;
  }
}
