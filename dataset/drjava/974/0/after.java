class PlaceHold {
  public ClassPathVector getClassPath() {
    if (_restart) {
      InterpreterJVMRemoteI slave = ensureInterpreterConnected();
      try {
        Vector<String> strClassPath = new Vector<String>(slave.getAugmentedClassPath());
        ClassPathVector classPath =
            new ClassPathVector(strClassPath.size() + _startupClassPathVector.size());
        for (String s : strClassPath) {
          classPath.add(s);
        }
        classPath.addAll(_startupClassPathVector);
        return classPath;
      } catch (RemoteException re) {
        _threwException(re);
      }
    }
    return new ClassPathVector();
  }
}
