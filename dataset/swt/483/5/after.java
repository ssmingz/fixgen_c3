class PlaceHold {
  protected IProject[] build(int kind, Map args, final IProgressMonitor monitor)
      throws CoreException {
    IResourceDelta delta = getDelta(getProject());
    if (delta == null) {
      return null;
    }
    delta.accept(
        new IResourceDeltaVisitor() {
          public boolean visit(IResourceDelta delta) throws CoreException {
            String path = delta.getFullPath().toPortableString();
            for (int i = 0; i < classes.length; i++) {
              if (classes[i].build) {
                continue;
              }
              String outputDir = classes[i].outputDir;
              if (path.startsWith(
                  outputDir.substring(0, outputDir.length() - "library/".length()))) {
                classes[i].build = true;
              }
            }
            return true;
          }
        });
    final IWorkspaceRoot root = getProject().getWorkspace().getRoot();
    for (int i = 0; i < classes.length; i++) {
      MainClass mainClass = classes[i];
      if (mainClass.build) {
        mainClass.build = false;
        IResource library = root.findMember(mainClass.outputDir);
        JNIGeneratorApp gen = new JNIGeneratorApp();
        gen.setMainClassName(mainClass.mainClassName, library.getLocation().toPortableString());
        gen.generate();
        library.refreshLocal(IResource.DEPTH_INFINITE, null);
      }
    }
    return null;
  }
}
