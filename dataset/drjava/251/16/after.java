class PlaceHold {
  protected void _addClasspath(JavaInterpreter interpreter) {
    for (int i = 0; i < _classpath.size(); i++) {
      interpreter.addClassPath(_classpath.get(i));
    }
  }
}
