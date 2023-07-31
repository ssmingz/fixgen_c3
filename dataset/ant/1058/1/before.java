class PlaceHold {
  private Ant createAntTask(File directory) {
    Ant ant = ((Ant) (getProject().createTask("ant")));
    ant.setOwningTarget(getOwningTarget());
    ant.init();
    if ((target != null) && (target.length() > 0)) {
      ant.setTarget(target);
    }
    if (output != null) {
      ant.setOutput(output);
    }
    if (directory != null) {
      ant.setDir(directory);
    }
    ant.setInheritAll(inheritAll);
    for (Enumeration i = properties.elements(); i.hasMoreElements(); ) {
      copyProperty(ant.createProperty(), ((Property) (i.nextElement())));
    }
    for (Enumeration i = propertySets.elements(); i.hasMoreElements(); ) {
      ant.addPropertyset(((PropertySet) (i.nextElement())));
    }
    ant.setInheritRefs(inheritRefs);
    for (Enumeration i = references.elements(); i.hasMoreElements(); ) {
      ant.addReference(((Ant.Reference) (i.nextElement())));
    }
    return ant;
  }
}
