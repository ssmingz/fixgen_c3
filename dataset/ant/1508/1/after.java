class PlaceHold {
  public void parse(Project project, Object source) throws BuildException {
    getImportStack().addElement(source);
    AntXMLContext context = null;
    context = ((AntXMLContext) (project.getReference("ant.parsing.context")));
    if (context == null) {
      context = new AntXMLContext(project);
      project.addReference("ant.parsing.context", context);
      project.addReference("ant.targets", context.getTargets());
    }
    if (getImportStack().size() > 1) {
      context.setIgnoreProjectTag(true);
      Target currentTarget = context.getCurrentTarget();
      try {
        Target newCurrent = new Target();
        newCurrent.setProject(project);
        newCurrent.setName("");
        context.setCurrentTarget(newCurrent);
        parse(project, source, new RootHandler(context, mainHandler));
        newCurrent.execute();
      } finally {
        context.setCurrentTarget(currentTarget);
      }
    } else {
      parse(project, source, new RootHandler(context, mainHandler));
      context.getImplicitTarget().execute();
    }
  }
}
