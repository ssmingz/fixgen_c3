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
    final ExecTask cmd = ((ExecTask) (getProject().createTask("exec")));
    cmd.setExecutable(JavaEnvUtils.getJdkExecutable("jarsigner"));
    if (maxMemory != null) {
      cmd.createArg().setValue("-J-Xmx" + maxMemory);
    }
    if (null != keystore) {
      File keystoreFile = getProject().resolveFile(keystore);
      if (keystoreFile.exists()) {
        cmd.createArg().setValue("-keystore");
        cmd.createArg().setValue(keystoreFile.getPath());
      } else {
        cmd.createArg().setValue("-keystore");
        cmd.createArg().setValue(keystore);
      }
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
      cmd.createArg().setValue(sigfile);
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
