class PlaceHold {
  public void getWritableCommand(Commandline cmd) {
    if (!m_Writable) {
      return;
    } else {
      cmd.createArgument().setValue(FLAG_WRITABLE);
    }
  }
}
