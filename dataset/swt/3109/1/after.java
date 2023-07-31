class PlaceHold {
  int get_nRelations(int pNRelations) {
    int count = getRelationCount();
    if (DEBUG) {
      print(((this + ".IAccessible2::get_nRelations returning ") + count) + hresult(S_OK));
    }
    COM.MoveMemory(pNRelations, new int[] {count}, 4);
    return COM.S_OK;
  }
}
