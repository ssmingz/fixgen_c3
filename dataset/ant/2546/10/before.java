class PlaceHold {
  private void getRecursiveCommand(Commandline cmd) {
    if (!m_Recursive) {
      return;
    } else {
      cmd.createArgument().setValue(FLAG_RECURSION);
    }
  }
}
