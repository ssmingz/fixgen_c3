class PlaceHold {
  public void execute() throws TaskException {
    preconditions();
    scan();
    if (dirty) {
      boolean useControlFile = (controlFile != null) && controlFile.exists();
      if (useControlFile && (!preModified)) {
        pre = false;
      }
      if (useControlFile && (!postModified)) {
        post = false;
      }
      if (useControlFile && (!invariantModified)) {
        invariant = false;
      }
      if (((pre || post) || invariant) && (controlFile != null)) {
        getLogger()
            .info("WARNING: specifying pre,post or invariant will override control file settings");
      }
      getProject().addProjectListener(new IContractPresenceDetector());
      instrumentDir.mkdirs();
      buildDir.mkdirs();
      repositoryDir.mkdirs();
      Path baseClasspath = createClasspath();
      String compiler = getProperty("build.compiler").toString();
      ClasspathHelper classpathHelper = new ClasspathHelper(compiler);
      classpathHelper.modify(baseClasspath);
      Path beforeInstrumentationClasspath = ((Path) (baseClasspath.clone()));
      beforeInstrumentationClasspath.append(new Path(srcDir.getAbsolutePath()));
      Path afterInstrumentationClasspath = ((Path) (baseClasspath.clone()));
      afterInstrumentationClasspath.append(new Path(instrumentDir.getAbsolutePath()));
      afterInstrumentationClasspath.append(new Path(repositoryDir.getAbsolutePath()));
      afterInstrumentationClasspath.append(new Path(srcDir.getAbsolutePath()));
      afterInstrumentationClasspath.append(new Path(buildDir.getAbsolutePath()));
      Path repositoryClasspath = ((Path) (baseClasspath.clone()));
      repositoryClasspath.append(new Path(instrumentDir.getAbsolutePath()));
      repositoryClasspath.append(new Path(srcDir.getAbsolutePath()));
      repositoryClasspath.append(new Path(repositoryDir.getAbsolutePath()));
      repositoryClasspath.append(new Path(buildDir.getAbsolutePath()));
      Path iContractClasspath = ((Path) (baseClasspath.clone()));
      iContractClasspath.append(
          new Path(
              (((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                          + "lib")
                      + File.separator)
                  + "tools.jar"));
      iContractClasspath.append(new Path(srcDir.getAbsolutePath()));
      iContractClasspath.append(new Path(repositoryDir.getAbsolutePath()));
      iContractClasspath.append(new Path(instrumentDir.getAbsolutePath()));
      iContractClasspath.append(new Path(buildDir.getAbsolutePath()));
      Java iContract = ((Java) (getProject().createTask("java")));
      iContract.setFork(true);
      iContract.setClassname("com.reliablesystems.iContract.Tool");
      iContract.setClasspath(iContractClasspath);
      StringBuffer args = new StringBuffer();
      args.append(directiveString());
      args.append("-v").append(verbosity).append(" ");
      args.append("-b")
          .append("\"")
          .append(icCompiler)
          .append(" -classpath ")
          .append(beforeInstrumentationClasspath)
          .append("\" ");
      args.append("-c")
          .append("\"")
          .append(icCompiler)
          .append(" -classpath ")
          .append(afterInstrumentationClasspath)
          .append(" -d ")
          .append(buildDir)
          .append("\" ");
      args.append("-n")
          .append("\"")
          .append(icCompiler)
          .append(" -classpath ")
          .append(repositoryClasspath)
          .append("\" ");
      args.append("-d").append(failThrowable).append(" ");
      args.append("-o")
          .append(instrumentDir)
          .append(File.separator)
          .append("@p")
          .append(File.separator)
          .append("@f.@e ");
      args.append("-k").append(repositoryDir).append(File.separator).append("@p ");
      args.append(quiet ? "-q " : "");
      args.append(instrumentall ? "-a " : "");
      args.append("@").append(targets.getAbsolutePath());
      iContract.createArg().setLine(args.toString());
      if (updateIcontrol) {
        Properties iControlProps = new Properties();
        try {
          iControlProps.load(new FileInputStream("icontrol.properties"));
        } catch (IOException e) {
          getLogger().info("File icontrol.properties not found. That's ok. Writing a default one.");
        }
        iControlProps.setProperty("sourceRoot", srcDir.getAbsolutePath());
        iControlProps.setProperty("classRoot", classDir.getAbsolutePath());
        iControlProps.setProperty("classpath", afterInstrumentationClasspath.toString());
        iControlProps.setProperty("controlFile", controlFile.getAbsolutePath());
        iControlProps.setProperty("targetsFile", targets.getAbsolutePath());
        try {
          iControlProps.store(
              new FileOutputStream("icontrol.properties"), ICONTROL_PROPERTIES_HEADER);
          getLogger().info("Updated icontrol.properties");
        } catch (IOException e) {
          getLogger().info("Couldn't write icontrol.properties.");
        }
      }
      int result = iContract.executeJava();
      if (result != 0) {
        if (iContractMissing) {
          getLogger().info("iContract can't be found on your classpath. Your classpath is:");
          getLogger().info(classpath.toString());
          getLogger()
              .info(
                  "If you don't have the iContract jar, go get it at"
                      + " http://www.reliable-systems.com/tools/");
        }
        throw new TaskException("iContract instrumentation failed. Code=" + result);
      }
    } else {
    }
  }
}
