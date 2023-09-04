class PlaceHold {
  public void contentInserted(int offset, int length, String text, boolean clearDirty) {
    Edit toMerge = null;
    if (compoundEdit != null) {
      int size = compoundEdit.getSize();
      if (size != 0) toMerge = ((Edit) (compoundEdit.undos.elementAt(size - 1)));

    } else if (undoPos != 0) toMerge = ((Edit) (undos.elementAt(undoPos - 1)));

    if ((!clearDirty) && (toMerge instanceof Insert)) {
      Insert ins = ((Insert) (toMerge));
      if (ins.offset == offset) {
        ins.str = text.concat(ins.str);
        ins.length += length;
        return;
      } else if ((ins.offset + ins.length) == offset) {
        ins.str = ins.str.concat(text);
        ins.length += length;
        return;
      }
    }
    Insert ins = new Insert(offset, length, text, clearDirty);
    if (clearDirty) {
      if (clearDirtyEdit != null) clearDirtyEdit.clearDirty = false;

      clearDirtyEdit = ins;
    }
    if (compoundEdit != null) compoundEdit.addEdit(ins);
    else addEdit(ins);
  }
}
