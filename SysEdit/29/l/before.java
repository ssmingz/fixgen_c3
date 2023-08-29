class PlaceHold {
  protected Object[] findSourceElements(String name, ISourceContainer[] containers)
      throws CoreException {
    List results = null;
    CoreException single = null;
    MultiStatus multiStatus = null;
    if (isFindDuplicates()) {
      results = new ArrayList();
    }
    for (int i = 0; i < containers.length; i++) {
      ISourceContainer container = containers[i];
      try {
        Object[] objects = container.findSourceElements(name);
        if (objects.length > 0) {
          if (isFindDuplicates()) {
            for (int j = 0; j < objects.length; j++) {
              results.add(objects[j]);
            }
          } else {
            if (objects.length == 1) {
              return objects;
            }
            return new Object[] {objects[0]};
          }
        }
      } catch (CoreException e) {
        if (single == null) {
          single = e;
        } else if (multiStatus == null) {
          multiStatus =
              new MultiStatus(
                  DebugPlugin.getUniqueIdentifier(),
                  DebugPlugin.ERROR,
                  new IStatus[] {single.getStatus()},
                  SourceLookupMessages.Source_Lookup_Error,
                  null);
          multiStatus.add(e.getStatus());
        } else {
          multiStatus.add(e.getStatus());
        }
      }
    }
    if (results == null) {
      if (multiStatus != null) {
        throw new CoreException(multiStatus);
      } else if (single != null) {
        throw single;
      }
      return EMPTY;
    }
    return results.toArray();
  }
}
