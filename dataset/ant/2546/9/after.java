class PlaceHold {
  public void getWritableCommand(Commandline cmd) {
    if (!m_Writable) {
      return;
    } else {
      cmd.addArgument(FLAG_WRITABLE);
    }
  }
}
