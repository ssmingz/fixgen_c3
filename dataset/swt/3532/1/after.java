class PlaceHold {
  NSBitmapImageRep getRepresentation() {
    NSBitmapImageRep rep = new NSBitmapImageRep(handle.bestRepresentationForDevice(null));
    if (rep.isKindOfClass(class_NSBitmapImageRep)) {
      return rep;
    }
    NSArray reps = handle.representations();
    NSSize size = handle.size();
    long count = reps.count();
    NSBitmapImageRep bestRep = null;
    for (int i = 0; i < count; i++) {
      rep = new NSBitmapImageRep(reps.objectAtIndex(i));
      if (rep.isKindOfClass(class_NSBitmapImageRep)) {
        return rep;
      }
      if ((bestRep == null)
          || ((((int) (size.width)) == rep.pixelsWide())
              && (((int) (size.height)) == rep.pixelsHigh()))) {
        bestRep = rep;
      }
    }
    bestRep.retain();
    for (int i = 0; i < count; i++) {
      handle.removeRepresentation(new NSImageRep(handle.representations().objectAtIndex(0)));
    }
    handle.addRepresentation(bestRep);
    NSBitmapImageRep newRep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
    newRep = newRep.initWithData(handle.TIFFRepresentation());
    handle.addRepresentation(newRep);
    handle.removeRepresentation(bestRep);
    bestRep.release();
    newRep.release();
    return newRep;
  }
}
