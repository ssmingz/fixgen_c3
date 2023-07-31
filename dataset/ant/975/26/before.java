class PlaceHold {
  private void doOneJar(final File jarSource, final File jarTarget) throws TaskException {
    if (isUpToDate(jarSource, jarTarget)) {
      return;
    }
    final String message = "Signing Jar : " + jarSource.getAbsolutePath();
    getLogger().info(message);
    final Commandline cmd = buildCommand(jarTarget, jarSource);
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    exe.setCommandline(cmd);
    exe.execute();
  }
}
