class PlaceHold {
  public void applyAll(IProgressMonitor pm, Shell shell, String title) throws CoreException {
    if (!fIsWorkspacePatch) {
      super.applyAll(pm, shell, title);
    } else {
      final int WORK_UNIT = 10;

      // get all files to be modified in order to call validateEdit
      List list = new ArrayList();
      for (int j = 0; j < fDiffProjects.length; j++) {
        DiffProject diffProject = fDiffProjects[j];
        if (diffProject.getProject().isAccessible())
          list.addAll(Arrays.asList(getTargetFiles(diffProject)));
      }
      // validate the files for editing
      if (!Utilities.validateResources(list, shell, title)) return;

      FileDiff[] diffs = getDiffs();
      if (pm != null) {
        String message = PatchMessages.Patcher_Task_message;
        pm.beginTask(message, diffs.length * WORK_UNIT);
      }

      for (int i = 0; i < diffs.length; i++) {

        int workTicks = WORK_UNIT;

        FileDiff diff = diffs[i];
        if (isAccessible(diff)) {
          IFile file = getTargetFile(diff);
          IPath path = file.getProjectRelativePath();
          if (pm != null) pm.subTask(path.toString());
          createPath(file.getProject(), path);

          List failed = new ArrayList();
          List result = null;

          int type = diff.getDiffType(isReversed());
          switch (type) {
            case Differencer.ADDITION:
              // patch it and collect rejected hunks
              result = apply(diff, file, true, failed);
              store(createString(result), file, new SubProgressMonitor(pm, workTicks));
              workTicks -= WORK_UNIT;
              break;
            case Differencer.DELETION:
              file.delete(true, true, new SubProgressMonitor(pm, workTicks));
              workTicks -= WORK_UNIT;
              break;
            case Differencer.CHANGE:
              // patch it and collect rejected hunks
              result = apply(diff, file, false, failed);
              store(createString(result), file, new SubProgressMonitor(pm, workTicks));
              workTicks -= WORK_UNIT;
              break;
          }

          if (isGenerateRejectFile() && failed.size() > 0) {
            IPath pp = null;
            if (path.segmentCount() > 1) {
              pp = path.removeLastSegments(1);
              pp = pp.append(path.lastSegment() + REJECT_FILE_EXTENSION);
            } else pp = new Path(path.lastSegment() + REJECT_FILE_EXTENSION);
            file = createPath(file.getProject(), pp);
            if (file != null) {
              store(getRejected(failed), file, pm);
              try {
                IMarker marker = file.createMarker(MARKER_TYPE);
                marker.setAttribute(IMarker.MESSAGE, PatchMessages.Patcher_Marker_message);
                marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
              } catch (CoreException ex) {
                // NeedWork
              }
            }
          }
        }

        if (pm != null) {
          if (pm.isCanceled()) break;
          if (workTicks > 0) pm.worked(workTicks);
        }
      }
    }
  }
}
