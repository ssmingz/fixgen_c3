class PlaceHold {
  protected void handleChildren(Object parent, RuntimeConfigurable parentWrapper)
      throws BuildException {
    if (parent instanceof TaskAdapter) {
      parent = ((TaskAdapter) (parent)).getProxy();
    }
    Class parentClass = parent.getClass();
    IntrospectionHelper ih = IntrospectionHelper.getHelper(parentClass);
    for (int i = 0; i < children.size(); i++) {
      RuntimeConfigurable childWrapper = parentWrapper.getChild(i);
      UnknownElement child = ((UnknownElement) (children.elementAt(i)));
      Object realChild = null;
      if (parent instanceof TaskContainer) {
        realChild = makeTask(child, childWrapper, false);
        ((TaskContainer) (parent)).addTask(((Task) (realChild)));
      } else {
        realChild = ih.createElement(project, parent, child.getTag());
      }
      childWrapper.setProxy(realChild);
      if (parent instanceof TaskContainer) {
        ((Task) (realChild)).setRuntimeConfigurableWrapper(childWrapper);
      }
      child.handleChildren(realChild, childWrapper);
      if (parent instanceof TaskContainer) {
        ((Task) (realChild)).maybeConfigure();
      }
    }
  }
}
