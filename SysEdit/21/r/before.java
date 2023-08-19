class PlaceHold {
  public synchronized void contentRemoved(int offset, int length) {
    if (positions.size() == 0) return;

    /* get all positions from offset to the end, inclusive */
    Iterator iter = positions.tailMap(new PosBottomHalf(offset)).keySet().iterator();
    iteration = true;
    while (iter.hasNext()) {
      PosBottomHalf bh = ((PosBottomHalf) (iter.next()));
      if (bh.offset <= (offset + length)) bh.offset = offset;
      else bh.offset -= length;
    }
    iteration = false;
  }
}
