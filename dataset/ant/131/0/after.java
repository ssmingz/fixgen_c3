class PlaceHold {
  public boolean execute() throws BuildException {
    attributes.log("Using jikes compiler", MSG_VERBOSE);
    Path classpath = new Path(project);
    if (bootclasspath != null) {
      classpath.append(bootclasspath);
    }
    classpath.addExtdirs(extdirs);
    if ((bootclasspath == null) || (bootclasspath.size() == 0)) {
      includeJavaRuntime = true;
    } else {
    }
    classpath.append(getCompileClasspath());
    if (compileSourcepath != null) {
      classpath.append(compileSourcepath);
    } else {
      classpath.append(src);
    }
    String jikesPath = System.getProperty("jikes.class.path");
    if (jikesPath != null) {
      classpath.append(new Path(project, jikesPath));
    }
    Commandline cmd = new Commandline();
    String exec = getJavac().getExecutable();
    cmd.setExecutable(exec == null ? "jikes" : exec);
    if (deprecation == true) {
      cmd.createArgument().setValue("-deprecation");
    }
    if (destDir != null) {
      cmd.createArgument().setValue("-d");
      cmd.createArgument().setFile(destDir);
    }
    cmd.createArgument().setValue("-classpath");
    cmd.createArgument().setPath(classpath);
    if (encoding != null) {
      cmd.createArgument().setValue("-encoding");
      cmd.createArgument().setValue(encoding);
    }
    if (debug) {
      cmd.createArgument().setValue("-g");
    }
    if (optimize) {
      cmd.createArgument().setValue("-O");
    }
    if (verbose) {
      cmd.createArgument().setValue("-verbose");
    }
    if (depend) {
      cmd.createArgument().setValue("-depend");
    }
    if (target != null) {
      cmd.createArgument().setValue("-target");
      cmd.createArgument().setValue(target);
    }
    String emacsProperty = project.getProperty("build.compiler.emacs");
    if ((emacsProperty != null) && Project.toBoolean(emacsProperty)) {
      cmd.createArgument().setValue("+E");
    }
    String warningsProperty = project.getProperty("build.compiler.warnings");
    if (warningsProperty != null) {
      attributes.log("!! the build.compiler.warnings property is " + "deprecated. !!", MSG_WARN);
      attributes.log("!! Use the nowarn attribute instead. !!", MSG_WARN);
      if (!Project.toBoolean(warningsProperty)) {
        cmd.createArgument().setValue("-nowarn");
      }
    }
    if (attributes.getNowarn()) {
      cmd.createArgument().setValue("-nowarn");
    }
    String pedanticProperty = project.getProperty("build.compiler.pedantic");
    if ((pedanticProperty != null) && Project.toBoolean(pedanticProperty)) {
      cmd.createArgument().setValue("+P");
    }
    String fullDependProperty = project.getProperty("build.compiler.fulldepend");
    if ((fullDependProperty != null) && Project.toBoolean(fullDependProperty)) {
      cmd.createArgument().setValue("+F");
    }
    if (attributes.getSource() != null) {
      cmd.createArgument().setValue("-source");
      String source = attributes.getSource();
      if (source.equals("1.1") || source.equals("1.2")) {
        cmd.createArgument().setValue("1.3");
      } else {
        cmd.createArgument().setValue(source);
      }
    }
    addCurrentCompilerArgs(cmd);
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
