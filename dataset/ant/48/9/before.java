class PlaceHold{
private void addBeans(Hashtable dictionary) {
    for (Enumeration e = dictionary.keys(); e.hasMoreElements();) {
        String key = ((String) (e.nextElement()));
        boolean isValid = (key.length() > 0) && Character.isJavaIdentifierStart(key.charAt(0));
        for (int i = 1; isValid && (i < key.length()); i++) {
            isValid = Character.isJavaIdentifierPart(key.charAt(i));
        }
        if (isValid) {
            beans.put(key, dictionary.get(key));
        }
    }
}
}