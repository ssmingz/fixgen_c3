class PlaceHold {
  protected void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      Mapper[] m = new Mapper[] {inputMapper, outputMapper, errorMapper};
      for (int i = 0; i < m.length; i++) {
        if (m[i] != null) {
          stk.push(m[i]);
          m[i].dieOnCircularReference(stk, p);
          stk.pop();
        }
      }
      Vector[] v = new Vector[] {inputFilterChains, outputFilterChains, errorFilterChains};
      for (int i = 0; i < v.length; i++) {
        if (v[i] != null) {
          for (Iterator fci = v[i].iterator(); fci.hasNext(); ) {
            FilterChain fc = ((FilterChain) (fci.next()));
            pushAndInvokeCircularReferenceCheck(fc, stk, p);
          }
        }
      }
      setChecked(true);
    }
  }
}
