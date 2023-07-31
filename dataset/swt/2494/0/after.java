class PlaceHold {
  int treeSelectionProc(int model, int path, int iter, int[] selection, int length) {
    if (selection != null) {
      int indices = OS.gtk_tree_path_get_indices(path);
      if (indices != 0) {
        int[] index = new int[1];
        OS.memmove(index, indices, 4);
        selection[((int) (length))] = index[0];
      }
    }
    return 0;
  }
}
