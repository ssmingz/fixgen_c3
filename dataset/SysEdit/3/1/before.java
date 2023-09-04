class PlaceHold {
  private int guess(WorkspacePatcher patcher, IProgressMonitor pm, int strip) {

    Diff[] diffs = patcher.getDiffs();
    if (diffs == null || diffs.length <= 0) return -1;

    // now collect files and determine "work"
    IFile[] files = new IFile[diffs.length];
    int work = 0;
    for (int i = 0; i < diffs.length; i++) {
      Diff diff = diffs[i];
      if (diff == null) continue;
      if (diff.getType() != Differencer.ADDITION) {
        IPath p = diff.fOldPath;
        if (strip > 0 && strip < p.segmentCount()) p = p.removeFirstSegments(strip);
        IFile file = existsInSelection(p);
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
        Diff d = diffs[i];
        IFile file = files[i];
        if (d != null && file != null) {
          List lines = patcher.load(file, false);
          String name = d.getPath().lastSegment();
          Iterator iter = d.fHunks.iterator();
          int shift = 0;
          for (int hcnt = 1; iter.hasNext(); hcnt++) {
            pm.subTask(MessageFormat.format(format, new String[] {name, Integer.toString(hcnt)}));
            Hunk h = (Hunk) iter.next();
            shift = patcher.calculateFuzz(h, lines, shift, pm, fuzzRef);
            int f = fuzzRef[0];
            if (f == -1) // cancel
            return -1;
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
