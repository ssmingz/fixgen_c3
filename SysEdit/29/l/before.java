class PlaceHold {
  protected Object[] findSourceElements(
      String name, org.eclipse.debug.core.sourcelookup.ISourceContainer[] containers)
      throws org.eclipse.core.runtime.CoreException {
    List results = null;
    org.eclipse.core.runtime.CoreException single = null;
    org.eclipse.core.runtime.MultiStatus multiStatus = null;
    if (isFindDuplicates()) {
      results = new ArrayList();
    }
    for (int i = 0; i < containers.length; i++) {
      org.eclipse.debug.core.sourcelookup.ISourceContainer container = containers[i];
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
      } catch (org.eclipse.core.runtime.CoreException e) {
        if (single == null) {
          single = e;
        } else if (multiStatus == null) {
          multiStatus =
              new org.eclipse.core.runtime.MultiStatus(
                  org.eclipse.debug.core.DebugPlugin.getUniqueIdentifier(),
                  org.eclipse.debug.core.DebugPlugin.ERROR,
                  new org.eclipse.core.runtime.IStatus[] {single.getStatus()},
                  org.eclipse.debug.internal.core.sourcelookup.SourceLookupMessages
                      .Source_Lookup_Error,
                  null);
          multiStatus.add(e.getStatus());
        } else {
          multiStatus.add(e.getStatus());
        }
      }
    }
    if (results == null) {
      if (multiStatus != null) {
        throw new org.eclipse.core.runtime.CoreException(multiStatus);
      } else if (single != null) {
        throw single;
      }
      return EMPTY;
    }
    return results.toArray();
  }
}
