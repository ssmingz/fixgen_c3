class PlaceHold {
  private void doOneJar(File jarSource, File jarTarget) throws BuildException {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      throw new BuildException(
          "The signjar task is only available on " + "JDK versions 1.2 or greater");
    }
    if (null == alias) {
      throw new BuildException("alias attribute must be set");
    }
    if (null == storepass) {
      throw new BuildException("storepass attribute must be set");
    }
    if (isUpToDate(jarSource, jarTarget)) {
      return;
    }
    final ExecTask cmd = ((ExecTask) (project.createTask("exec")));
    cmd.setExecutable("jarsigner");
    if (null != keystore) {
      cmd.createArg().setValue("-keystore");
      cmd.createArg().setValue(keystore.toString());
    }
    if (null != storepass) {
      cmd.createArg().setValue("-storepass");
      cmd.createArg().setValue(storepass);
    }
    if (null != storetype) {
      cmd.createArg().setValue("-storetype");
      cmd.createArg().setValue(storetype);
    }
    if (null != keypass) {
      cmd.createArg().setValue("-keypass");
      cmd.createArg().setValue(keypass);
    }
    if (null != sigfile) {
      cmd.createArg().setValue("-sigfile");
      cmd.createArg().setValue(sigfile.toString());
    }
    if (null != jarTarget) {
      cmd.createArg().setValue("-signedjar");
      cmd.createArg().setValue(jarTarget.toString());
    }
    if (verbose) {
      cmd.createArg().setValue("-verbose");
    }
    if (internalsf) {
      cmd.createArg().setValue("-internalsf");
    }
    if (sectionsonly) {
      cmd.createArg().setValue("-sectionsonly");
    }
    cmd.createArg().setValue(jarSource.toString());
    cmd.createArg().setValue(alias);
    log("Signing Jar : " + jarSource.getAbsolutePath());
    cmd.setFailonerror(true);
    cmd.setTaskName(getTaskName());
    cmd.execute();
  }
}
