class PlaceHold {
  public synchronized void runImmediateQuitTest() throws Exception {
    _currentTest = "runImmediateQuitTest";
    _justQuit = false;
    _letter = 'a';
    invokeSlave(
        new String[] {"-Djava.system.class.loader=edu.rice.cs.util.newjvm.CustomSystemClassLoader"},
        NULL_FILE);
    quitSlave();
    while (!_justQuit) {
      wait();
    }
    _currentTest = "";
  }
}
