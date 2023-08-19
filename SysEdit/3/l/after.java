class PlaceHold {
  private int guess(
      org.eclipse.compare.internal.patch.WorkspacePatcher patcher,
      org.eclipse.core.runtime.IProgressMonitor pm,
      int strip) {
    org.eclipse.compare.internal.patch.Diff[] diffs = patcher.getDiffs();
    if ((diffs == null) || (diffs.length <= 0)) return -1;

    // now collect files and determine "work"
    org.eclipse.core.resources.IFile[] files = new org.eclipse.core.resources.IFile[diffs.length];
    int work = 0;
    for (int i = 0; i < diffs.length; i++) {
      org.eclipse.compare.internal.patch.Diff diff = diffs[i];
      if (diff == null) continue;

      if (diff.getDiffType() != org.eclipse.compare.structuremergeviewer.Differencer.ADDITION) {
        org.eclipse.core.runtime.IPath p = diff.fOldPath;
        if ((strip > 0) && (strip < p.segmentCount())) p = p.removeFirstSegments(strip);

        org.eclipse.core.resources.IFile file = existsInSelection(p);
        if (file != null) {
          files[i] = file;
          work += diff.fHunks.size();
        }
      }
    }
    // do the "work"
    int[] fuzzRef = new int[1];
    String format = PatchMessages.PreviewPatchPage_GuessFuzzProgress_format;
    pm.beginTask(PatchMessages.PreviewPatchPage_GuessFuzzProgress_text, work);
    try {
      int fuzz = 0;
      for (int i = 0; i < diffs.length; i++) {
        org.eclipse.compare.internal.patch.Diff d = diffs[i];
        org.eclipse.core.resources.IFile file = files[i];
        if ((d != null) && (file != null)) {
          List lines = patcher.load(file, false);
          String name = d.getPath().lastSegment();
          Iterator iter = d.fHunks.iterator();
          int shift = 0;
          for (int hcnt = 1; iter.hasNext(); hcnt++) {
            pm.subTask(
                com.ibm.icu.text.MessageFormat.format(
                    format, new String[] {name, Integer.toString(hcnt)}));
            org.eclipse.compare.internal.patch.Hunk h =
                ((org.eclipse.compare.internal.patch.Hunk) (iter.next()));
            shift = patcher.calculateFuzz(h, lines, shift, pm, fuzzRef);
            int f = fuzzRef[0];
            // cancel
            if (f == (-1)) return -1;

            if (f > fuzz) fuzz = f;

            pm.worked(1);
          }
        }
      }
      return fuzz;
    } finally {
      pm.done();
    }
  }
}
