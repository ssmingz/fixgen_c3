class PlaceHold {
  public void getQuietCommand(Commandline cmd) {
    if (m_Quiet) {
      cmd.addArgument(FLAG_QUIET);
    }
  }
}
