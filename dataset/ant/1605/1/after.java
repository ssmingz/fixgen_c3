class PlaceHold {
  protected void handleChildren(Object parent, RuntimeConfigurable parentWrapper)
      throws BuildException {
    if (parent instanceof TypeAdapter) {
      parent = ((TypeAdapter) (parent)).getProxy();
    }
    String parentUri = getNamespace();
    Class parentClass = parent.getClass();
    IntrospectionHelper ih = IntrospectionHelper.getHelper(getProject(), parentClass);
    if (children != null) {
      Iterator it = children.iterator();
      for (int i = 0; it.hasNext(); i++) {
        RuntimeConfigurable childWrapper = parentWrapper.getChild(i);
        UnknownElement child = ((UnknownElement) (it.next()));
        try {
          if (!handleChild(parentUri, ih, parent, child, childWrapper)) {
            if (!(parent instanceof TaskContainer)) {
              ih.throwNotSupported(getProject(), parent, child.getTag());
            } else {
              TaskContainer container = ((TaskContainer) (parent));
              container.addTask(child);
            }
          }
        } catch (UnsupportedElementException ex) {
          throw new BuildException(
              ((parentWrapper.getElementTag() + " doesn\'t support the nested \"")
                      + ex.getElement())
                  + "\" element.",
              ex);
        }
      }
    }
  }
}
