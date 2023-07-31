class PlaceHold {
  public void execute() throws ExecutionException {
    String language = factory.getScriptLanguage(scriptName);
    String script = factory.getScript(scriptName);
    try {
      BSFManager manager = new BSFManager();
      manager.declareBean("self", this, getClass());
      manager.declareBean("context", getContext(), AntContext.class);
      BSFEngine engine = manager.loadScriptingEngine(language);
      engine.exec(scriptName, 0, 0, script);
      for (Iterator i = attributes.keySet().iterator(); i.hasNext(); ) {
        String attributeName = ((String) (i.next()));
        String value = ((String) (attributes.get(attributeName)));
        StringBuffer setter = new StringBuffer(attributeName);
        setter.setCharAt(0, Character.toUpperCase(setter.charAt(0)));
        engine.call(null, "set" + setter, new Object[] {value});
      }
      Iterator i = nestedElementNames.iterator();
      Iterator j = nestedElements.iterator();
      while (i.hasNext()) {
        String nestedName = ((String) (i.next()));
        Object nestedElement = j.next();
        StringBuffer adder = new StringBuffer(nestedName);
        adder.setCharAt(0, Character.toUpperCase(adder.charAt(0)));
        engine.call(null, "add" + adder, new Object[] {nestedElement});
      }
      engine.call(null, "execute", new Object[] {});
    } catch (BSFException e) {
      Throwable t = e;
      Throwable te = e.getTargetException();
      if (te != null) {
        if (te instanceof ExecutionException) {
          throw ((ExecutionException) (te));
        } else {
          t = te;
        }
      }
      throw new ExecutionException(t);
    }
  }
}
