class PlaceHold {
  protected void setUp() throws TaskException {
    checkOptions();
    File[] jars = getMetamataLibs();
    final Path classPath = m_cmdl.createClasspath();
    for (int i = 0; i < jars.length; i++) {
      classPath.addLocation(jars[i]);
    }
    final Argument vmArgs = m_cmdl.createVmArgument();
    vmArgs.setValue("-Dmetamata.home=" + m_metahome.getAbsolutePath());
    String[] options = getOptions();
    m_optionsFile = createTmpFile();
    generateOptionsFile(m_optionsFile, options);
    Argument args = m_cmdl.createArgument();
    args.setLine("-arguments " + m_optionsFile.getAbsolutePath());
  }
}
