class PlaceHold {
  protected void run(Commandline cmd) throws TaskException {
    final Execute exe = new Execute();
    exe.setWorkingDirectory(getBaseDirectory());
    exe.setCommandline(cmd);
    exe.setReturnCode(0);
    exe.execute(getContext());
  }
}
