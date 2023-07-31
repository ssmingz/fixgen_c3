class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.gtk_tree_model_iter_n_children(modelHandle, 0);
  }
}
