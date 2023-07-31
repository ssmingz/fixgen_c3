class PlaceHold {
  protected void setUp() throws TaskException {
    checkOptions();
    File jar = getMetamataJar(metamataHome);
    final Path classPath = cmdl.createClasspath(getProject());
    classPath.createPathElement().setLocation(jar);
    final Commandline.Argument vmArgs = cmdl.createVmArgument();
    vmArgs.setValue("-Dmetamata.home=" + metamataHome.getAbsolutePath());
    includedFiles = scanFileSets();
    log(includedFiles.size() + " files added for audit", MSG_VERBOSE);
    Vector options = getOptions();
    optionsFile = createTmpFile();
    generateOptionsFile(optionsFile, options);
    Commandline.Argument args = cmdl.createArgument();
    args.setLine("-arguments " + optionsFile.getAbsolutePath());
  }
}
