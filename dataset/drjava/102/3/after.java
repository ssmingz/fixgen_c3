class PlaceHold {
  public synchronized void runTestSequence() throws Exception {
    _currentTest = "runTestSequence";
    _justQuit = false;
    _letter = 'a';
    invokeSlave(
        new String[] {"-Djava.system.class.loader=edu.rice.cs.util.newjvm.CustomSystemClassLoader"},
        NULL_FILE);
    wait();
    ((SlaveI) (getSlave())).startLetterTest();
    while (_letter != 'f') {
      wait();
    }
    for (int i = 0; i < 7; i++) {
      int value = ((SlaveI) (getSlave())).getNumber();
      assertEquals("value returned by slave", i, value);
    }
    quitSlave();
    wait();
    _currentTest = "";
  }
}
