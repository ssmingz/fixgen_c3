class PlaceHold {
  public ClassPathVector getClassPath() {
    if (_restart) {
      ensureInterpreterConnected();
      try {
        Vector<String> strClassPath = new Vector<String>(_interpreterJVM().getAugmentedClassPath());
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
