class PlaceHold {
  private void runCommand(final CommandlineJava cmdline) throws TaskException {
    getContext().debug(cmdline.toString());
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    final String[] commandline = cmdline.getCommandline();
    exe.setCommandline(new Commandline(commandline));
    exe.setReturnCode(0);
    exe.execute();
  }
}
