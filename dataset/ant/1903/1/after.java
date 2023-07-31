class PlaceHold {
  protected void setUp() throws TaskException {
    validate();
    File jar = getMetamataJar(m_metamataHome);
    final Path classPath = m_cmdl.createClasspath();
    classPath.addLocation(jar);
    m_cmdl.addVmArgument("-Dmetamata.home=" + m_metamataHome.getAbsolutePath());
    m_includedFiles = scanFileSets();
    getLogger().debug(m_includedFiles.size() + " files added for audit");
    ArrayList options = getOptions();
    m_optionsFile = createTmpFile();
    generateOptionsFile(m_optionsFile, options);
    Argument args = m_cmdl.createArgument();
    args.setLine("-arguments " + m_optionsFile.getAbsolutePath());
  }
}
