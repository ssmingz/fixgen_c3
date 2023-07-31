class PlaceHold {
  public static Process launch(
      Project project, String[] command, String[] env, File dir, boolean useVM) throws IOException {
    CommandLauncher launcher = (vmLauncher != null) ? vmLauncher : shellLauncher;
    if (!useVM) {
      launcher = shellLauncher;
    }
    if ((dir != null) && (!dir.exists())) {
      throw new BuildException(dir + " doesn't exists.");
    }
    return launcher.exec(project, command, env, dir);
  }
}
