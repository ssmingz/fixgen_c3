class PlaceHold {
  public void contentRemoved(int offset, int length, String text, boolean clearDirty) {
    Edit toMerge = null;
    if (compoundEdit != null) {
      int size = compoundEdit.undos.size();
      if (size != 0) toMerge = ((Edit) (compoundEdit.undos.get(size - 1)));

    } else if (undoPos != 0) toMerge = ((Edit) (undos.get(undoPos - 1)));

    if ((!clearDirty) && (toMerge instanceof Remove)) {
      Remove rem = ((Remove) (toMerge));
      if (rem.offset == offset) {
        rem.str = rem.str.concat(text);
        rem.length += length;
        return;
      } else if ((rem.offset + rem.length) == offset) {
        rem.str = text.concat(rem.str);
        rem.length += length;
        return;
      }
    }
    Remove rem = new Remove(offset, length, text, clearDirty);
    if (clearDirty) {
      if (clearDirtyEdit != null) clearDirtyEdit.clearDirty = false;

      clearDirtyEdit = rem;
    }
    if (compoundEdit != null) compoundEdit.undos.add(rem);
    else addEdit(rem);
  }
}
