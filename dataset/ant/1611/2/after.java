class PlaceHold {
  protected void setUp() throws BuildException {
    checkOptions();
    File[] jars = getMetamataLibs();
    final Path classPath = cmdl.createClasspath(getProject());
    for (int i = 0; i < jars.length; i++) {
      classPath.createPathElement().setLocation(jars[i]);
    }
    final Commandline.Argument vmArgs = cmdl.createVmArgument();
    vmArgs.setValue("-Dmetamata.home=" + metamataHome.getAbsolutePath());
    Vector opts = getOptions();
    String[] options = new String[opts.size()];
    opts.copyInto(options);
    optionsFile = createTmpFile();
    generateOptionsFile(optionsFile, options);
    Commandline.Argument args = cmdl.createArgument();
    args.setLine("-arguments " + optionsFile.getAbsolutePath());
  }
}
