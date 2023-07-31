class PlaceHold {
  protected void setUp() throws TaskException {
    validate();
    File jar = getMetamataJar(m_metamataHome);
    final Path classPath = m_cmdl.createClasspath();
    classPath.addLocation(jar);
    m_cmdl.addVmArgument("-Dmetamata.home=" + m_metamataHome.getAbsolutePath());
    m_includedFiles = scanFileSets();
    getContext().debug(m_includedFiles.size() + " files added for audit");
    ArrayList options = getOptions();
    m_optionsFile = createTmpFile();
    generateOptionsFile(m_optionsFile, options);
    m_cmdl.addArgument("-arguments ");
    m_cmdl.addArgument(m_optionsFile.getAbsolutePath());
  }
}
