class PlaceHold {
  protected void setUp() throws BuildException {
    checkOptions();
    File jar = getMetamataJar(metamataHome);
    final Path classPath = cmdl.createClasspath(project);
    classPath.createPathElement().setLocation(jar);
    final Commandline.Argument vmArgs = cmdl.createVmArgument();
    vmArgs.setValue("-Dmetamata.home=" + metamataHome.getAbsolutePath());
    includedFiles = scanSources(new Hashtable());
    log(includedFiles.size() + " files added for audit", MSG_VERBOSE);
    Vector options = getOptions();
    optionsFile = createTmpFile();
    generateOptionsFile(optionsFile, options);
    Commandline.Argument args = cmdl.createArgument();
    args.setLine("-arguments " + optionsFile.getAbsolutePath());
  }
}
