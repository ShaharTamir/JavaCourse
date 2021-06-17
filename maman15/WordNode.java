public class WordNode {

    private String _val;
    private WordNode _next;

    /**
     * default C'tor - consturct a new node with empty string, next is null.
     */
    public WordNode()
    {
        _val = new String();
        _next = null;
    }

    /**
     * C'tor - construct a new node, holding the value of val, next is null.
     * 
     * @param val a word.
     */
    public WordNode(String val)
    {
        _val = new String(val);
        _next = null;
    }

    /**
     * C'tor - construct a new node, holding the value of val, next is node.
     * 
     * @param val a word.
     * @param node next node to point at.
     */
    public WordNode(String val, WordNode node)
    {
        _val = new String(val);
        _next = node;
    }

    /**
     * this method returns a copy of the string the node is containing.
     * @return a copy of the string the node is containing.
     */
    public String getWord()
    {
        return new String(_val);
    }

    /**
     * this method returns a reference to the node this node is pointing at.
     * @return a reference to the node this node is pointing at.
     */
    public WordNode getNext()
    {
        return _next;
    }

    /**
     * set the string this node is containing.
     * @param val the new string to contain.
     */
    public void setWord(String val)
    {
        _val = new String(val);
    }

    /**
     * set the next node to point at.
     * @param node next node to point at.
     */
    public void setNext(WordNode node)
    {
        _next = node;
    }
} // end of class WordNode
