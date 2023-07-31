class PlaceHold {
  public void getQuietCommand(Commandline cmd) {
    if (m_Quiet) {
      cmd.createArgument().setValue(FLAG_QUIET);
    }
  }
}
